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
 * Denotes the frequency or abundance of events in which a member acts as a
 * causal agent at the molecular scale.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * molecularActivity(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class MolecularActivity extends Function {

    /**
     * {@link Strings#MOLECULAR_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#MOLECULAR_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.MOLECULAR_ACTIVITY;
        ABBREVIATION = Strings.MOLECULAR_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which a " +
                "member acts as a causal agent at the molecular scale";
    }

    public MolecularActivity() {
        super(NAME, ABBREVIATION, DESC,
                "molecularActivity(F:abundance)abundance");
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
