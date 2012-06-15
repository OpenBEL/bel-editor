/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.actions;

import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.swt.widgets.Display.getCurrent;
import static org.openbel.editor.core.common.BELUtilities.noLength;
import static org.openbel.editor.ui.Activator.getDefault;
import static org.openbel.editor.ui.UIFunctions.logError;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionDelegate;

/**
 * Handles creation of document groups.
 */
public class DocumentGroupDelegate extends ActionDelegate implements
        IObjectActionDelegate {
    private final static String DIALOG_PROMPT;
    private final static String DIALOG_TITLE;
    static {
        DIALOG_TITLE = "New BEL Document Group";
        DIALOG_PROMPT = "Document group name:";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(IAction action) {
        Shell shell = getCurrent().getActiveShell();
        InputDialog i = new InputDialog(shell, DIALOG_TITLE, DIALOG_PROMPT, "",
                new IInputValidator() {
                    @Override
                    public String isValid(String newGrp) {
                        return validateDocumentGroup(newGrp);
                    }
                });
        i.open();
        // super.run(action);
        if (i.getReturnCode() == Window.OK) {
            String userInput = i.getValue();
            List<String> projects = getDefault().getSelectedProjects();
            for (final String project : projects) {
                IWorkspaceRoot r = getWorkspace().getRoot();
                IProject p = r.getProject(project);
                IFolder f = p.getFolder(userInput);
                try {
                    f.create(false, true, new NullProgressMonitor());
                } catch (CoreException e) {
                    logError(e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void runWithEvent(IAction action, Event event) {
        // invoked immediately before invocation of run(IAction)
        super.runWithEvent(action, event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
        // invoked on project right-click
    }

    private String validateDocumentGroup(String newGrp) {
        if (noLength(newGrp)) {
            return "";
        }
        IWorkspaceRoot r = getWorkspace().getRoot();
        List<String> projects = getDefault().getSelectedProjects();
        for (final String project : projects) {
            IProject p = r.getProject(project);
            IFolder f = p.getFolder(newGrp);
            if (f.exists()) {
                final StringBuilder bldr = new StringBuilder();
                bldr.append("Document group already exists in the project: ");
                bldr.append(project);
                return bldr.toString();
            }
        }
        return null;
    }
}
