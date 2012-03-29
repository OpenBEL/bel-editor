package org.openbel.workbench.ui.interpreter;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;

public class BELMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

    public BELMainLaunchConfigurationTab(String mode) {
        super(mode);
    }

    @Override
    protected boolean breakOnFirstLinePrefEnabled(
            PreferencesLookupDelegate delegate) {
        return false;
    }

    @Override
    protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
        return false;
    }

    @Override
    public String getNatureID() {
        return BEL_NATURE;
    }

}
