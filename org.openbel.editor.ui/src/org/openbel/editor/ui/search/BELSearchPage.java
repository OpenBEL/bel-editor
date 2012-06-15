/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.search;

import static org.openbel.editor.core.BELLanguageToolkit.getDefault;

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
