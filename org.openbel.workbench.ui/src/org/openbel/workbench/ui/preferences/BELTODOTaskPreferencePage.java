/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.preferences;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.TodoTaskOptionsBlock;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.openbel.workbench.ui.Activator;

public class BELTODOTaskPreferencePage extends
        AbstractConfigurationBlockPropertyAndPreferencePage {

    @Override
    protected AbstractOptionsBlock createOptionsBlock(
            IStatusChangeListener newStatusChangedListener, IProject project,
            IWorkbenchPreferenceContainer container) {
        return new TodoTaskOptionsBlock(newStatusChangedListener, project,
                container, Activator.PLUGIN_ID);
    }

    @Override
    protected String getHelpId() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getNatureId() {
        return BEL_NATURE;
    }

    @Override
    protected String getPreferencePageId() {
        return "org.openbel.workbench.preferences.todo";
    }

    @Override
    protected String getProjectHelpId() {
        return null;
    }

    @Override
    protected String getPropertyPageId() {
        return "org.openbel.workbench.preferences.todo";
    }

    @Override
    protected void setDescription() {
        setDescription("Strings indicating tasks in BEL script comments.");
    }

    @Override
    protected void setPreferenceStore() {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

}
