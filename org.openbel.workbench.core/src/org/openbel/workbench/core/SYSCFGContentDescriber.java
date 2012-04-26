/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core;

import java.util.regex.Pattern;

import org.eclipse.dltk.core.ScriptContentDescriber;

/**
 * Used to analyze the file content (mostly for files without extensions). For
 * example, we could check for a BEL interpreter header:
 * {@code #/path/to/beli.sh} or {@code #C:\Path\To\beli.bat}
 */
public class SYSCFGContentDescriber extends ScriptContentDescriber {
    private static final Pattern[] patterns;
    static {
        patterns = new Pattern[1];
        patterns[0] = Pattern.compile(".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pattern[] getHeaderPatterns() {
        return patterns;
    }
}
