/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Selventa - initial API and implementation
 */
package org.openbel.workbench.core.parser.ast;

import org.eclipse.dltk.ast.expressions.Literal;
import org.openbel.workbench.core.common.lang.Function;

/**
 * Represents a BEL function literal; from the predefined set of BEL
 * {@link Function functions}.
 * <p>
 * This is analogous to one of the BEL language functions within the context of
 * a term in the BEL language. For example, the following BEL Script snippet
 * uses three unique BEL functions.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class FunctionLiteral extends Literal {

    /**
     * Constructs a function literal AST node.
     * 
     * @param start
     * @param end
     */
    public FunctionLiteral(int start, int end) {
        super(start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return 0;
    }

}
