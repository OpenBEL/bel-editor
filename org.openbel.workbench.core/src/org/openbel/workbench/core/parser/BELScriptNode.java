/**
 * 
 */
package org.openbel.workbench.core.parser;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.DLTKToken;

/**
 * BEL Script abstract syntax tree node.
 */
public class BELScriptNode extends ASTNode {

    /**
     * 
     */
    public BELScriptNode() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param token
     */
    public BELScriptNode(DLTKToken token) {
        super(token);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param start
     * @param end
     */
    public BELScriptNode(int start, int end) {
        super(start, end);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor arg0) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

