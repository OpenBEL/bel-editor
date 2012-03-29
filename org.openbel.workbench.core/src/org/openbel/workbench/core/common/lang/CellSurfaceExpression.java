package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which members of an abundance
 * move to the surface of cells.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * cellSurfaceExpression(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class CellSurfaceExpression extends Function {

    /**
     * {@link Strings#CELL_SURFACE_EXPRESSION}
     */
    public final static String NAME;

    /**
     * {@link Strings#CELL_SURFACE_EXPRESSION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.CELL_SURFACE_EXPRESSION;
        ABBREVIATION = Strings.CELL_SURFACE_EXPRESSION_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which " +
                "members of an abundance move to the surface of cells";
    }

    public CellSurfaceExpression() {
        super(NAME, ABBREVIATION, DESC,
                "cellSurfaceExpression(F:abundance)abundance");
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