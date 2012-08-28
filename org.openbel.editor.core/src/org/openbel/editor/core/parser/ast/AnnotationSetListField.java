package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class AnnotationSetListField extends Expression {
    private ObjectIdentExpression objectIdent;
    private ValueListExpression valueList;

    public AnnotationSetListField() {
    }

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
