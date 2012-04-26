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
 * Denotes a list of terms.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * list(E:abundance)list
 * </pre>
 * 
 * </p>
 * 
 * @author Tat Chu
 * @see Signature
 */
public class TermList extends Function {

    /**
     * {@link Strings#LIST}
     */
    public final static String NAME;

    /**
     * {@code null}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.LIST;
        ABBREVIATION = null;
        DESC = "Groups a list of terms together";
    }

    public TermList() {
        super(NAME, ABBREVIATION, DESC,
                "list(E:abundance...)list",
                "list(F:abundance...)list");
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
