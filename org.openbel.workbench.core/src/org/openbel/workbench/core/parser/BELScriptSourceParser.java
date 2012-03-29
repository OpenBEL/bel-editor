package org.openbel.workbench.core.parser;

import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

/**
 * 
 */
public class BELScriptSourceParser extends AbstractSourceParser {

    /**
     * {@inheritDoc}
     */
    @Override
    public IModuleDeclaration parse(IModuleSource src, IProblemReporter pr) {
        /*
         * BELScriptProblem p = new BELScriptProblem("42.txt",
         * "Your answer is."); p.setWarning(true); arg1.reportProblem(p);
         */
        return null;
    }
}
