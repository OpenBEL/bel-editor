/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the frequency or abundance of events in which members of an abundance
 * move from cells to regions outside of the cells.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * cellSecretion(E:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class CellSecretion extends Function {

    /**
     * {@link Strings#CELL_SECRETION}
     */
    public final static String NAME;

    /**
     * {@link Strings#CELL_SECRETION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.CELL_SECRETION;
        ABBREVIATION = Strings.CELL_SECRETION_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which members "
                + "of an abundance move from cells to regions outside of the "
                + "cells";
    }

    public CellSecretion() {
        super(NAME, ABBREVIATION, DESC,
                "cellSecretion(F:abundance)abundance");
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
