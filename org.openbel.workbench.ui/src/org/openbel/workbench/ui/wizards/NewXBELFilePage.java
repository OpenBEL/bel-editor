package org.openbel.workbench.ui.wizards;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public class NewXBELFilePage extends NewSourceModulePage {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPageDescription() {
        return "Create a new XBEL document.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPageTitle() {
        return "New XBEL Document";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getFileExtensions() {
        return new String[] { "xbel" };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRequiredNature() {
        return BEL_NATURE;
    }
}
