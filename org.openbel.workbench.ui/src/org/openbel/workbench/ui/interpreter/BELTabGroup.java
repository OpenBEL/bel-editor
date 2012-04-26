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

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;
import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptCommonTab;

public class BELTabGroup extends AbstractLaunchConfigurationTabGroup {
    @Override
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        MainLaunchConfigurationTab main = new BELMainLaunchConfigurationTab(
                mode);
        ScriptArgumentsTab args = new ScriptArgumentsTab();
        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { main,
                args, new ScriptCommonTab(main) };
        setTabs(tabs);
    }
}
