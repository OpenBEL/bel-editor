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
 * Denotes a disease or pathology process.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * pathology(E:pathology)pathology
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Pathology extends Function {

    /**
     * {@link Strings#PATHOLOGY}
     */
    public final static String NAME;

    /**
     * {@link Strings#PATHOLOGY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.PATHOLOGY;
        ABBREVIATION = Strings.PATHOLOGY_ABBREV;
        DESC = "Denotes a disease or pathology process";
    }

    public Pathology() {
        super(NAME, ABBREVIATION, DESC,
                "pathology(E:pathology)pathology");
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
