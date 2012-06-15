/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.parser;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.openbel.editor.core.parser.BELScript_v1Parser.document_return;

/**
 * BEL Script implementation of a source parser.
 */
public class BELScriptSourceParser extends AbstractSourceParser {

    private IProblemReporter pr;

    /**
     * {@inheritDoc}
     */
    @Override
    public IModuleDeclaration parse(IModuleSource src, IProblemReporter pr) {
        this.pr = pr;
        char[] content = src.getContentsAsCharArray();
        int length = content.length;
        BELScriptDocument d = new BELScriptDocument(length);
        String str = new String(content);
        ANTLRStringStream stream = new ANTLRStringStream(str);

        BELScript_v1Lexer lexer = new BELScript_v1Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BELScript_v1Parser parser = new BELScript_v1Parser(tokens);

        document_return DOC = null;
        try {
            DOC = parser.document();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

        CommonTree tree = (CommonTree) DOC.getTree();
        List<CommonTree> children = tree.getChildren();
        for (final CommonTree tree2 : children) {
            int k = 10;
        }

        /*
         * BELScriptProblem p = new BELScriptProblem("42.txt",
         * "Your answer is."); p.setWarning(true); arg1.reportProblem(p);
         */
        System.out.println("parsed document");
        here(DOC);
        return d;
    }

    private void here(document_return DOC) {
        int i = 0;
    }
}
