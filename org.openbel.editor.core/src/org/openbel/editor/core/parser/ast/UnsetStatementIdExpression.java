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
 * Represents UNSET annotation id statement in a BEL script document.
 * <p>
 * Example:
 * 
 * <pre>
 * <code>
 * UNSET CellStructure
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#UNSET_ID}
 * </p>
 */
public class UnsetStatementIdExpression extends Expression {
    private ObjectIdentExpression annotationId;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.UNSET_ID;
    }

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (annotationId != null) {
            visitor.visit(annotationId);
        }
    }

    public ObjectIdentExpression getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(ObjectIdentExpression annotationId) {
        this.annotationId = annotationId;
    }

}
