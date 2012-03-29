package org.openbel.workbench.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.openbel.workbench.ui.Activator;

public class ColorPrefPage extends AbstractConfigurationBlockPreferencePage {

    @Override
    protected IPreferenceConfigurationBlock createConfigurationBlock(
            OverlayPreferenceStore overlayPreferenceStore) {
        return new ColorCfg(overlayPreferenceStore);
    }

    @Override
    protected String getHelpId() {
        return null;
    }

    @Override
    protected void setDescription() {
        // leave blank
    }

    @Override
    protected void setPreferenceStore() {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }
}
