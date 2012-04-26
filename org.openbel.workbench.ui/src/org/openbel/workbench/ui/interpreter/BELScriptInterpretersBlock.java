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
