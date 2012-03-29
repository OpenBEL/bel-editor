package org.openbel.workbench.ui.navigator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IContributorResourceAdapter;

public class BaseDocumentsFolder implements DocumentsFolder, IAdaptable,
        IContributorResourceAdapter {

    private final IFolder folder;

    public BaseDocumentsFolder(IFolder folder) {
        this.folder = folder;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof IFolder) {
            IFolder other = (IFolder) o;
            return folder.equals(other);
        }
        return false;
    }

    @Override
    public IResource getAdaptedResource(IAdaptable adaptable) {
        return folder;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IResource.class) {
            return folder;
        }
        if (adapter == IContributorResourceAdapter.class) {
            return this;
        }
        Object result = folder.getAdapter(adapter);
        return result;
    }

    @Override
    public IFolder getFolder() {
        return folder;
    }

    @Override
    public String getName() {
        return folder.getName();
    }

    @Override
    public IProject getProject() {
        return folder.getProject();
    }

    @Override
    public int hashCode() {
        return folder.hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

}
