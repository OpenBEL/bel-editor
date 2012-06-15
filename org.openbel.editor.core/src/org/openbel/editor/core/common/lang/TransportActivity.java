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
 * Denotes the frequency or abundance of events in which a member directs acts
 * to enable the directed movement of substances into, out of, within, or
 * between cells.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * transportActivity(F:abundance)abundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class TransportActivity extends Function {

    /**
     * {@link Strings#TRANSPORT_ACTIVITY}
     */
    public final static String NAME;

    /**
     * {@link Strings#TRANSCRIPTIONAL_ACTIVITY_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.TRANSPORT_ACTIVITY;
        ABBREVIATION = Strings.TRANSPORT_ACTIVITY_ABBREV;
        DESC = "Denotes the frequency or abundance of events in which a " +
                "member directs acts to enable the directed movement of " +
                "substances into, out of, within, or between cells";
    }

    public TransportActivity() {
        super(NAME, ABBREVIATION, DESC,
                "transportActivity(F:complexAbundance)abundance",
                "transportActivity(F:proteinAbundance)abundance");
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
