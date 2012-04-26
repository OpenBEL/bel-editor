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
 * Denotes the abundance of a gene.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * rnaAbundance(E:rnaAbundance)geneAbundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class RNAAbundance extends Function {

    /**
     * {@link Strings#RNA_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#RNA_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.RNA_ABUNDANCE;
        ABBREVIATION = Strings.RNA_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a gene";
    }

    public RNAAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "rnaAbundance(E:rnaAbundance)geneAbundance",
                "rnaAbundance(E:rnaAbundance,F:fusion)geneAbundance");
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
