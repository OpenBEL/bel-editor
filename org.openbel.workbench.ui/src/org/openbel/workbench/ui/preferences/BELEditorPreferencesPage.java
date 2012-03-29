package org.openbel.workbench.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.EditorConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.openbel.workbench.ui.Activator;

public class BELEditorPreferencesPage extends
        AbstractConfigurationBlockPreferencePage {

    @Override
    protected IPreferenceConfigurationBlock createConfigurationBlock(
            OverlayPreferenceStore overlayPreferenceStore) {
        return new EditorConfigurationBlock(this, overlayPreferenceStore);
    }

    @Override
    protected String getHelpId() {
        return null;
    }

    @Override
    protected void setDescription() {
        setDescription("BEL Script Editor preferences");
    }

    @Override
    protected void setPreferenceStore() {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }
}
