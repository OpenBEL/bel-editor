/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core;

import static org.openbel.editor.core.Nature.BEL_NATURE;

import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;

/**
 * The core part of our DLTK based IDE. The entry point for all language
 * specific features.
 */
public class BELLanguageToolkit extends AbstractLanguageToolkit {
    private static BELLanguageToolkit toolkit;
    /** {@code org.openbel.editor.bel-content-type} */
    public final static String BEL_CONTENT_TYPE;
    /** {@code Biological Expression Language} */
    public final static String BEL_LANG_NAME;
    static {
        BEL_CONTENT_TYPE = "org.openbel.editor.bel-content-type";
        BEL_LANG_NAME = "Biological Expression Language";
    }

    /**
     * @return {@link IDLTKLanguageToolkit}
     */
    public static IDLTKLanguageToolkit getDefault() {
        if (toolkit == null) {
            toolkit = new BELLanguageToolkit();
        }
        return toolkit;
    }

    /**
     * Returns {@link #BEL_CONTENT_TYPE}.
     */
    @Override
    public String getLanguageContentType() {
        return BEL_CONTENT_TYPE;
    }

    /**
     * Returns {@link #BEL_LANG_NAME}.
     */
    @Override
    public String getLanguageName() {
        return BEL_LANG_NAME;
    }

    /**
     * Returns {@link Nature#BEL_NATURE}.
     */
    @Override
    public String getNatureId() {
        return BEL_NATURE;
    }

}
