package org.openbel.workbench.ui.wizards;

import static org.eclipse.dltk.ui.DLTKUIPlugin.getDefault;

import java.util.List;

import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.ui.wizards.ISourceModuleWizardExtension;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

/**
 * Wizard for creating a new XBEL file.
 */
public class NewXBELFileWizard extends NewSourceModuleWizard {

    /**
     * {@link NewXBELFileWizard}
     */
    public NewXBELFileWizard() {
        setWindowTitle("New XBEL Document Wizard");
        setDialogSettings(getDefault().getDialogSettings());
    }

    @Override
    public IEnvironment getEnvironment() {
        // TODO Auto-generated method stub
        return super.getEnvironment();
    }

    @Override
    protected List<ISourceModuleWizardExtension> getExtensions() {
        // TODO Auto-generated method stub
        return super.getExtensions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NewSourceModulePage createNewSourceModulePage() {
        return new NewXBELFilePage();
    }
}