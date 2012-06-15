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
 * Indicates an abundance of proteins with truncation sequence variants.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * truncation(E:truncation)truncation
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Truncation extends Function {

    /**
     * {@link Strings#TRUNCATION}
     */
    public final static String NAME;

    /**
     * {@link Strings#TRUNCATION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.TRUNCATION;
        ABBREVIATION = Strings.TRUNCATION_ABBREV;
        DESC = "Indicates an abundance of proteins with truncation " +
                "sequence variants";
    }

    public Truncation() {
        super(NAME, ABBREVIATION, DESC,
                "truncation(E:*)truncation");
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
