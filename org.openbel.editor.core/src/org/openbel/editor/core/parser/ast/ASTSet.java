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
 * Represents the use of a defined annotation within the current context of the
 * AST.
 * <p>
 * This is analogous to the {@code SET} statement within the context of the body
 * of a BEL Script document. For example, the following BEL Script snippet
 * applies the <i>species</i> annotation with a value of {@code 9606} to a BEL
 * statement.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * DEFINE ANNOTATION Species AS URL "http://internal/bel-annotations/species"
 * SET Species = "9606"
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class ASTSet extends Expression {

    /**
     * Constructs a set AST node.
     */
    public ASTSet() {
    }

    /**
     * Constructs a set AST node.
     * 
     * @param start
     * @param end
     */
    public ASTSet(int start, int end) {
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
