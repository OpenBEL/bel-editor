package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Indicates an abundance of proteins with truncation sequence variants.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * truncation(E:truncation)truncation
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Truncation extends Function {

    /**
     * {@link Strings#TRUNCATION}
     */
    public final static String NAME;

    /**
     * {@link Strings#TRUNCATION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.TRUNCATION;
        ABBREVIATION = Strings.TRUNCATION_ABBREV;
        DESC = "Indicates an abundance of proteins with truncation " +
                "sequence variants";
    }

    public Truncation() {
        super(NAME, ABBREVIATION, DESC,
                "truncation(E:*)truncation");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 1:
            return true;
        default:
            return false;
        }
    }
}
