/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.wizards;

import static java.lang.String.format;
import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.debug.internal.ui.SWTFactory.createComposite;
import static org.eclipse.debug.internal.ui.SWTFactory.createPushButton;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.H_SCROLL;
import static org.eclipse.swt.SWT.SINGLE;
import static org.eclipse.swt.SWT.V_SCROLL;
import static org.eclipse.swt.layout.GridData.FILL_BOTH;
import static org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
import static org.openbel.editor.core.common.BELUtilities.hasItems;
import static org.openbel.editor.core.common.BELUtilities.hasLength;
import static org.openbel.editor.core.common.BELUtilities.readable;
import static org.openbel.editor.ui.UIConstants.NO_KAM_BUILDERS;
import static org.openbel.editor.ui.UIConstants.SYSCFG_EXTENSION;
import static org.openbel.editor.ui.UIFunctions.getAbsolutePath;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.openbel.editor.ui.Activator;
import org.openbel.editor.ui.OpenBELProviders.LaunchContent;
import org.openbel.editor.ui.OpenBELProviders.LaunchLabels;

/**
 * Presents an output directory and KAM builder list view to the user to export
 * a KAM builder.
 */
public class BuilderWizardPage extends WizardPage {
    private final static String desc1;
    private final static String desc2;
    private final static String desc3;
    private final static String desc4;
    private final static String desc5;
    private final static String err1;
    private final static String err2;
    private final static String err3;
    private final static String err4;
    static {
        desc1 = "Select an empty directory, system configuration, and KAM builder.";
        desc2 = "Select an empty directory.";
        desc3 = "Select the KAM builder to export.";
        desc4 = "Ready to export.";
        desc5 = "Select a system configuration.";
        err1 = "The directory cannot be written to.";
        err2 = "The directory is not empty.";
        err3 = "The system configuration cannot be read.";
        err4 = "System configuration files end with the extension \"%s\".";
    }
    private Button btnBrowseDir;
    private Text txtDir;
    private Button btnBrowseSysCfg;
    private Text txtSysCfg;
    private TreeViewer treeViewer;
    private Tree tree;

