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

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

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
public class SetExpression extends Expression {

    private Expression name;
    private String text;

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (name != null) {
                name.traverse(pVisitor);
            }
            pVisitor.endvisit(this);
        }
    }

    public Expression getName() {
        return name;
    }

    public void setName(Expression name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.ANNO_SET_QV;
    }

}
