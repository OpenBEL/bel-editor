package org.openbel.workbench.ui.interpreter;

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
