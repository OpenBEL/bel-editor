/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.text;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

/**
 * This rule captures parameter expansions that are not covered by
 * DollarBraceCountingRule. That is, all dollar-prefixed variables including
 * special variables where encapsulation in braces is not necessary.
 */
public class DollarRule implements IRule {

    private final StringBuffer buffer = new StringBuffer();
    private final IToken defaultToken;
    private final IWordDetector detector;
    private final IToken successToken;

    public DollarRule(IWordDetector detector, IToken defaultToken, IToken token) {
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
        int c = scanner.read();
        if (detector.isWordStart((char) c)) {
            buffer.setLength(0);
            do {
                buffer.append((char) c);
                c = scanner.read();
                if ((buffer.length() == 1) && isSpecial((char) c))
                    return successToken;
            } while ((c != ICharacterScanner.EOF)
                    && detector.isWordPart((char) c));
            scanner.unread();
            if (buffer.length() > 1)
                return successToken;
            if (defaultToken.isUndefined())
                unreadBuffer(scanner);
            return defaultToken;
        }
        scanner.unread();
        return Token.UNDEFINED;
    }

    /**
     * Returns whether the specified character is a special parameter in BEL
     * script. A dollar sign combined with any of the following characters will
     * be highlighted, but no characters after that.
     * <p>
     * '*', '@' - positional parameters starting from 1<br>
     * '#' - number of positional parameters<br>
     * '?' - exit status of the last executed foreground command<br>
     * '-' - current option flags<br>
     * '$' - PID of the shell<br>
     * '!' - PID of the last executed background command<br>
     * '_' - pathname used to invoke the shell and every subsequent command <br>
     * '0' - usually the name of the file used to invoke the shell<br>
     * '1' to '9' - expands to the corresponding positional parameters,
     * parameters 10+ must be referenced with braces like ${12}
     * 
     * @param c
     *            a character to test
     * @return true if the specified character is a special parameter
     */
    protected boolean isSpecial(char c) {
        return ((c == '*') || (c == '@') || (c == '#') || (c == '?')
                || (c == '-') || (c == '$') || (c == '!') || (c == '_') || Character
                    .isDigit(c));
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
