package org.openbel.workbench.ui.commands;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;

/**
 * Implementation of the project handler that adds the BEL nature to a project.
 */
public class AddNature extends AbstractProjectHandler {

    @Override
    protected void fettleProject(IProject project) {
        try {
            // Get the project description
            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();

            // Add the nature to the list
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = BEL_NATURE;

            // Set the project description
            description.setNatureIds(newNatures);
            project.setDescription(description, null);
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }
}
