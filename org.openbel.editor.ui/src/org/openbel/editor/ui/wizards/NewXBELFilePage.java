/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.wizards;

import static org.openbel.editor.core.Nature.BEL_NATURE;

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
