package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class AnnotationSetField extends Expression {
    private Expression name;
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

    public Expression getName() {
        return name;
    }

    public void setName(Expression name) {
        this.name = name;
    }

    @Override
    public int getKind() {
        return BELScript_v1Parser.ANNO_SET_QV;
    }

    public QuotedValue getValue() {
        return value;
    }

    public void setValue(QuotedValue value) {
        this.value = value;
    }

}
