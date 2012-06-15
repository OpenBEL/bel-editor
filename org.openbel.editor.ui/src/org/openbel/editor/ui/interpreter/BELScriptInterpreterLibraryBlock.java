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
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;

public class BELScriptInterpreterLibraryBlock extends
        AbstractInterpreterLibraryBlock {
    public BELScriptInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
        super(d);
    }

    @Override
    protected IBaseLabelProvider getLabelProvider() {
        return new LibraryLabelProvider();
    }
}
