package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events where a member acts as an
 * enzymatic catalyst of biochecmial reactions.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * catalyticActivity(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class CatalyticActivity extends Function {

    /**
     * {@link Strings#CATALYTIC_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#CATALYTIC_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.CATALYTIC_ACTIVITY;
        ABBREVIATION = Strings.CATALYTIC_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events where a member " +
                "acts as an enzymatic catalyst of biochecmial reactions";
    }

    public CatalyticActivity() {
        super(NAME, ABBREVIATION, DESC,
                "catalyticActivity(F:complexAbundance)abundance",
                "catalyticActivity(F:proteinAbundance)abundance");
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