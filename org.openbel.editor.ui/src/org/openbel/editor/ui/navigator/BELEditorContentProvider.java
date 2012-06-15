/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.navigator;

import static org.openbel.editor.core.Nature.BEL_NATURE;
import static org.openbel.editor.ui.UIFunctions.logError;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class BELEditorContentProvider implements ICommonContentProvider,
        IResourceChangeListener {
    public static final Object[] NO_CHILDREN = new Object[0];
    private Object input = null;
    private TreeViewer viewer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // nothing yet
    }

    private void findAddedResource(IResourceDelta delta,
            ArrayList<IResource> addedFiles) {

        int kind = delta.getKind();
        IResource resource = delta.getResource();

        if ((kind == IResourceDelta.ADDED) && !addedFiles.contains(resource)) {
            addedFiles.add(resource);
        }

        for (IResourceDelta child : delta.getAffectedChildren()) {
            findAddedResource(child, addedFiles);
        }

    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IWorkspaceRoot) {
            final IWorkspaceRoot root = (IWorkspaceRoot) parentElement;
            return root.getProjects();
        } else if (parentElement instanceof DocumentsContainer) {
            final DocumentsContainer container = (DocumentsContainer) parentElement;

            try {
                List<Object> result = new ArrayList<Object>();
                IFolder baseFolder = container.getFolder();
                for (IResource res : baseFolder.members()) {
                    result.add(res);
                }

                return result.toArray();
            } catch (CoreException e) {
                logError(e);

                return NO_CHILDREN;
            }
        }

        return NO_CHILDREN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getElements(Object inputElement) {
        if (input != null) {
            return getChildren(input);
        }

        return NO_CHILDREN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParent(Object element) {
        if (element instanceof IWorkspaceRoot) {
            return null;
        } else if (element instanceof IProject) {
            return ((IProject) element).getParent();
        } else if (element instanceof DocumentsFolder) {
            return ((DocumentsFolder) element).getProject();
        } else if (element instanceof IResource) {
            IResource resource = (IResource) element;
            return resource.getParent();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ICommonContentExtensionSite cfg) {
        // nothing yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (this.viewer != null) {
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        }

        this.viewer = (TreeViewer) viewer;

        if (this.viewer != null) {
            ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
        }

        input = newInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        final int eventType = event.getType();
        // IResource resource = event.getResource();
        IResourceDelta delta = event.getDelta();
        if (delta == null) {
            return;
        }
        int kind = delta.getKind();

        if ((eventType == IResourceChangeEvent.POST_CHANGE)
                && (kind == IResourceDelta.CHANGED)) {
            ArrayList<IResource> addedResources = new ArrayList<IResource>();
            findAddedResource(delta, addedResources);
            final ArrayList<IProject> prjs = new ArrayList<IProject>();
            for (IResource addedResource : addedResources) {
                IProject project = addedResource.getProject();
                if (!project.isOpen()) {
                    continue;
                }
                try {
                    if (!prjs.contains(project)
                            && project.hasNature(BEL_NATURE)) {
                        prjs.add(project);
                    }
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }

            if (!prjs.isEmpty()) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        for (IProject project : prjs) {
                            viewer.refresh(project, true);
                        }
                    }
                });
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreState(IMemento aMemento) {
        // no-op
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveState(IMemento aMemento) {
        // no-op
    }
}
