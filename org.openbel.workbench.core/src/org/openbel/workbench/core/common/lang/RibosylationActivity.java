package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which a member acts to
 * perform post-translational modification of proteins.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * ribosylationActivity(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class RibosylationActivity extends Function {

    /**
     * {@link Strings#RIBOSYLATION_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#RIBOSYLATION_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.RIBOSYLATION_ACTIVITY;
        ABBREVIATION = Strings.RIBOSYLATION_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which a " +
                "member acts to perform post-translational modification of " +
                "proteins";
    }

    public RibosylationActivity() {
        super(NAME, ABBREVIATION, DESC,
                "ribosylationActivity(F:complexAbundance)abundance",
                "ribosylationActivity(F:proteinAbundance)abundance");
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
