/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.interpreter;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.swt.widgets.Shell;

public class AddBELInterpreterDialog extends AddScriptInterpreterDialog {
    public AddBELInterpreterDialog(IAddInterpreterDialogRequestor requestor,
            Shell shell, IInterpreterInstallType[] interpreterInstallTypes,
            IInterpreterInstall editedInterpreter) {
        super(requestor, shell, interpreterInstallTypes, editedInterpreter);
    }

    @Override
    protected AbstractInterpreterLibraryBlock createLibraryBlock(
            AddScriptInterpreterDialog dialog) {
        return new BELScriptInterpreterLibraryBlock(dialog);
    }
}
