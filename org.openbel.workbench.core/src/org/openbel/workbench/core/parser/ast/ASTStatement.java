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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceRange;

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

    /**
     * Constructs a statement AST node.
     */
    public ASTStatement() {
        super();
    }

    /**
     * Constructs a statement AST node.
     * 
     * @param start
     * @param end
     */
    public ASTStatement(int start, int end) {
        super(start, end);
    }

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
        return 0;
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
    }

}
