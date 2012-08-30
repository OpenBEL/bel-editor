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
 * Represents an annotation declaration using a list of strings within the BEL
 * Editor AST.
 * <p>
 * 
 * <pre>
 * <code>
 *  DEFINE ANNOTATION TextLocation AS  LIST {"Abstract","Results","Legend","Review"}
 * </code>
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#ANNO_DEF_LIST}
 * </p>
 */
public class AnnotationDefineListField extends Expression {
    private ObjectIdentExpression name;
    private ValueListExpression value;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.ANNO_DEF_LIST;
    }

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

    public ObjectIdentExpression getName() {
        return name;
    }

    public void setName(ObjectIdentExpression name) {
        this.name = name;
    }

    public ValueListExpression getValue() {
        return value;
    }

    public void setValue(ValueListExpression value) {
        this.value = value;
    }

}
