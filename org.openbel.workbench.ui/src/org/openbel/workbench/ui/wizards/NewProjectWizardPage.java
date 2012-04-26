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

import static org.eclipse.debug.internal.ui.SWTFactory.createComposite;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.SINGLE;
import static org.eclipse.swt.layout.GridData.FILL_BOTH;
import static org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
import static org.eclipse.ui.dialogs.PreferencesUtil.createPreferenceDialogOn;
import static org.openbel.workbench.core.common.BELUtilities.noLength;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.Activator.getImageDescriptor;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.openbel.workbench.ui.preferences.BELFrameworkPreferencePage;

/**
 * {@link NewProjectWizardPage} represents the create project {@link WizardPage
 * wizard page}. The flow of the wizard page is:
 * <ol>
 * <li>Read BEL Framework home location from
 * {@link BELFrameworkPreferencePage#BF_PREF_KEY}. If this preference is not set
 * then show an error message and prevent finishing, otherwise just show an info
 * message.</li>
 * <li>User enters a project name.</li>
 * <li>User finishes the wizard.</li>
 * </ol>
 */
public class NewProjectWizardPage extends WizardPage {
    private Group g1;
    private Text txtProject;
    private Link link;
    private final static String desc1;
    private final static String desc2;
    private final static String desc3;
    private final static String err1;
    private final static String linktxt;
    static {
        linktxt = "<a>Configure</a> your BEL Framework installation before "
                + "creating a new project.";
        desc1 = "Enter a project name.";
        desc2 = "Your BEL Framework installation has not been configured.";
        desc3 = "Create a BEL project.";
        err1 = "A project with this name already exists.";
    }

    /**
     * Constructs the create project page.
     * 
     * <p>
     * Reads the {@link BELFrameworkPreferencePage#BF_PREF_KEY preference} to
     * determine if the BEL Framework home location is set.
     * </p>
     */
    protected NewProjectWizardPage() {
        super("basicPage");
        setTitle("Create a BEL Project");
        setDescription(desc3);
        setImageDescriptor(getImageDescriptor("icons/logo.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(Composite parent) {
        Font f = parent.getFont();
        Composite composite = createComposite(parent, f, 1, 1, FILL_BOTH);
        setControl(composite);
        ((GridLayout) composite.getLayout()).verticalSpacing = 15;

        Listener listener = new Listener();

        boolean no_home = false;
        if (noLength(getDefault().getBELFrameworkHome())) {
            link = new Link(composite, NONE);
            link.setText(linktxt);
            link.addSelectionListener(listener);
            no_home = true;
        }

        g1 = new Group(composite, NONE);
        g1.setText("&Project Name:");
        GridData gd = new GridData(FILL_HORIZONTAL);
        g1.setLayoutData(gd);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        g1.setLayout(gl);
        g1.setFont(f);

        txtProject = new Text(g1, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtProject.setLayoutData(gd);
        txtProject.setFont(f);
        txtProject.addKeyListener(listener);
        if (no_home) {
            g1.setEnabled(false);
        }

        setPageComplete(false);
        setControl(composite);
    }

    /**
     * Returns the project name entered by the user.
     * 
     * @return the project name
     */
    public String getProjectName() {
        return txtProject.getText();
    }

    private void update() {
        if (noLength(getDefault().getBELFrameworkHome())) {
            setDescription(desc2);
            g1.setEnabled(false);
            setPageComplete(false);
            return;
        }
        g1.setEnabled(true);
        if (noLength(txtProject.getText())) {
            setDescription(desc1);
            setPageComplete(false);
            return;
        }
        String prj = txtProject.getText();
        if (getDefault().getProject(prj) != null) {
            setErrorMessage(err1);
            setPageComplete(false);
            return;
        }
        setDescription(desc3);
        setErrorMessage(null);
        setPageComplete(true);
    }

    private class Listener implements SelectionListener, KeyListener {

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
            if (ev.getSource() == link) {
                PreferenceDialog dlg;
                dlg = createPreferenceDialogOn(getShell(),
                        "org.openbel.workbench.ui.preferences", null, null);
                dlg.open();
                update();
            }

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(KeyEvent ev) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyReleased(KeyEvent ev) {
            update();
        }
    }
}
