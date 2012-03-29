package org.openbel.workbench.ui.navigator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

public interface DocumentsContainer {

    public IFolder getFolder();

    public IProject getProject();
}
