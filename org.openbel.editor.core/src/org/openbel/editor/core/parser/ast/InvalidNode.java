package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class InvalidNode extends ASTNode {
    private String error;

    public InvalidNode(String error) {
        this.error = error;
    }

    @Override
    public void traverse(ASTVisitor arg0) throws Exception {

    }

    @Override
    public String toString() {
        return error;
    }

}
