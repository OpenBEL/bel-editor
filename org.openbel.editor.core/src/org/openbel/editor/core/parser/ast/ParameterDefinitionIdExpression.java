package org.openbel.editor.core.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class ParameterDefinitionIdExpression extends Expression {
    private List<ObjectIdentExpression> objects = new ArrayList<ObjectIdentExpression>();

    @Override
    public int getKind() {
        return BELScript_v1Parser.PARAM_DEF_ID;
    }

    public void addObjects(ObjectIdentExpression obj) {
        objects.add(obj);
    }

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
