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
 * Denotes the frequency or abundance of events in a reaction.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * reaction(F:reactants,F:products)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Reaction extends Function {

    /**
     * {@link Strings#REACTION}
     */
    public final static String NAME;

    /**
     * {@link Strings#REACTION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.REACTION;
        ABBREVIATION = Strings.REACTION_ABBREV;
        DESC = "Denotes the frequency or abundance of events in a reaction";
    }

    public Reaction() {
        super(NAME, ABBREVIATION, DESC,
                "reaction(F:reactants,F:products)abundance");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 2:
            return true;
        default:
            return false;
        }
    }
}
