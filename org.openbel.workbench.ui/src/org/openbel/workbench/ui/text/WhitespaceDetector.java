package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class WhitespaceDetector implements IWhitespaceDetector {

    /**
     * @see IWhitespaceDetector#isWhitespace
     */
    @Override
    public boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }
}
