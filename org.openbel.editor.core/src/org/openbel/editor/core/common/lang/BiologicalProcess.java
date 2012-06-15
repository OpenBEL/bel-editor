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
 * Denotes a process or population of events.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * biologicalProcess(E:biologicalProcess)biologicalProcess
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class BiologicalProcess extends Function {

    /**
     * {@link Strings#BIOLOGICAL_PROCESS}
     */
    public final static String NAME;

    /**
     * {@link Strings#BIOLOGICAL_PROCESS_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.BIOLOGICAL_PROCESS;
        ABBREVIATION = Strings.BIOLOGICAL_PROCESS_ABBREV;
        DESC = "Denotes a process or population of events";
    }

    public BiologicalProcess() {
        super(NAME, ABBREVIATION, DESC,
                "biologicalProcess(E:biologicalProcess)biologicalProcess");
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
