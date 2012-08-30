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
 * Represent an object name that is used in statements like DEFINE NAMESPACE or
 * SET.
 * <p>
 * In the example below, PFH and Species are ObjectIdentExpressions:
 * 
 * <pre>
 * <code>
 * DEFINE NAMESPACE PFH AS URL "http://resource.belframework.org/belframework/1.0/namespace/selventa-named-human-protein-families.belns"
 * SET Species = "9606"
 * </code>
 * </pre>
 * 
 * </p>
 * 
 * *
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#OBJECT_IDENT}
 * </p>
 */
public class ObjectIdentExpression extends Expression {
    /**
     * The name of the object.
     */
    private String name;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.OBJECT_IDENT;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
