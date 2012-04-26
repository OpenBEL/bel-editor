/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui;

import org.eclipse.dltk.compiler.task.ITodoTaskPreferences;
import org.eclipse.dltk.compiler.task.TodoTaskPreferencesOnPreferenceLookupDelegate;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractTodoTaskBuildParticipantType;

public class BELTODOParserType extends AbstractTodoTaskBuildParticipantType {

    @Override
    protected ITodoTaskPreferences getPreferences(IScriptProject project) {
        return new TodoTaskPreferencesOnPreferenceLookupDelegate(
                Activator.PLUGIN_ID, project);
    }
}
