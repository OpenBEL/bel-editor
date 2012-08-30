<<<<<<< HEAD
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
 * Represents a field of a BEL Script document in the BEL Editor AST.
 * <p>
 * This is analogous to setting a BEL Script document property within the
 * context of the header of a BEL Script document. For example, the following
 * BEL Script snippet defines the required properties of a BEL Script document.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * SET DOCUMENT Description = "Description"
 * SET DOCUMENT Version = "1.0"
 * </code>
 * </pre>
 * 
 * </p>
 * *
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#DOCSET_QV}
 * </p>
 */
public class DocumentField extends Expression {
    private Keyword keyword;
    private QuotedValue value;

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (keyword != null) {
                keyword.traverse(pVisitor);
            }
            if (value != null) {
                value.traverse(pVisitor);
            }
            pVisitor.endvisit(this);
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.DOCSET_QV;
    }

    public QuotedValue getValue() {
        return value;
    }

    public void setValue(QuotedValue value) {
        this.value = value;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
=======
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
 * Represents a field of a BEL Script document in the BEL Editor AST.
 * <p>
 * This is analogous to setting a BEL Script document property within the
 * context of the header of a BEL Script document. For example, the following
 * BEL Script snippet defines the required properties of a BEL Script document.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * SET DOCUMENT Description = "Description"
 * SET DOCUMENT Version = "1.0"
 * </code>
 * </pre>
 * 
 * </p>
 */
public class DocumentField extends Expression {
    private Keyword keyword;
    private QuotedValue value;

    /**
     * @see org.eclipse.dltk.ast.ASTNode#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override
    public void traverse(ASTVisitor pVisitor) throws Exception {
        if (pVisitor.visit(this)) {
            if (getKeyword() != null) {
                getKeyword().traverse(pVisitor);
            }
            if (value != null) {
                value.traverse(pVisitor);
            }
            pVisitor.endvisit(this);
        }
    }

    @Override
    public int getKind() {
        return BELScript_v1Parser.DOCSET_QV;
    }

    public QuotedValue getValue() {
        return value;
    }

    public void setValue(QuotedValue value) {
        this.value = value;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
>>>>>>> 07f6eb5c27fbcf71e3d1d88cdbd500a1eee9ce6b