    /**
     * New export wizard page.
     * 
     * @param pageName The title of the page
     * @param selection {@link IStructuredSelection}
     */
    public BuilderWizardPage(String pageName, IStructuredSelection selection) {
        super(pageName);
        setTitle(pageName);
        setDescription(desc1);
        setPageComplete(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(Composite parent) {
        Font f = parent.getFont();
        Composite composite = createComposite(parent, f, 1, 1, FILL_BOTH);
        setControl(composite);
        ((GridLayout) composite.getLayout()).verticalSpacing = 5;

        Listener listener = new Listener();

        Group g1 = new Group(composite, NONE);
        g1.setText("&To directory:");
        GridData gd = new GridData(FILL_HORIZONTAL);
        g1.setLayoutData(gd);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        g1.setLayout(gl);
        g1.setFont(f);
        txtDir = new Text(g1, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtDir.setLayoutData(gd);
        txtDir.setFont(f);
        txtDir.setEnabled(false);
        btnBrowseDir = createPushButton(g1, "&Browse...", null);
        btnBrowseDir.addSelectionListener(listener);

        Group g2 = new Group(composite, NONE);
        g2.setText("&System configuration:");
        gd = new GridData(FILL_HORIZONTAL);
        g2.setLayoutData(gd);
        gl = new GridLayout();
        gl.numColumns = 2;
        g2.setLayout(gl);
        g2.setFont(f);
        txtSysCfg = new Text(g2, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtSysCfg.setLayoutData(gd);
        txtSysCfg.setFont(f);
        txtSysCfg.setEnabled(false);
        btnBrowseSysCfg = createPushButton(g2, "&Browse...", null);
        btnBrowseSysCfg.addSelectionListener(listener);

        Label lbl = new Label(composite, NONE);
        lbl.setText("&KAM builder:");
        int style = BORDER | H_SCROLL | V_SCROLL;

        treeViewer = new TreeViewer(composite, style);
        treeViewer.setContentProvider(new LaunchContent());
        treeViewer.setLabelProvider(new LaunchLabels());
        treeViewer.setInput("root");
        treeViewer.expandAll();
        gd = new GridData(FILL_BOTH);
        treeViewer.addSelectionChangedListener(listener);
        tree = treeViewer.getTree();
        tree.setLayoutData(gd);
        update();
    }

    /**
     * Returns the builder selected by the user.
     * 
     * @return {@link String}
     * @see Activator#getBuilders()
     */
    public String getBuilder() {
        return tree.getSelection()[0].getText();
    }

    /**
     * Returns the absolute path to the output directory selected by the user.
     * 
     * @return {@link String}
     */
    public String getOutputDirectory() {
        return txtDir.getText();
    }

    /**
     * Returns the system configuration selected by the user.
     * 
     * @return {@link String}
     */
    public String getSystemConfiguration() {
        return txtSysCfg.getText();
    }

    private void update() {
        if (tree.getItemCount() == 0) {
            setErrorMessage(NO_KAM_BUILDERS);
            setPageComplete(false);
            return;
        }
        boolean validBldr = (tree.getSelectionCount() != 0);
        String absdir = txtDir.getText();
        boolean validDir = hasLength(absdir);
        String syscfg = txtSysCfg.getText();
        boolean validSysCfg = hasLength(syscfg);

        if (!validBldr && !validDir && !validSysCfg) {
            setDescription(desc1);
            setErrorMessage(null);
            setPageComplete(false);
            return;
        }
        if (!validDir) {
            setDescription(desc2);
            setErrorMessage(null);
            setPageComplete(false);
            return;
        }
        File dir = new File(absdir);
        if (!dir.canWrite()) {
            setErrorMessage(err1);
            setPageComplete(false);
            return;
        }
        if (hasItems(dir.list())) {
            setErrorMessage(err2);
            setPageComplete(false);
            return;
        }
        if (!validSysCfg) {
            setDescription(desc5);
            setErrorMessage(null);
            setPageComplete(false);
            return;
        }
        if (!syscfg.endsWith(SYSCFG_EXTENSION)) {
            setErrorMessage(format(err4, SYSCFG_EXTENSION));
            setPageComplete(false);
            return;
        }
        if (!readable(new File(syscfg))) {
            setErrorMessage(err3);
            setPageComplete(false);
            return;
        }
        if (!validBldr) {
            setDescription(desc3);
            setErrorMessage(null);
            setPageComplete(false);
            return;
        }
        // XXX bldr assumed readable

        setDescription(desc4);
        setErrorMessage(null);
        setPageComplete(true);
    }

    private class Listener implements SelectionListener,
            ISelectionChangedListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetDefaultSelected(SelectionEvent ev) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(SelectionEvent ev) {
            Object src = ev.getSource();
            if (src == btnBrowseDir) {
                File dir = chooseDirectory();
                if (dir == null) {
                    txtDir.setText("");
                } else {
                    String name = dir.getAbsolutePath();
                    txtDir.setText(name);
                }
                update();
            } else if (src == btnBrowseSysCfg) {
                ILabelProvider lbls = new WorkbenchLabelProvider();
                ITreeContentProvider cntnt = new BaseWorkbenchContentProvider();
                ElementTreeSelectionDialog dlg;
                dlg = new ElementTreeSelectionDialog(getShell(), lbls, cntnt);
                dlg.setInput(getWorkspace().getRoot());
                if (dlg.open() == Window.OK) {
                    IFile f = (IFile) dlg.getFirstResult();
                    txtSysCfg.setText(getAbsolutePath(f));
                    update();
                }
            }
        }

        private File chooseDirectory() {
            DirectoryDialog d = new DirectoryDialog(getShell());
            String dir = d.open();
            if (dir == null) {
                return null;
            }
            return new File(dir);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void selectionChanged(SelectionChangedEvent ev) {
            Object src = ev.getSource();
            if (src == treeViewer) {
                update();
            }
        }
    }
}
