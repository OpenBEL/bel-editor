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
 * Denotes the abundance of a molecular complex.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * complexAbundance(E:abundance)abundance
 * complexAbundance(F:abundance...)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class ComplexAbundance extends Function {

    /**
     * {@link Strings#COMPLEX_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#COMPLEX_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.COMPLEX_ABUNDANCE;
        ABBREVIATION = Strings.COMPLEX_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a molecular complex";
    }

    public ComplexAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "complexAbundance(E:abundance)complexAbundance",
                "complexAbundance(F:abundance...)complexAbundance");
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
