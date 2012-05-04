/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.parser;

import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

/**
 * BEL Script implementation of a source parser.
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
