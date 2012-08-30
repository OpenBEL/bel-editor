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
 * Represents a quoted string value in a BEL Script document.
 * <p>
 * For example, the following BEL Script snippet uses the quoted value "U-937"
 * 
 * <pre>
 * <code>
 * SET CellLine = "U-937"
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#QUOTED_VALUE}
 * </p>
 */
public class QuotedValue extends Expression {
    private String text;

    /**
     * Default constructor.
     * 
     * @param value - the string value of this quoted value
     */
    public QuotedValue(String value) {
        this.text = value;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.QUOTED_VALUE;
    }

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            pVisitor.endvisit(this);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

}
