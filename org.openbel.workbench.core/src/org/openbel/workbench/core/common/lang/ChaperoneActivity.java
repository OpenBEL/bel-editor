package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which a member binds to some
 * substrate and acts as a chaperone for the substrate.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * chaperoneActivity(F:complexAbundance)abundance
 * chaperoneActivity(F:proteinAbundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class ChaperoneActivity extends Function {

    /**
     * {@link Strings#CHAPERONE_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#CHAPERONE_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.CHAPERONE_ACTIVITY;
        ABBREVIATION = Strings.CHAPERONE_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which a " +
                "member binds to some substrate and acts as a chaperone for " +
                "the substrate";
    }

    public ChaperoneActivity() {
        super(NAME, ABBREVIATION, DESC,
                "chaperoneActivity(F:complexAbundance)abundance",
                "chaperoneActivity(F:proteinAbundance)abundance");
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
