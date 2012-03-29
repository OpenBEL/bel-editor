package org.openbel.workbench.ui.wizards;

import static java.lang.System.arraycopy;
import static org.eclipse.ui.internal.WorkbenchPlugin.getDefault;
import static org.openbel.workbench.core.CoreConstants.BUILDER_FILE_NAME;
import static org.openbel.workbench.core.Nature.BEL_NATURE;
import static org.openbel.workbench.core.common.BELUtilities.closeSilently;
import static org.openbel.workbench.core.common.BELUtilities.readable;
import static org.openbel.workbench.core.common.PathConstants.SYSCONFIG_FILENAME;
import static org.openbel.workbench.ui.Activator.BF_PREF_KEY;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.*;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.perspective.OpenBELWorkbenchPerspective;

public class NewProjectWizard extends Wizard implements INewWizard {

    private static final class CreateProject extends WorkspaceModifyOperation {
        private final String projectName;
        private IStatus status;

        private CreateProject(final String projectName) {
            this.projectName = projectName;
        }

        private void createFolder(IFolder folder) throws CoreException {
            if (!folder.exists()) {

                if (folder.getParent() instanceof IFolder) {
                    createFolder((IFolder) folder.getParent());
                }

                folder.create(false, true, new NullProgressMonitor());
            }
        }

        void createProject(IProgressMonitor m)
                throws CoreException {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject project = root.getProject(projectName);
            project.create(new NullProgressMonitor());
            project.open(new NullProgressMonitor());

            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();
            String[] newNatures = new String[natures.length + 1];
            arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = BEL_NATURE;
            description.setNatureIds(newNatures);
            project.setDescription(description, null);

            // create initial document group folder
            IFolder bels = project.getFolder("Documents");
            createFolder(bels);

            IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
            final StringBuilder b = new StringBuilder(ps.getString(BF_PREF_KEY))
                    .append(File.separator).append("config")
                    .append(File.separator).append(SYSCONFIG_FILENAME);

            final File configFile = new File(b.toString());
            if (!readable(configFile)) {
                throw new CoreException(Status.CANCEL_STATUS);
            }

            // create system configuration file read from belframework_home
            IFile syscfg = project.getFile(SYSCONFIG_FILENAME);

            try {
                syscfg.create(new FileInputStream(configFile), IResource.NONE,
                        new NullProgressMonitor());
            } catch (FileNotFoundException e) {
                // should not caught if previous file read check succeeds
                throw new CoreException(Status.CANCEL_STATUS);
            }

            // create builder
            IFile builder = project.getFile(BUILDER_FILE_NAME);
            String name = "/org/openbel/workbench/ui/builder.xml";
            InputStream is = getClass().getResourceAsStream(name);

            builder.create(is, IResource.NONE, new NullProgressMonitor());
            closeSilently(is);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final IProgressMonitor m) throws CoreException,
                InvocationTargetException, InterruptedException {
            try {
                createProject(m);
                status = Status.OK_STATUS;
            } catch (CoreException ce) {
                status = ce.getStatus();
            }
        }

        public IStatus getResult() {
            return status;
        }
    }

    private NewProjectWizardPage page;

    @SuppressWarnings("unused")
    private IStructuredSelection selection;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        page = new NewProjectWizardPage();
        addPage(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFinish() {
        return page.isPageComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        final String projectName = page.getProjectName();

        if (!(projectName.length() > 0)) {
            return false;
        }

        final CreateProject createOperation = new CreateProject(projectName);
        try {
            getContainer().run(false, false, createOperation);
        } catch (InvocationTargetException e) {
            Activator.logError(e);
            return false;
        } catch (InterruptedException e) {
            return false;
        }

        final IStatus status = createOperation.getResult();
        if (status.isOK()) {
            switchToPerspective();
            return true;
        }

        ErrorDialog.openError(getShell(), "Error Creating Project",
                status.getMessage(), status);
        return false;
    }

    private void switchToPerspective() {
        IWorkbenchWindow window = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow();

        IPerspectiveRegistry reg = getDefault().getPerspectiveRegistry();
        PerspectiveDescriptor rtPerspectiveDesc = (PerspectiveDescriptor) reg
                .findPerspectiveWithId(OpenBELWorkbenchPerspective.ID);

        // Now set it as the active perspective.
        if (window != null) {
            IWorkbenchPage page = window.getActivePage();
            page.setPerspective(rtPerspectiveDesc);
        }
    }
}
