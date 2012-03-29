package org.openbel.workbench.ui;

import static org.openbel.workbench.ui.Activator.getBELDocuments;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.Activator.getDocumentGroups;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A reusable content provider providing all documents and document groups.
 */
public class DocumentContentProvider implements ITreeContentProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inputChanged(Viewer view, Object oldInput, Object newInput) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getChildren(Object o) {
        if (o instanceof IProject) {
            return getDocumentGroups((IProject) o);
        } else if (o instanceof IFolder) {
            return getBELDocuments((IFolder) o);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getElements(Object o) {
        IProject[] prjs = getDefault().getOpenedBELProjects();
        return prjs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParent(Object o) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildren(Object o) {
        if (o instanceof IProject) {
            IFolder[] fldrs = getDocumentGroups((IProject) o);
            return fldrs.length != 0;
        } else if (o instanceof IFolder) {
            IFile[] docs = getBELDocuments((IFolder) o);
            return docs.length != 0;
        }
        return false;
    }

}
