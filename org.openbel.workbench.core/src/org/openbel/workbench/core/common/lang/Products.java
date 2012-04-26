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
 * Denotes the products of a reaction.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * products(E:abundance...)products
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Products extends Function {

    /**
     * {@link Strings#PRODUCTS}
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
        NAME = Strings.PRODUCTS;
        ABBREVIATION = null;
        DESC = "Denotes the products of a reaction";
    }

    public Products() {
        super(NAME, ABBREVIATION, DESC, "products(F:abundance...)products");
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
