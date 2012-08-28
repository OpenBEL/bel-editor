package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class QuotedValue extends Expression {
    private String text;

    public QuotedValue(String value) {
        this.text = value;
    }

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
