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

/**
 * Represent an invalid node as reported by ANTLR. The node type value is 0.
 */
public class InvalidNode extends ASTNode {
    private String error;

    public InvalidNode(String error) {
        this.error = error;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor arg0) throws Exception {

    }

    @Override
    public String toString() {
        return error;
    }

}
