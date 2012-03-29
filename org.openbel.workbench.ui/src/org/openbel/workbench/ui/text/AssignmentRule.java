package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public class AssignmentRule implements IRule {
    private final StringBuffer buffer = new StringBuffer();
    private final IToken defaultToken;
    private final IWordDetector detector;
    private final IToken successToken;

    public AssignmentRule(IWordDetector detector, IToken defaultToken,
            IToken token) {
        this.detector = detector;
        this.successToken = token;
        this.defaultToken = defaultToken;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules
     * .ICharacterScanner)
     */
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        char c = (char) scanner.read();
        if (detector.isWordStart((c))) {
            buffer.setLength(0);
            do {
                buffer.append((c));
                c = (char) scanner.read();
            } while ((c != ICharacterScanner.EOF) && detector.isWordPart(c));
            scanner.unread();
            if (c == '=')
                return successToken;
            if (defaultToken.isUndefined())
                unreadBuffer(scanner);
            return defaultToken;
        }
        scanner.unread();
        return Token.UNDEFINED;
    }

    /**
     * Returns the characters in the buffer to the scanner.
     * 
     * @param scanner
     *            the scanner to be used
     */
    protected void unreadBuffer(ICharacterScanner scanner) {
        for (int i = buffer.length() - 1; i >= 0; i--)
            scanner.unread();
    }
}
