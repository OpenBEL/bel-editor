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

import static java.lang.Character.*;
import static org.openbel.editor.core.CoreConstants.BELSCRIPT_KEYWORDS;
import static org.openbel.editor.core.CoreConstants.FUNCTIONS;
import static org.openbel.editor.core.CoreConstants.RELATIONSHIPS;
import static org.openbel.editor.ui.UIConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.rules.IScriptWordDetector;
import org.eclipse.dltk.ui.text.rules.ScriptWordRule;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.*;

/**
 * The code scanner supports highlighting of syntactic elements. Scanners will
 * scan partitions and return color regions.
 */
public class BELCodeScanner extends AbstractScriptScanner {

    private static String fgTokenProperties[];
    static {
        fgTokenProperties = new String[] {
            HASH_COMMENT,
            FUNCTION,
            RELATIONSHIP,
            KEYWORD,
            OTHER
        };
    }

    /**
     * BEL code scanner.
     * 
     * @param manager Color manager
     * @param store Preference store
     */
    public BELCodeScanner(IColorManager manager, IPreferenceStore store) {
        super(manager, store);
        this.initialize();
    }

    @Override
    protected List<IRule> createRules() {
        List<IRule> rules = new ArrayList<IRule>();

        IToken keyword = this.getToken(KEYWORD);
        IToken comment = this.getToken(HASH_COMMENT);
        IToken other = this.getToken(OTHER);
        IToken relationship = this.getToken(RELATIONSHIP);
        IToken function = this.getToken(FUNCTION);

        rules.add(new WhitespaceRule(new WhitespaceDetector()));
        SingleLineRule slr = new SingleLineRule("#", null, comment);
        slr.setColumnConstraint(0);
        rules.add(slr);

        ScriptWordRule swr;

        swr = new ScriptWordRule(new FunctionDetector(),
                Token.UNDEFINED);
        for (String s : FUNCTIONS) {
            swr.addWord(s, function);
        }
        rules.add(swr);

        swr = new ScriptWordRule(new RelationshipDetector(), Token.UNDEFINED);
        for (String s : RELATIONSHIPS) {
            swr.addWord(s, relationship);
        }
        rules.add(swr);

        swr = new ScriptWordRule(new KeywordDetector(), Token.UNDEFINED);
        for (String s : BELSCRIPT_KEYWORDS) {
            swr.addWord(s, keyword);
        }
        rules.add(swr);

        this.setDefaultReturnToken(other);
        return rules;
    }

    @Override
    protected String[] getTokenProperties() {
        return fgTokenProperties;
    }

    /**
     * Detects BEL keywords.
     */
    private static class KeywordDetector implements IScriptWordDetector {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordPart(char c) {
            return isUpperCase(c) || (c == '_');
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordStart(char c) {
            return isUpperCase(c);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPriorCharValid(char c) {
            if (isLetterOrDigit(c)) return false;
            return true;
        }
    }

    /**
     * Detects BEL functions.
     */
    private static class FunctionDetector implements IScriptWordDetector {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordPart(char c) {
            return isLetter(c);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordStart(char c) {
            return isLowerCase(c);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPriorCharValid(char c) {
            if (isWhitespace(c)) return true;
            if (c == '(') return true;
            if (c == ',') return true;
            return false;
        }
    }

    /**
     * Detects BEL relationships.
     */
    private static class RelationshipDetector implements IScriptWordDetector {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordPart(char c) {
            if (isLetter(c)) return true;
            switch (c) {
            case '>':
            case '|':
            case '-':
                return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordStart(char c) {
            if (isLowerCase(c)) return true;
            switch (c) {
            case '=':
            case '>':
            case '-':
            case ':':
                return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPriorCharValid(char c) {
            if (c == ' ') return true;
            return false;
        }
    }
}
