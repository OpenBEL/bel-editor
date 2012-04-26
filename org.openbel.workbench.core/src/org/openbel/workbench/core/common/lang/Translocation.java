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
 * Denotes the frequency or abundance of events in which members move between
 * locations.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * translocation(F:abundance,E:translocation,E:translocation)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Translocation extends Function {

    /**
     * {@link Strings#TRANSLOCATION}
     */
    public final static String NAME;

    /**
     * {@link Strings#TRANSLOCATION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.TRANSLOCATION;
        ABBREVIATION = Strings.TRANSLOCATION_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which " +
                "members move between locations";
    }

    public Translocation() {
        super(NAME, ABBREVIATION, DESC,
                "translocation(F:abundance,E:abundance,E:abundance)abundance");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 3:
            return true;
        default:
            return false;
        }
    }
}
