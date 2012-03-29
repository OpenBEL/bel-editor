package org.openbel.workbench.core;

import java.util.regex.Pattern;

import org.eclipse.dltk.core.ScriptContentDescriber;

/**
 * Used to analyze the file content (mostly for files without extensions). For
 * example, we could check for a BEL interpreter header:
 * {@code #/path/to/beli.sh} or {@code #C:\Path\To\beli.bat}
 */
public class BELContentDescriber extends ScriptContentDescriber {
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
