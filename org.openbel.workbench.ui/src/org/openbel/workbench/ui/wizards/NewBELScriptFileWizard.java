package org.openbel.workbench.ui.wizards;

import static org.eclipse.dltk.ui.DLTKUIPlugin.getDefault;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

/**
 * Wizard for creating a new BEL script file.
 */
public class NewBELScriptFileWizard extends NewSourceModuleWizard {

    /**
     * {@link NewBELScriptFileWizard}
     */
    public NewBELScriptFileWizard() {
        setWindowTitle("New BEL Document Wizard");
        setDialogSettings(getDefault().getDialogSettings());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NewSourceModulePage createNewSourceModulePage() {
        return new NewBELScriptFilePage();
    }
}
