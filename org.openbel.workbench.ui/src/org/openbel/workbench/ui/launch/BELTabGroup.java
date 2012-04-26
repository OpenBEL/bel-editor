/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * In most cases, the tab group is not interested in the lifecycle calls (
 * {@link #setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)},
 * {@link #performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)}
 * , ...). When this is true, you should make your tab group extend
 * org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup. This abstract class
 * implements these 4 methods to simply loop through all owned tabs and make the
 * corresponding call on each.
 */
public class BELTabGroup extends AbstractLaunchConfigurationTabGroup {

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        ILaunchConfigurationTab _1 = new MainTab();

        // All tab groups should include CommonTab.
        // This tab contains UI that allows users to make their confs "local"
        CommonTab _2 = new CommonTab();
        // or "shared". By contention, it is the last tab in the group.

        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
            _1,
            _2
        };

        setTabs(tabs);
    }
}
