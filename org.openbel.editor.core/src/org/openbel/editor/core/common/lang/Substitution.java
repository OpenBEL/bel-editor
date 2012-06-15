/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.common.lang;

import org.openbel.editor.core.common.Strings;

/**
 * Indicates the abundance of proteins with amino acid substitution sequence.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * substitution(E:substitution,E:substitution,E:substitution)substitution
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Substitution extends Function {

    /**
     * {@link Strings#SUBSTITUTION}
     */
    public final static String NAME;

    /**
     * {@link Strings#SUBSTITUTION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.SUBSTITUTION;
        ABBREVIATION = Strings.SUBSTITUTION_ABBREV;
        DESC = "Indicates the abundance of proteins with amino acid " +
                "substitution sequence";
    }

    public Substitution() {
        super(NAME, ABBREVIATION, DESC,
                "substitution(E:*,E:*,E:*)substitution");
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
