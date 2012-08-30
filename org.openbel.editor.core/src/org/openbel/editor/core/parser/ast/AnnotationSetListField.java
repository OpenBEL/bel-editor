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
 * Represents the definition of an SET element within a BEL Script document
 * which contains one or more values. For example, the following BEL Script
 * snippet defines a <i>Citation</i> annotation.
 * 
 * <pre>
 * <code>
 *     SET Citation = {"PubMed", "J Biol Chem. 1997 Jul 4;272(27):16917-23.","9202001"}
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#ANNO_SET_LIST}
 * </p>
 */
public class AnnotationSetListField extends Expression {
    private ObjectIdentExpression objectIdent;
    private ValueListExpression valueList;

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (objectIdent != null) {
                objectIdent.traverse(pVisitor);
            }
            if (valueList != null) {
                valueList.traverse(pVisitor);
            }
            pVisitor.endvisit(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.ANNO_SET_LIST;
    }

    public ObjectIdentExpression getObjectIdent() {
        return objectIdent;
    }

    public void setObjectIdent(ObjectIdentExpression objectIdent) {
        this.objectIdent = objectIdent;
    }

    public ValueListExpression getValueList() {
        return valueList;
    }

    public void setValueList(ValueListExpression valueList) {
        this.valueList = valueList;
    }

}
