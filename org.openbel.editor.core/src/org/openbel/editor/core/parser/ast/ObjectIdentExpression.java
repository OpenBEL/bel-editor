package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class ObjectIdentExpression extends Expression {
    private String name;

    @Override
    public int getKind() {
        return BELScript_v1Parser.OBJECT_IDENT;
    }

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
