/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.wizards;

import static java.lang.String.format;
import static org.eclipse.jface.dialogs.MessageDialog.ERROR;
import static org.eclipse.jface.dialogs.MessageDialog.INFORMATION;
import static org.openbel.workbench.core.common.BELUtilities.*;
import static org.openbel.workbench.ui.Activator.*;
import static org.openbel.workbench.ui.UIFunctions.*;
import static org.openbel.workbench.ui.launch.LaunchDelegate.ATTR_DOC;
import static org.openbel.workbench.ui.launch.LaunchDelegate.KAM_DESC;
import static org.openbel.workbench.ui.launch.LaunchDelegate.KAM_NAME;
import static org.openbel.workbench.ui.launch.LaunchDelegate.tokenize;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.internal.dialogs.ExportWizard;
import org.openbel.workbench.ui.Activator;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BuilderExportWizard extends ExportWizard implements
        IWorkbenchWizard {

    BuilderWizardPage page;

    public BuilderExportWizard() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle("OpenBEL Workbench Export Wizard");
        setNeedsProgressMonitor(false);
        page = new BuilderWizardPage("Export KAM Builder", selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        super.addPage(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        final String syscfg = page.getSystemConfiguration();
        final String curBldr = page.getBuilder();
        File launch = getLaunchFile(curBldr);
        if (launch == null) {
            logError("launch isn't available");
            return false;
        }
        final String curDir = page.getOutputDirectory();
        final File outDir = new File(curDir);
        if (!outDir.canWrite()) {
            logError("can't write to: " + curDir);
            return false;
        }
        final String outDirAbs = outDir.getAbsolutePath();

        /*
         * XXX Parse the launch file. This isn't the best spot for this code.
         * (it may be better suited in launch delegate) 
         */

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(launch);
        } catch (ParserConfigurationException e) {
            logError(e);
            return false;
        } catch (SAXException e) {
            logError(e);
            return false;
        } catch (IOException e) {
            logError(e);
            return false;
        }

        doc.getDocumentElement().normalize();
        NodeList entries = doc.getElementsByTagName("mapEntry");

        String name = null, desc = null, docs = null;
        for (int i = 0; i < entries.getLength(); i++) {
            Node node = entries.item(i);
            NamedNodeMap attrs = node.getAttributes();
            if (attrs == null) continue;
            Node key = attrs.getNamedItem("key");
            if (key == null) continue;
            if (KAM_NAME.equals(key.getNodeValue())) {
                Node value = attrs.getNamedItem("value");
                if (value == null) continue;
                name = value.getNodeValue();
            } else if (KAM_DESC.equals(key.getNodeValue())) {
                Node value = attrs.getNamedItem("value");
                if (value == null) continue;
                desc = value.getNodeValue();
            } else if (ATTR_DOC.equals(key.getNodeValue())) {
                Node value = attrs.getNamedItem("value");
                if (value == null) continue;
                docs = value.getNodeValue();
            }
        }

        if (nulls(name, desc, docs)) {
            return false;
        }

        String[] docTokens = tokenize(docs);
        if (noItems(docTokens)) return false;

        final String kamName = name;
        final String kamDesc = desc;

        final List<IFile> documents = sizedArrayList(docTokens.length);
        Activator act = getDefault();
        for (final String token : docTokens) {
            IResource rsrc = act.getResource(token);
            IFile file = (IFile) rsrc.getAdapter(IFile.class);
            documents.add(file);
        }

        Job job = new Job("Exporting KAM builder...") {
            @Override
            protected IStatus run(IProgressMonitor monitor) {

                // 1: Create the 'documents' directory (build.xml dir.documents)
                // 2: Copy each document to the created directory.
                // 3: Copy build.xml.
                // 4: Copy belframework.cfg.
                // 5: Create build.properties.
                // 6: Place a README.txt file in the directory for contents.

                // Total work is number of steps above plus num docs - 1.
                int work = 6 + (documents.size() - 1);

                monitor.beginTask("Exporting", work);
                monitor.subTask("Creating output directory");

                // Pause a moment to show our progress status to the fans.
                pause();

                // 1: Create the 'documents' directory (build.xml dir.documents)
                String path = asPath(outDirAbs, "documents");
                File outDocs = new File(path);
                if (!outDocs.mkdir()) {
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                }
                monitor.worked(1);

                monitor.subTask("Exporting documents");
                // Pause a moment to show our progress status to the fans.
                pause();

                // 2: Copy each document to the created directory.
                for (final IFile f : documents) {
                    File src = new File(getAbsolutePath(f));
                    File dest = new File(asPath(path, src.getName()));
                    FileInputStream fis = null;
                    FileOutputStream fos = null;
                    FileChannel srcChan = null;
                    FileChannel destChan = null;
                    try {
                        fis = new FileInputStream(src);
                        fos = new FileOutputStream(dest);
                        srcChan = fis.getChannel();
                        destChan = fos.getChannel();
                        destChan.transferFrom(srcChan, 0, srcChan.size());
                    } catch (IOException e) {
                        logError(e);
                        monitor.setCanceled(true);
                        return Status.CANCEL_STATUS;
                    } finally {
                        closeSilently(fis);
                        closeSilently(fos);
                        closeSilently(srcChan);
                        closeSilently(destChan);
                    }
                    monitor.worked(1);
                }

                monitor.subTask("Creating build.xml");
                // Pause a moment to show our progress status to the fans.
                pause();

                // 3: Copy build.xml.
                path = asPath(outDirAbs, "build.xml");
                File buildfile = new File(path);
                String rsrc = "/org/openbel/workbench/ui/build.xml";
                InputStream in = getClass().getResourceAsStream(rsrc);
                BufferedInputStream bis = new BufferedInputStream(in);
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(buildfile);
                } catch (FileNotFoundException e) {
                    closeSilently(bis);
                    logError(e);
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                }
                byte[] buffer = new byte[1024];
                int len = 0;
                try {
                    while ((len = bis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    logError(e);
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                } finally {
                    closeSilently(bis);
                    closeSilently(fos);
                }
                monitor.worked(1);

                monitor.subTask("Cloning system configuration");
                // Pause a moment to show our progress status to the fans.
                pause();

                // 4: Copy belframework.cfg.
                File src = new File(syscfg);
                File dest = new File(asPath(outDirAbs, "belframework.cfg"));
                FileChannel srcChan = null, destChan = null;
                try {
                    if (!dest.createNewFile()) {
                        monitor.setCanceled(true);
                        return Status.CANCEL_STATUS;
                    }
                    srcChan = new FileInputStream(src).getChannel();
                    destChan = new FileOutputStream(dest).getChannel();
                    destChan.transferFrom(srcChan, 0, srcChan.size());
                } catch (IOException e) {
                    logError(e);
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                } finally {
                    if (srcChan != null) closeSilently(srcChan);
                    if (destChan != null) closeSilently(destChan);
                }
                monitor.worked(1);

                monitor.subTask("Creating build.properties");
                // Pause a moment to show our progress status to the fans.
                pause();

                // 5: Create build.properties.
                dest = new File(asPath(outDirAbs, "build.properties"));
                FileWriter fw = null;
                BufferedWriter bw = null;
                try {
                    fw = new FileWriter(dest);
                    bw = new BufferedWriter(fw);
                    StringBuilder bldr = new StringBuilder();

                    // KAM NAME
                    bldr.append("KAM_NAME = ");
                    bldr.append(kamName);
                    bldr.append("\n");

                    // KAM DESCRIPTION
                    bldr.append("KAM_DESCRIPTION = ");
                    bldr.append(kamDesc);
                    bldr.append("\n");

                    // Include EXTRA_COMPILER_ARGS for the user.
                    bldr.append("\n# Extra compiler arguments can be supplied here.\n");
                    bldr.append("# More than one argument will require quotes:\n");
                    bldr.append("# Examples:\n");
                    bldr.append("# EXTRA_COMPILER_ARGS = --no-phaseIII\n");
                    bldr.append("# EXTRA_COMPILER_ARGS = \"--no-phaseIII --timing --verbose\"\n");
                    bldr.append("#\n");
                    bldr.append("#EXTRA_COMPILER_ARGS =\n");

                    bw.write(bldr.toString());
                } catch (IOException e) {
                    logError(e);
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                } finally {
                    closeSilently(bw);
                    closeSilently(fw);
                }
                monitor.worked(1);

                monitor.subTask("Generating README");
                // Pause a moment to show our progress status to the fans.
                pause();

                // 6: Create build.properties.
                dest = new File(asPath(outDirAbs, "README.txt"));
                fw = null;
                bw = null;
                try {
                    fw = new FileWriter(dest);
                    bw = new BufferedWriter(fw);
                    StringBuilder bldr = new StringBuilder();

                    bldr.append("# OpenBEL Workbench KAM builder export");
                    bldr.append(System.getProperty("line.separator"));
                    bldr.append("exported_by: ");
                    bldr.append(System.getProperty("user.name"));
                    bldr.append(System.getProperty("line.separator"));
                    bldr.append("export_made_at: ");
                    bldr.append(new Date().toString());
                    bldr.append(System.getProperty("line.separator"));

                    bw.write(bldr.toString());
                } catch (IOException e) {
                    logError(e);
                    monitor.setCanceled(true);
                    return Status.CANCEL_STATUS;
                } finally {
                    closeSilently(bw);
                    closeSilently(fw);
                }
                monitor.worked(1);

                monitor.done();
                return Status.OK_STATUS;
            }
        };
        job.setPriority(Job.SHORT);
        job.addJobChangeListener(new IJobChangeListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void sleeping(IJobChangeEvent ev) {

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void scheduled(IJobChangeEvent ev) {

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void running(IJobChangeEvent ev) {

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void done(IJobChangeEvent ev) {
                if (ev.getResult() == Status.OK_STATUS) {
                    exportSuccess(outDirAbs);
                } else if (ev.getResult() == Status.CANCEL_STATUS) {
                    exportFailed(outDirAbs);
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void awake(IJobChangeEvent ev) {

            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void aboutToRun(IJobChangeEvent ev) {

            }
        });
        job.schedule();
        return true;
    }

    /**
     * Pops up a message dialog indicating the KAM builder export succeeded.
     * 
     * @param outputDir Output directory
     */
    private void exportSuccess(String outputDir) {
        final String title = "Export Successful";
        final StringBuilder bldr = new StringBuilder();
        bldr.append("Export completed.\n\n");
        bldr.append(format("The contents of \"%s\" can be used ", outputDir));
        bldr.append("anywhere the BEL Framework is installed.\n");
        final String msg = bldr.toString();
        runAsync(new Runnable() {
            @Override
            public void run() {
                MessageDialog dlg = new MessageDialog(getShell(), title,
                        null, msg, INFORMATION, new String[] { "&OK" }, 0);
                dlg.open();
            }
        });
    }

    /**
     * Pops up a message dialog indicating the KAM builder export failed.
     * 
     * @param outputDir Output directory
     */
    private void exportFailed(String outputDir) {
        final String title = "Export Failed";
        final String msg = "Export unsuccessful. Errors may have been reported.";
        runAsync(new Runnable() {
            @Override
            public void run() {
                MessageDialog dlg = new MessageDialog(getShell(), title,
                        null, msg, ERROR, new String[] { "&OK" }, 0);
                dlg.open();
            }
        });
    }

    private void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFinish() {
        return page.isPageComplete();
    }

}
