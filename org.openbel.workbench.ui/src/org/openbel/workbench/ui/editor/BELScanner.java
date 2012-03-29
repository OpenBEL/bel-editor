package org.openbel.workbench.ui.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

/**
 * 
 * @author julianray
 * 
 */
public class BELScanner extends RuleBasedScanner {

    /**
     * 
     * @param colorManager
     */
    public BELScanner(BELColorManager colorManager) {

        WordRule wordRules = new WordRule(new IWordDetector() {
            @Override
            public boolean isWordStart(char c) {
                return Character.isJavaIdentifierStart(c) || (c == '=')
                        || (c == ':') || (c == '-') || (c == '>');
            }

            @Override
            public boolean isWordPart(char c) {
                return Character.isJavaIdentifierPart(c) || (c == '>')
                        || (c == '|');
            }
        });

        Token controlWordToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.CONTROL_WORD), null,
                SWT.BOLD));
        Token objectWordToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.OBJECT_WORD), null,
                SWT.BOLD));
        Token functionToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.BEL_WORD), null,
                SWT.BOLD));
        Token relationshipToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.BEL_RELATIONSHIP),
                null, SWT.BOLD));

        Token commentToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.BEL_COMMENT), null,
                SWT.ITALIC));
        Token stringToken = new Token(new TextAttribute(
                colorManager.getColor(IBELColorConstants.STRING)));

        // add tokens for each reserved word
        for (String word : BELGrammar.controlWords) {
            wordRules.addWord(word, controlWordToken);
        }
        for (String word : BELGrammar.objectWords) {
            wordRules.addWord(word, objectWordToken);
        }
        for (String function : BELGrammar.belFunctions) {
            wordRules.addWord(function, functionToken);
        }
        for (String word : BELGrammar.relationshipWords) {
            wordRules.addWord(word, relationshipToken);
        }

        setRules(new IRule[] { wordRules,

        new SingleLineRule("#", null, commentToken),
                new SingleLineRule("//", null, commentToken),

                new SingleLineRule("\"", "\"", stringToken, '\\'),
                new SingleLineRule("'", "'", stringToken, '\\'),

                new WhitespaceRule(new IWhitespaceDetector() {
                    @Override
                    public boolean isWhitespace(char c) {
                        return Character.isWhitespace(c);
                    }
                }), });
    }
}
