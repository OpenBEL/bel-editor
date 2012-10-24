package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

public class StatementComment extends Expression {
    private String comment;

    @Override
    public int getKind() {
        return BELScript_v1Parser.STATEMENT_COMMENT;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
