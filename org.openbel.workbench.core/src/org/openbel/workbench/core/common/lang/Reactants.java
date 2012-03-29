package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the reactants of a reaction.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * reactants(E:abundance...)reactants
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Reactants extends Function {

    /**
     * {@link Strings#REACTANTS}
     */
    public final static String NAME;

    /**
     * {@code null}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.REACTANTS;
        ABBREVIATION = null;
        DESC = "Denotes the reactants of a reaction";
    }

    public Reactants() {
        super(NAME, ABBREVIATION, DESC, "reactants(F:abundance...)reactants");
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
