package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.PatternRule;

/**
 * This rule captures a pattern that begins with '$' and an opening brace and
 * ends with a closed brace. This is used for highlighting ${} or $() blocks. It
 * counts the opening and closing braces as it encounters them to make sure we
 * always end at the correct closing brace or the end of the line, which ever
 * comes first.
 */
public class DollarBraceCountingRule extends PatternRule {

    private int fBraceCount;
    private final char fClosing;

    private char[][] fLineDelimiters;
    private final char fOpening;
    private char[][] fSortedLineDelimiters;

    /**
     * Creates a rule for the given opening and closing braces. When these
     * braces are detected the rule will return the specified token.
     * Alternatively, the sequence can also be ended by the end of the line. Any
     * character which follows the given escapeCharacter will be ignored.
     * 
     * @param openingBrace
     *            the pattern's start sequence
     * @param closingBrace
     *            the pattern's end sequence, <code>null</code> is a legal value
     * @param token
     *            the token which will be returned on success
     * @param escapeCharacter
     *            any character following this one will be ignored
     */
    public DollarBraceCountingRule(char openingBrace, char closingBrace,
            IToken token, char escapeChar) {
        super("$" + Character.toString(openingBrace), Character
                .toString(closingBrace), token, escapeChar, true, false, false);
        fOpening = openingBrace;
        fClosing = closingBrace;
    }

    /**
     * This method is rather more or less identical to the super class
     * implementation with the exception that instead of checking for the end
     * sequence, it counts occurrences of braces and returns successfully when
     * the number of closing braces encountered equals the number of opening
     * braces.
     * 
     * @see org.eclipse.jface.text.rules.PatternRule#endSequenceDetected(org.eclipse.jface.text.rules.ICharacterScanner)
     */
    @Override
    protected boolean endSequenceDetected(ICharacterScanner scanner) {
        char[][] originalDelimiters = scanner.getLegalLineDelimiters();
        int count = originalDelimiters.length;
        if ((fLineDelimiters == null) || (originalDelimiters.length != count)) {
            fSortedLineDelimiters = new char[count][];
        } else {
            while ((count > 0)
                    && (fLineDelimiters[count - 1] == originalDelimiters[count - 1]))
                count--;
        }
        if (count != 0) {
            fLineDelimiters = originalDelimiters;
            System.arraycopy(fLineDelimiters, 0, fSortedLineDelimiters, 0,
                    fLineDelimiters.length);
        }

        int readCount = 1;
        int c;
        fBraceCount = 1;
        while ((c = scanner.read()) != ICharacterScanner.EOF) {
            if (c == fEscapeCharacter) {
                // Skip escaped character(s)
                if (fEscapeContinuesLine) {
                    c = scanner.read();
                    for (char[] fSortedLineDelimiter : fSortedLineDelimiters) {
                        if ((c == fSortedLineDelimiter[0])
                                && sequenceDetected(scanner,
                                        fSortedLineDelimiter, true))
                            break;
                    }
                } else
                    scanner.read();

            } else if (c == fOpening) {
                // Count opening braces
                fBraceCount++;
            } else if (c == fClosing) {
                // Count closing braces
                fBraceCount--;
                // End successfully when the open brace count returns to zero
                if (fBraceCount == 0)
                    return true;
            } else if (fBreaksOnEOL) {
                // Check for end of line since it can be used to terminate the
                // pattern.
                for (char[] fSortedLineDelimiter : fSortedLineDelimiters) {
                    if ((c == fSortedLineDelimiter[0])
                            && sequenceDetected(scanner, fSortedLineDelimiter,
                                    true))
                        return true;
                }
            }
            readCount++;
        }

        if (fBreaksOnEOF)
            return true;

        for (; readCount > 0; readCount--)
            scanner.unread();

        return false;
    }
}
