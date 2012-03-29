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
