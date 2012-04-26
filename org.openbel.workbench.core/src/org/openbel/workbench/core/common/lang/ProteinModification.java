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
 * Denotes a covalently modified protein abundance.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * proteinModification()proteinModification
 * proteinModification(E:phosphorylation)proteinModification
 * proteinModification(E:phosphorylation,E:phosphorylation)proteinModification
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class ProteinModification extends Function {

    /**
     * {@link Strings#PROTEIN_MODIFICATION}
     */
    public final static String NAME;

    /**
     * {@link Strings#MODIFICATION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.PROTEIN_MODIFICATION;
        ABBREVIATION = Strings.PROTEIN_MODIFICATION_ABBREV;
        DESC = "Denotes a covalently modified protein abundance";
    }

    public ProteinModification() {
        super(NAME, ABBREVIATION, DESC,
                "proteinModification(E:*)proteinModification",
                "proteinModification(E:*,E:*)proteinModification",
                "proteinModification(E:*,E:*,E:*)proteinModification");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 1:
        case 2:
        case 3:
            return true;
        default:
            return false;
        }
    }
}
