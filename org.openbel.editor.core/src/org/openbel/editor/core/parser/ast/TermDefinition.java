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
    private static final String CLOSE_BRACKET = ")";
    private static final String OPEN_BRACKET = "(";
    private static final String ITEM_SEPARATOR = ", ";
    private List<TermDefinition> termDefinition = new ArrayList<TermDefinition>();
    private RelationshipLiteral relationshipLiteral;
    private List<ParameterDefinitionExpression> parameterExpression = new ArrayList<ParameterDefinitionExpression>();
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

            for (TermDefinition expr : termDefinition) {
                if (expr != null) {
                    expr.traverse(pVisitor);
                }
            }

            if (relationshipLiteral != null) {
                relationshipLiteral.traverse(pVisitor);
            }

            for (ParameterDefinitionExpression expr : parameterExpression) {
                if (expr != null) {
                    expr.traverse(pVisitor);
                }
            }
            for (ParameterDefinitionIdExpression expr : definitionIdExpressions) {
                if (expr != null) {
                    expr.traverse(pVisitor);
                }
            }
            pVisitor.endvisit(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder parameterExpressionSb = new StringBuilder();
        StringBuilder termDefinitionSb = new StringBuilder();
        StringBuilder definitionIdExpressionsSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterExpression.size(); i++) {
            parameterExpressionSb.append(parameterExpression.get(i)
                    .toString());
            if (i < parameterExpression.size() - 1) {
                parameterExpressionSb.append(ITEM_SEPARATOR);
            }
        }

        for (int i = 0; i < termDefinition.size(); i++) {
            termDefinitionSb.append(termDefinition.get(i)
                    .toString());
            if (i < termDefinition.size() - 1) {
                termDefinitionSb.append(ITEM_SEPARATOR);
            }
        }

        for (int i = 0; i < definitionIdExpressions.size(); i++) {
            definitionIdExpressionsSb.append(definitionIdExpressions.get(i)
                    .toString());
            if (i < definitionIdExpressions.size() - 1) {
                definitionIdExpressionsSb.append(ITEM_SEPARATOR);
            }
        }

        sb.append(relationshipLiteral.toString());
        if (parameterExpressionSb.toString().length() != 0) {
            sb.append(OPEN_BRACKET);
            sb.append(parameterExpressionSb);
            if (termDefinitionSb.toString().length() != 0) {
                sb.append(ITEM_SEPARATOR);
                sb.append(termDefinitionSb);
            }
            sb.append(CLOSE_BRACKET);
        } else if (definitionIdExpressionsSb.length() != 0) {
            sb.append(OPEN_BRACKET);
            sb.append(definitionIdExpressionsSb);

            if (termDefinitionSb.toString().length() != 0) {
                sb.append(ITEM_SEPARATOR);
                sb.append(termDefinitionSb);
            }
            sb.append(CLOSE_BRACKET);
        }
        if (termDefinitionSb.toString().length() != 0
                && parameterExpressionSb.toString().length() == 0
                && definitionIdExpressionsSb.length() == 0) {
            sb.append(OPEN_BRACKET);
            sb.append(termDefinitionSb);
            sb.append(CLOSE_BRACKET);
        }

        return sb.toString();
    }

    public List<TermDefinition> getTermDefinition() {
        return termDefinition;
    }

    public void setTermDefinition(List<TermDefinition> termDefinition) {
        this.termDefinition = termDefinition;
    }

    public List<ParameterDefinitionExpression> getParameterExpression() {
        return parameterExpression;
    }

    public void setParameterExpression(
            List<ParameterDefinitionExpression> parameterExpression) {
        this.parameterExpression = parameterExpression;
    }

    public void addParameterDefinitionIdExpression(
            ParameterDefinitionIdExpression expression) {
        definitionIdExpressions.add(expression);
    }

    public RelationshipLiteral getRelationshipLiteral() {
        return relationshipLiteral;
    }

    public void setRelationshipLiteral(RelationshipLiteral relationshipLiteral) {
        this.relationshipLiteral = relationshipLiteral;
    }

}
