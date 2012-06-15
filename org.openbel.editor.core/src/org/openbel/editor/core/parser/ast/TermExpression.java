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
package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.expressions.Expression;

/**
 * Represents a BEL term within the BEL Editor AST.
 * <p>
 * This is analogous to a term within the context of a statement occurring in a
 * BEL Script document. For example, the following BEL Script snippet contains
 * four unique terms.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class TermExpression extends Expression {

    /**
     * Constructs a term expression AST node.
     */
    public TermExpression() {
    }

    /**
     * Constructs a term expression AST node.
     * 
     * @param start
     * @param end
     */
    public TermExpression(int start, int end) {
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
