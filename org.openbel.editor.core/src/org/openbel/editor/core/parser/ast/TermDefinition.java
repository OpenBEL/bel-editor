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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

/**
 * Represents a BEL term definition in a BEL script document.
 * <p>
 * The term in the snippet below is composed of a relationship and a parameter
 * definition.
 * 
 * <pre>
 * <code>
 * p(HGNC:DUSP1)
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#TERMDEF}
 * </p>
 */
public class TermDefinition extends Expression {
    private TermDefinition termDefinition;
    private RelationshipLiteral relationshipLiteral;
    private ParameterDefinitionExpression parameterExpression;
    private List<ParameterDefinitionIdExpression> definitionIdExpressions = new ArrayList<ParameterDefinitionIdExpression>();

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.TERMDEF;
    }

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (termDefinition != null) {
                termDefinition.traverse(pVisitor);
            }
            if (relationshipLiteral != null) {
                relationshipLiteral.traverse(pVisitor);
            }
            if (parameterExpression != null) {
                parameterExpression.traverse(pVisitor);
            }
            for (ParameterDefinitionIdExpression expr : definitionIdExpressions) {
                if (expr != null) {
                    expr.traverse(pVisitor);
                }
            }
            pVisitor.endvisit(this);
        }
    }

    public void addParameterDefinitionIdExpression(
            ParameterDefinitionIdExpression expression) {
        definitionIdExpressions.add(expression);
    }

    public TermDefinition getTermDefinition() {
        return termDefinition;
    }

    public void setTermDefinition(TermDefinition termDefinition) {
        this.termDefinition = termDefinition;
    }

    public RelationshipLiteral getRelationshipLiteral() {
        return relationshipLiteral;
    }

    public void setRelationshipLiteral(RelationshipLiteral relationshipLiteral) {
        this.relationshipLiteral = relationshipLiteral;
    }

    public ParameterDefinitionExpression getParameterExpression() {
        return parameterExpression;
    }

    public void setParameterExpression(
            ParameterDefinitionExpression parameterExpression) {
        this.parameterExpression = parameterExpression;
    }

}
