package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which members are present.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * compositeAbundance(F:abundance...)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class CompositeAbundance extends Function {

    /**
     * {@link Strings#COMPOSITE_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#COMPOSITE_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.COMPOSITE_ABUNDANCE;
        ABBREVIATION = Strings.COMPOSITE_ABUNDANCE_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which " +
                "members are present";
    }

    public CompositeAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "compositeAbundance(F:abundance...)abundance");
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
