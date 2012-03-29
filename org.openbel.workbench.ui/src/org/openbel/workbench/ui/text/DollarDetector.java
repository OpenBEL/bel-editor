package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Determines whether given any given character forms part of a BEL variable.
 */
public class DollarDetector implements IWordDetector {

    @Override
    public boolean isWordPart(char c) {
        return Character.isJavaIdentifierPart(c);
    }

    @Override
    public boolean isWordStart(char c) {
        return c == '$';
    }

}
