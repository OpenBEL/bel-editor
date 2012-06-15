/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.openbel.editor.ui.wizards;

import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.openbel.editor.core.common.BELUtilities.noLength;
import static org.openbel.editor.ui.Activator.getDefault;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class ImportWizard extends Wizard implements IImportWizard {

    ImportWizardPage page;

    public ImportWizard() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        IFile file = page.createNewFile();
        if (file == null)
            return false;
        return true;
    }

    @Override
    public boolean canFinish() {
        if (noLength(page.getSelectedDocument())) {
            // No error here
            return false;
        }

        IPath path = page.getContainerFullPath();
        IWorkspace w = getWorkspace();
        if (w == null) {
            page.setErrorMessage("No usable workspace.");
            return false;
        }

        IWorkspaceRoot root = w.getRoot();
        String _0 = path.segment(0);
        IProject prj = root.getProject(_0);

        if (!getDefault().isOpenBELProject(prj)) {
            page.setErrorMessage("Select a BEL Editor project.");
            return false;
        }

        if (path.segmentCount() != 2) {
            page.setErrorMessage("Parent folder must be a document group.");
            return false;
        }

        if (noLength(page.getNewFileName())) {
            page.setErrorMessage("Enter a new file name.");
            return false;
        }

        return super.canFinish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle("BEL Editor Import Wizard"); // NON-NLS-1
        setNeedsProgressMonitor(false);
        page = new ImportWizardPage("Import BEL Script", selection); // NON-NLS-1
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        super.addPages();
        addPage(page);
    }

}
