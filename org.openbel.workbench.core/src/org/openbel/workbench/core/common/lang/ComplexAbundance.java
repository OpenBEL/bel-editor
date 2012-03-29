package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the abundance of a molecular complex.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * complexAbundance(E:abundance)abundance
 * complexAbundance(F:abundance...)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class ComplexAbundance extends Function {

    /**
     * {@link Strings#COMPLEX_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#COMPLEX_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.COMPLEX_ABUNDANCE;
        ABBREVIATION = Strings.COMPLEX_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a molecular complex";
    }

    public ComplexAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "complexAbundance(E:abundance)complexAbundance",
                "complexAbundance(F:abundance...)complexAbundance");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validArgumentCount(int count) {
        if (count > 0) {
            return true;
        }
        return false;
    }
}
