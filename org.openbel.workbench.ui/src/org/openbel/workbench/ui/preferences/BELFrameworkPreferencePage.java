/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.preferences;

import static org.eclipse.debug.internal.ui.SWTFactory.createComposite;
import static org.eclipse.debug.internal.ui.SWTFactory.createPushButton;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.SINGLE;
import static org.eclipse.swt.layout.GridData.FILL_BOTH;
import static org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
import static org.openbel.workbench.core.common.BELUtilities.hasLength;
import static org.openbel.workbench.ui.Activator.BF_PREF_KEY;
import static org.openbel.workbench.ui.Activator.CY_PREF_KEY;
import static org.openbel.workbench.ui.Activator.ENV_BELFRAMEWORK_HOME;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIFunctions.logError;
import static org.openbel.workbench.ui.util.StackUtilities.myFrame;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateCytoscape;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateFramework;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.openbel.workbench.ui.util.ValidationUtilities.FileState;

/**
 * {@link BELFrameworkPreferencePage} represents a workbench preference page
 * that allows the user to set the BEL Framework home location.
 */
public class BELFrameworkPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {
    private final static String err1;
    private final static String err2;
    private final static String err3;
    private final static String err4;
    static {
        err1 = "Missing BEL Framework installation path.";
        err2 = "Invalid BEL Framework installation path.";
        err3 = "Cytoscape support is disabled.";
        err4 = "Invalid Cytoscape path, support is disabled.";
    }

    private String bfPrefValue;
    private String cyPrefValue;
    private Text txtBELFrameworkInstall;
    private Button btnBFBrowse;
    private Text txtCytoscapeInstall;
    private Button btnCYBrowse;

    /**
     * BEL framework preference page.
     */
    public BELFrameworkPreferencePage() {
        this.setDescription("Specify paths to software installations.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        Font f = parent.getFont();
        Composite composite = createComposite(parent, f, 1, 1, FILL_BOTH);
        //setControl(composite);
        ((GridLayout) composite.getLayout()).verticalSpacing = 5;

        Listener listener = new Listener();

        Group g1 = new Group(composite, NONE);
        g1.setText("&BEL Framework installation:");
        GridData gd = new GridData(FILL_HORIZONTAL);
        g1.setLayoutData(gd);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        g1.setLayout(gl);
        g1.setFont(f);
        txtBELFrameworkInstall = new Text(g1, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtBELFrameworkInstall.setLayoutData(gd);
        txtBELFrameworkInstall.setFont(f);
        txtBELFrameworkInstall.setEnabled(false);
        btnBFBrowse = createPushButton(g1, "&Browse...", null);
        btnBFBrowse.addSelectionListener(listener);

        Group g2 = new Group(composite, NONE);
        g2.setText("&Cytoscape installation:");
        gd = new GridData(FILL_HORIZONTAL);
        g2.setLayoutData(gd);
        g2.setLayout(gl);
        g2.setFont(f);
        txtCytoscapeInstall = new Text(g2, SINGLE | BORDER);
        txtCytoscapeInstall.setLayoutData(gd);
        txtCytoscapeInstall.setFont(f);
        txtCytoscapeInstall.setEnabled(false);
        btnCYBrowse = createPushButton(g2, "Br&owse...", null);
        btnCYBrowse.addSelectionListener(listener);

        if (bfPrefValue != null) {
            txtBELFrameworkInstall.setText(bfPrefValue);
        }

        IPreferenceStore prefs = getDefault().getPreferenceStore();
        if (prefs.contains(BF_PREF_KEY)) {
            txtBELFrameworkInstall.setText(prefs.getString(BF_PREF_KEY));
        } else {
            initializeDefaults();
        }
        if (prefs.contains(CY_PREF_KEY)) {
            txtCytoscapeInstall.setText(prefs.getString(CY_PREF_KEY));
        }

        update();
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        initializeDefaults();
        txtBELFrameworkInstall.setText(ENV_BELFRAMEWORK_HOME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench wb) {
    }

    /**
     * <p>
     * Retrieve the Open BEL Workbench preference store.
     * </p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        return getDefault().getPreferenceStore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performApply() {
        logError("(unbound apply handler - " + myFrame() + ")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        storeBELFramework();
        if (hasLength(cyPrefValue)) {
            doGetPreferenceStore().setValue(CY_PREF_KEY, cyPrefValue);
        }

        return true;
    }

    /**
     * Stores the BEL Framework preference and reloads the configuration if
     * necessary.
     */
    private void storeBELFramework() {
        // get original preference
        IPreferenceStore prefs = getDefault().getPreferenceStore();
        String original = prefs.getString(BF_PREF_KEY);

        // store new preference
        doGetPreferenceStore().setValue(BF_PREF_KEY, bfPrefValue);

        // reload resources iff the BEL Framework Home location changed.
        if (!bfPrefValue.equals(original)) {
            getDefault().reloadResources();
        }
    }

    /**
     * Set default preference value and validate.
     */
    private void initializeDefaults() {
        bfPrefValue = ENV_BELFRAMEWORK_HOME;
        update();
    }

    private void update() {
        String bftxt = txtBELFrameworkInstall.getText();
        boolean haveBF = hasLength(bftxt);
        String cytxt = txtCytoscapeInstall.getText();
        boolean haveCY = hasLength(cytxt);

        if (!haveBF) {
            setErrorMessage(err1);
            setValid(false);
            return;
        }
        FileState state = validateFramework(bftxt);
        if (state != FileState.OK) {
            setErrorMessage(err2);
            setValid(false);
            return;
        }

        bfPrefValue = bftxt;
        setValid(true);

        if (!haveCY) {
            setErrorMessage(err3);
            return;
        }

        state = validateCytoscape(cytxt);
        if (state != FileState.OK) {
            setErrorMessage(err4);
            return;
        }
        setErrorMessage(null);
    }

    private class Listener implements SelectionListener {

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
            if (src == btnBFBrowse) {
                final DirectoryDialog fd = new DirectoryDialog(getShell());
                fd.setMessage("Locate the BEL Framework installation");
                fd.setText("Open");
                fd.setFilterPath(System.getenv("user.dir")); //$NON-NLS-1$
                String selected = fd.open();
                if (selected != null) {
                    bfPrefValue = selected;
                    txtBELFrameworkInstall.setText(selected);
                    update();
                }
            } else if (src == btnCYBrowse) {
                final DirectoryDialog fd = new DirectoryDialog(getShell());
                fd.setMessage("Locate the Cytoscape installation");
                fd.setText("Open");
                fd.setFilterPath(System.getenv("user.dir")); //$NON-NLS-1$
                String selected = fd.open();
                if (selected != null) {
                    cyPrefValue = selected;
                    txtCytoscapeInstall.setText(selected);
                    update();
                }
            }
        }
    }
}
