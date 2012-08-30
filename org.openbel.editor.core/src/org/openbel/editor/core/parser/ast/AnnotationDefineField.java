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
 * Represents the definition of an annotation within a BEL Script document.
 * <p>
 * This field is analogous to the definition of an annotation within the context
 * of a BEL Script document. For example, the following BEL Script snippet
 * defines a <i>Species</i> annotation.
 * 
 * <pre>
 * <code>
 * DEFINE ANNOTATION Species AS URL "http://internal/bel-annotations/species"
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#ANNO_DEF_URL}
 * </p>
 */
public class AnnotationDefineField extends Expression {

    private ObjectIdentExpression name;
    private QuotedValue value;

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (name != null) {
                name.traverse(pVisitor);
            }
            if (value != null) {
                value.traverse(pVisitor);
            }
            pVisitor.endvisit(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.ANNO_DEF_URL;
    }

    public QuotedValue getValue() {
        return value;
    }

    public void setValue(QuotedValue value) {
        this.value = value;
    }

    public ObjectIdentExpression getName() {
        return name;
    }

    public void setName(ObjectIdentExpression name) {
        this.name = name;
    }

}
