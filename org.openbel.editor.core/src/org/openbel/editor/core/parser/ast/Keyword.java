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

/**
 * Represents a keyword used in a SET statement in a BEL Script document.
 * <p>
 * Keyword examples: <code>Name, Description, Version</code>
 * </p>
 * <p>
 * Usage: The following snippet uses the keyword <code>Name</code> in a SET
 * statement.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * </code>
 * </pre>
 * 
 * </p>
 */
public class Keyword extends Expression {
    /**
     * The name of the keyword itself.
     */
    private String text;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this)) {
            visitor.endvisit(this);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
