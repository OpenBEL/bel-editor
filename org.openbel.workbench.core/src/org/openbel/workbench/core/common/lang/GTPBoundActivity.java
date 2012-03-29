package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which a member of a G-protein
 * abundance is GTP-bound.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * gtpBoundActivity(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class GTPBoundActivity extends Function {

    /**
     * {@link Strings#GTP_BOUND_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#GTP_BOUND_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.GTP_BOUND_ACTIVITY;
        ABBREVIATION = Strings.GTP_BOUND_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which " +
                "a member of a G-protein abundance is GTP-bound";
    }

    public GTPBoundActivity() {
        super(NAME, ABBREVIATION, DESC,
                "gtpBoundActivity(F:complexAbundance)abundance",
                "gtpBoundActivity(F:proteinAbundance)abundance");
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