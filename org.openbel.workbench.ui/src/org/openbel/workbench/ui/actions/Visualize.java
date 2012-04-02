package org.openbel.workbench.ui.actions;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static org.eclipse.jface.dialogs.MessageDialog.ERROR;
import static org.openbel.workbench.core.CoreFunctions.compilerException;
import static org.openbel.workbench.core.common.BELUtilities.asPath;
import static org.openbel.workbench.core.common.BELUtilities.closeSilently;
import static org.openbel.workbench.core.common.BELUtilities.deleteDirectory;
import static org.openbel.workbench.core.common.BELUtilities.hasLength;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIConstants.BUILDER_PROCESS_TYPE;
import static org.openbel.workbench.ui.UIFunctions.*;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateCytoscape;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.ant.internal.core.AbstractEclipseBuildLogger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.util.ValidationUtilities.FileState;

/**
 * Handles visualization of BEL/XBEL documents using Cytoscape.
 */
public class Visualize extends ActionDelegate implements IObjectActionDelegate {
    private String document;

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(IAction action, ISelection sel) {
        super.selectionChanged(action, sel);
        document = null;
        if (!(sel instanceof TreeSelection)) {
            return;
        }
        TreeSelection ts = (TreeSelection) sel;
        Object first = ts.getFirstElement();
        if ((first == null) || !(first instanceof IFile)) {
            return;
        }
        IFile file = (IFile) first;
        document = getAbsolutePath(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(IAction action) {
        if (document == null) {
            return;
        }

        // validate cytoscape install, error dialog if invalid
        boolean valid = checkCytoscapeHome();
        if (!valid) {
            return;
        }

        Job job = new VJob();
        job.setPriority(Job.LONG);
        job.addJobChangeListener(new IJobChangeListener() {

            @Override
            public void sleeping(IJobChangeEvent arg0) {

            }

            @Override
            public void scheduled(IJobChangeEvent arg0) {

            }

            @Override
            public void running(IJobChangeEvent arg0) {

            }

            @Override
            public void done(IJobChangeEvent arg0) {

            }

            @Override
            public void awake(IJobChangeEvent arg0) {

            }

            @Override
            public void aboutToRun(IJobChangeEvent arg0) {

            }
        });
        job.schedule();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart part) {
    }

    private String[] buildArgs(String document) {
        List<String> ret = new ArrayList<String>();
        final StringBuilder bldr = new StringBuilder();

        Activator act = getDefault();

        bldr.append("-DBELFRAMEWORK_HOME=");
        bldr.append(act.getBELFrameworkHome());
        ret.add(bldr.toString());
        bldr.setLength(0);

        bldr.append("-DCYTOSCAPE_HOME=");
        bldr.append(act.getCytoscapeHome());
        ret.add(bldr.toString());
        bldr.setLength(0);

        bldr.append("-DDOCUMENT=");
        bldr.append(document);
        ret.add(bldr.toString());
        bldr.setLength(0);

        return ret.toArray(new String[0]);
    }

    private boolean checkCytoscapeHome() {
        String cythome = getDefault().getCytoscapeHome();
        if (validateCytoscape(cythome) != FileState.OK) {
            final String title = "Cytoscape disabled";
            final String msg = "Cytoscape support is disabled.";
            Display.getDefault().syncExec(new Runnable() {
                @Override
                public void run() {
                    okDialog(title, msg, ERROR);
                }
            });
            return false;
        }
        return true;
    }

    private class VJob extends Job {

        public VJob() {
            super("Visualizing ".concat(document));
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        protected IStatus run(IProgressMonitor monitor) {
            if (document == null) {
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            }

            // validate cytoscape install, error dialog if invalid
            boolean valid = checkCytoscapeHome();
            if (!valid) {
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            }

            File tmpdir;
            try {
                tmpdir = createTempDirectory();
            } catch (IOException e) {
                // TODO error dialog
                logError(e);
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            }

            String rsrc = "/org/openbel/workbench/ui/visualize.xml";
            InputStream in = getClass().getResourceAsStream(rsrc);
            BufferedInputStream bis = new BufferedInputStream(in);
            FileOutputStream fos;

            String buildfile = asPath(tmpdir.getAbsolutePath(), "visualize.xml");
            try {
                fos = new FileOutputStream(buildfile);
            } catch (FileNotFoundException e) {
                closeSilently(bis);
                logError(e);
                deleteDirectory(tmpdir);
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
                deleteDirectory(tmpdir);
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            } finally {
                closeSilently(bis);
                closeSilently(fos);
            }

            rsrc = "/org/openbel/workbench/ui/visualize.cfg";
            in = getClass().getResourceAsStream(rsrc);
            bis = new BufferedInputStream(in);
            String syscfg = asPath(tmpdir.getAbsolutePath(), "visualize.cfg");
            try {
                fos = new FileOutputStream(syscfg);
            } catch (FileNotFoundException e) {
                closeSilently(bis);
                logError(e);
                deleteDirectory(tmpdir);
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            }
            buffer = new byte[1024];
            len = 0;
            try {
                while ((len = bis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                logError(e);
                deleteDirectory(tmpdir);
                monitor.setCanceled(true);
                return Status.CANCEL_STATUS;
            } finally {
                closeSilently(bis);
                closeSilently(fos);
            }

            Map attrs = new HashMap(2);
            attrs.put(IProcess.ATTR_PROCESS_TYPE, BUILDER_PROCESS_TYPE);
            String stamp = valueOf(currentTimeMillis());
            attrs.put(AbstractEclipseBuildLogger.ANT_PROCESS_ID, stamp);
            AntRunner ar = new AntRunner();
            //ar.setExecutionTargets(new String[] { "", "" });
            ar.setBuildFileLocation(buildfile);
            ar.setArguments(buildArgs(document));
            try {
                monitor.setTaskName("This task will finish once Cytoscape is closed.");
                ar.run();
            } catch (CoreException e) {
                CoreException e2 = compilerException(e);
                if (e2 != e) {
                    // Different exception - let's be more informative to the user
                    final String title = "Compiler error";
                    final String msg = e2.getMessage();
                    runAsync(new Runnable() {
                        @Override
                        public void run() {
                            okDialog(title, msg, ERROR);
                        }
                    });
                } else if (hasLength(e.getMessage())) {
                    final String title = "Error";
                    final String msg = e.getMessage();
                    runAsync(new Runnable() {
                        @Override
                        public void run() {
                            okDialog(title, msg, ERROR);
                        }
                    });

                }
                return Status.CANCEL_STATUS;
            }

            deleteDirectory(tmpdir);
            return Status.OK_STATUS;
        }
    }
}
