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
 * Denotes the frequency or abundance of events in which a member is degraded in
 * some way such that it is no longer a member.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * degradation(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Degradation extends Function {

    /**
     * {@link Strings#DEGRADATION}
     */
    public final static String NAME;

    /**
     * {@link Strings#DEGRADATION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.DEGRADATION;
        ABBREVIATION = Strings.DEGRADATION_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which a " +
                "member is degraded in some way such that it is no longer " +
                "a member";
    }

    public Degradation() {
        super(NAME, ABBREVIATION, DESC,
                "degradation(F:abundance)abundance");
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
