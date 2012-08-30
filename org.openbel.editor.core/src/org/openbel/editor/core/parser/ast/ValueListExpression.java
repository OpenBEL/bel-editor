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
 * Represents a comma delimited lists of strings in a BEL script document.
 * <p>
 * In the snippet below the values list is {"PubMed",
 * "J Biol Chem. 1997 Jul 4;272(27):16917-23.","9202001"}
 * 
 * <pre>
 *  <code>
 * SET Citation = {"PubMed", "J Biol Chem. 1997 Jul 4;272(27):16917-23.","9202001"}
 *  </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#VALUE_LIST}
 * </p>
 */
public class ValueListExpression extends Expression {

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
        return BELScript_v1Parser.VALUE_LIST;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
