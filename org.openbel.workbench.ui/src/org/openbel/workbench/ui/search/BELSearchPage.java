package org.openbel.workbench.ui.search;

import static org.openbel.workbench.core.BELLanguageToolkit.getDefault;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.search.ScriptSearchPage;

public class BELSearchPage extends ScriptSearchPage {

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDLTKLanguageToolkit getLanguageToolkit() {
        return getDefault();
    }
}
