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
 * Denotes the abundance of a protein.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * proteinAbundance(E:proteinAbundance)proteinAbundance
 * proteinAbundance(E:proteinAbundance,F:proteinModification)proteinAbundance
 * proteinAbundance(E:proteinAbundance,F:substitution)proteinAbundance
 * proteinAbundance(E:proteinAbundance,F:truncation)proteinAbundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class ProteinAbundance extends Function {

    /**
     * {@link Strings#PROTEIN_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#PROTEIN_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.PROTEIN_ABUNDANCE;
        ABBREVIATION = Strings.PROTEIN_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a protein";
    }

    public ProteinAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "proteinAbundance(E:proteinAbundance)proteinAbundance",
                "proteinAbundance(E:proteinAbundance,F:proteinModification)proteinAbundance",
                "proteinAbundance(E:proteinAbundance,F:substitution)proteinAbundance",
                "proteinAbundance(E:proteinAbundance,F:fusion)proteinAbundance",
                "proteinAbundance(E:proteinAbundance,F:truncation)proteinAbundance");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 1:
        case 2:
            return true;
        default:
            return false;
        }
    }
}
