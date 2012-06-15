/*******************************************************************************
 * Copyright (c) 2010 Mat Booth and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openbel.editor.ui.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * An abstract command handler that iterates through the current selection
 * looking for projects resources to save us from having this code in every
 * handler that wants to manipulate projects.
 */
public abstract class AbstractProjectHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil
                .getCurrentSelection(event);
        Iterator<?> iterator = selection.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            IProject project = null;
            if (element instanceof IProject) {
                project = (IProject) element;
            } else if (element instanceof IAdaptable) {
                project = (IProject) ((IAdaptable) element)
                        .getAdapter(IProject.class);
            }
            if (project != null) {
                fettleProject(project);
            }
        }
        return null;
    }

    /**
     * To be implemented by sub-classes to perform actions on a project. This
     * will be called once for every project resource that is currently
     * selected.
     * 
     * @param project
     *            a project resource for manipulation
     */
    protected abstract void fettleProject(IProject project);
}
