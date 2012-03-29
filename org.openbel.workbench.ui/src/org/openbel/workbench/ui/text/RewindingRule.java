package org.openbel.workbench.ui.text;

import static org.eclipse.jface.text.rules.ICharacterScanner.EOF;

import org.eclipse.jface.text.rules.*;

public class RewindingRule extends WordRule {
    private final StringBuilder bldr = new StringBuilder();

    /**
     * @param detector
     */
    public RewindingRule(IWordDetector detector) {
        super(detector);
    }

    /**
     * @param detector
     * @param defaultToken
     */
    public RewindingRule(IWordDetector detector, IToken defaultToken) {
        super(detector, defaultToken);
    }

    /**
     * @param detector
     * @param defaultToken
     * @param ignoreCase
     */
    public RewindingRule(IWordDetector detector, IToken defaultToken,
            boolean ignoreCase) {
        super(detector, defaultToken, ignoreCase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        IToken ret = Token.UNDEFINED;
        char c = (char) scanner.read();
        int read = 1;
        if (fDetector.isWordStart(c)) {
            if ((fColumn != UNDEFINED)
                    && (fColumn != (scanner.getColumn() - 1))) {
                unread(scanner, read);
                return ret;
            }
            bldr.setLength(0);
            do {
                bldr.append(c);
                c = (char) scanner.read();
                read++;
            } while ((c != EOF) && fDetector.isWordPart(c));

            ret = (IToken) fWords.get(bldr.toString());
            if (ret != null) return ret;
        }

        if (fDefaultToken != null) return fDefaultToken;
        return ret;
    }

    private static void unread(ICharacterScanner scanner, int number) {
        for (int i = 0; i < number; i++) {
            scanner.unread();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unreadBuffer(ICharacterScanner scanner) {
        for (int i = bldr.length() - 1; i >= 0; i--)
            scanner.unread();
    }

}
