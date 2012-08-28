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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceRange;
import org.openbel.editor.core.parser.BELScript_v1Parser;

/**
 * Represents a statement in BEL Script.
 * <p>
 * The following is an example of a BEL statement represented by this AST type,
 * in the form of {@code term relationship term}.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class ASTStatement extends Statement {
    private TermDefinition leftTerm;
    private TermDefinition rightTerm;
    private RelationshipLiteral relationship;

    /**
     * {@inheritDoc}
     */
    @Override
    public String debugString() {
        return super.debugString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.STMTDEF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ISourceRange getSourceRange() {
        return super.getSourceRange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean locationMatches(ASTNode other) {
        return super.locationMatches(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        super.traverse(visitor);
        if (leftTerm != null) {
            leftTerm.traverse(visitor);
        }
        if (rightTerm != null) {
            rightTerm.traverse(visitor);
        }
        if (relationship != null) {
            relationship.traverse(visitor);
        }
    }

    public TermDefinition getLeftTerm() {
        return leftTerm;
    }

    public void setLeftTerm(TermDefinition leftTerm) {
        this.leftTerm = leftTerm;
    }

    public TermDefinition getRightTerm() {
        return rightTerm;
    }

    public void setRightTerm(TermDefinition rightTerm) {
        this.rightTerm = rightTerm;
    }

    public RelationshipLiteral getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipLiteral relationship) {
        this.relationship = relationship;
    }

}
