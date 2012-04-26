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
 * Denotes the abundance of a processed, functional microRNA.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * microRNAAbundance(E:microRNAAbundance)microRNAAbundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class MicroRNAAbundance extends Function {

    /**
     * {@link Strings#MICRO_RNA_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#MICRO_RNA_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.MICRO_RNA_ABUNDANCE;
        ABBREVIATION = Strings.MICRO_RNA_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a processed, functional microRNA";
    }

    public MicroRNAAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "microRNAAbundance(E:microRNAAbundance)microRNAAbundance");
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
