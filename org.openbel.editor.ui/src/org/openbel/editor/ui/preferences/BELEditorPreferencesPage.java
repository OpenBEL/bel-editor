/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.EditorConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.openbel.editor.ui.Activator;

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
