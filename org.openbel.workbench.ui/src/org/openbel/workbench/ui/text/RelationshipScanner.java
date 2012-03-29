package org.openbel.workbench.ui.text;

import static java.lang.Character.isLetter;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static org.openbel.workbench.ui.UIConstants.*;

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
public class RelationshipScanner extends AbstractScriptScanner {

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
     * The set of BEL keywords.
     */
    public static String[] KEYWORDS = {
            "SET", "DEFINE", "UNSET", "DOCUMENT",
            "ANNOTATION", "NAMESPACE", "AS", "URL",
            "PATTERN", "LIST", "STATEMENT_GROUP",
            "DEFAULT"
    };

    public static String[] FUNCTIONS = {
            "a", "abundance", "act", "biologicalProcess", "bp",
            "cat", "catalyticActivity", "cellSecretion",
            "cellSurfaceExpression", "chap",
            "chaperoneActivity", "complex", "complexAbundance", "composite",
            "compositeAbundance", "deg", "degradation", "fus", "fusion", "g",
            "geneAbundance", "gtp", "gtpBoundActivity", "kin",
            "kinaseActivity", "list", "m", "microRNAAbundance",
            "molecularActivity", "p", "path", "pathology", "pep",
            "peptidaseActivity", "phos", "phosphataseActivity", "pmod",
            "products", "proteinAbundance", "proteinModification", "r",
            "reactants", "reaction", "ribo", "ribosylationActivity",
            "rnaAbundance", "rxn", "sec", "sub", "substitution", "surf",
            "tloc", "tport", "transcriptionalActivity", "translocation",
            "transportActivity", "trunc", "truncation", "tscript"
    };

    public static String[] RELATIONSHIPS = {
            "=>", "=|", ">>", "->", "-|",
            "--", ":>", "analogous", "association", "biomarkerFor",
            "causesNoChange", "decreases", "directlyDecreases",
            "directlyIncreases", "hasComponent", "hasComponents", "hasMember",
            "hasMembers", "increases", "isA", "negativeCorrelation",
            "orthologous", "positiveCorrelation", "prognosticBiomarkerFor",
            "rateLimitingStepOf", "subProcessOf", "transcribedTo",
            "translatedTo"
    };

    /**
     * BEL code scanner.
     * 
     * @param manager
     *            Color manager
     * @param store
     *            Preference store
     */
    public RelationshipScanner(IColorManager manager, IPreferenceStore store) {
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

        ScriptWordRule swr = new ScriptWordRule(new FunctionDetector(), other);
        for (String s : FUNCTIONS) {
            swr.addWord(s, function);
        }
        rules.add(swr);

        swr = new ScriptWordRule(new RelationshipDetector(), other);
        for (String s : RELATIONSHIPS) {
            swr.addWord(s, relationship);
        }
        rules.add(swr);

        swr = new ScriptWordRule(new KeywordDetector(), other);
        for (String s : KEYWORDS) {
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
     * Whitespace detector.
     */
    private static class WhitespaceDetector implements IWhitespaceDetector {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWhitespace(char c) {
            return Character.isWhitespace(c);
        }
    }

    /**
     * Detects BEL keywords.
     */
    private static class KeywordDetector implements IWordDetector {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordPart(char c) {
            System.out.print(c);
            return isUpperCase(c) || (c == '_');
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordStart(char c) {
            System.out.print("\nKeyword: " + c);
            return isUpperCase(c);
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
            System.out.print(c);
            boolean ret = isLetter(c);
            if (!ret) System.out.println(" (false)");
            return ret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isWordStart(char c) {
            System.out.print("\nFunction: " + c);
            return isLowerCase(c);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPriorCharValid(char c) {
            return true;
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
            System.out.print(c);
            if (isLowerCase(c)) return true;
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
            System.out.print("\nRelationship: " + c);
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
