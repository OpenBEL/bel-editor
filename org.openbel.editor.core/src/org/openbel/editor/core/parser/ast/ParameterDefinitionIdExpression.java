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
 * Represents a parameter definition id in a BEL Script document.
 * <p>
 * For example, the following BEL Script snippet defines a parameter definition
 * id called HGNC with the value DUSP1. The second example defines two parameter
 * definition id elements, P and T with no values.
 * 
 * <pre>
 * <code>
 * p(HGNC:DUSP1)
 * pmod(P, T)
 * </code>
 * </pre>
 * 
 * </p>
 * *
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#PARAM_DEF_ID}
 * </p>
 */
public class ParameterDefinitionIdExpression extends Expression {
    private List<ObjectIdentExpression> objects = new ArrayList<ObjectIdentExpression>();

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.PARAM_DEF_ID;
    }

    public void addObjects(ObjectIdentExpression obj) {
        objects.add(obj);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this)) {
            if (objects != null) {
                for (ObjectIdentExpression object : objects) {
                    object.traverse(visitor);
                }
            }
            visitor.endvisit(this);
        }
    }

}
