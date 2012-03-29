package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes a disease or pathology process.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * pathology(E:pathology)pathology
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Pathology extends Function {

    /**
     * {@link Strings#PATHOLOGY}
     */
    public final static String NAME;

    /**
     * {@link Strings#PATHOLOGY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.PATHOLOGY;
        ABBREVIATION = Strings.PATHOLOGY_ABBREV;
        DESC = "Denotes a disease or pathology process";
    }

    public Pathology() {
        super(NAME, ABBREVIATION, DESC,
                "pathology(E:pathology)pathology");
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
