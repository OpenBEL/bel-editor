/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

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
