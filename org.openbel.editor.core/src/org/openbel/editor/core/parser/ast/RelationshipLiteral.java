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

import org.eclipse.dltk.ast.expressions.Literal;
import org.openbel.editor.core.common.enums.RelationshipType;

/**
 * Represents a BEL relationship literal; from the predefined set of BEL
 * {@link RelationshipType relationships}.
 * <p>
 * This is analogous to one of the BEL language relationships within the context
 * of a statement in the BEL language. For example, the following BEL snippet
 * uses the relationship <i>directlyIncreases</i> to indicates causality between
 * two terms.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class RelationshipLiteral extends Literal {

    /**
     * Constructs a relationship literal AST node.
     * 
     * @param start
     * @param end
     */
    public RelationshipLiteral(int start, int end) {
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
