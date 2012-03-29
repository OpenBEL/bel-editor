package org.openbel.workbench.ui.interpreter;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

public class BELScriptInterpretersBlock extends InterpretersBlock {
    @Override
    protected AddScriptInterpreterDialog createInterpreterDialog(
            IEnvironment environment, IInterpreterInstall standin) {
        AddBELInterpreterDialog dialog = new AddBELInterpreterDialog(this,
                getShell(),
                ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()),
                standin);
        dialog.setEnvironment(environment);
        return dialog;
    }

    @Override
    protected String getCurrentNature() {
        return BEL_NATURE;
    }

}
