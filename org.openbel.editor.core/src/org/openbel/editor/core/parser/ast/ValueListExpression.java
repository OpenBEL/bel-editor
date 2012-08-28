package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class ValueListExpression extends Expression {

    private String text;

    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        if (visitor.visit(this)) {
            visitor.endvisit(this);
        }
    }

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
