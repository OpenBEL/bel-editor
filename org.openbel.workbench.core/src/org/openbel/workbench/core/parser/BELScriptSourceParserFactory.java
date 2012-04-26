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

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the BEL script source parser.
 */
public class BELScriptSourceParserFactory implements ISourceParserFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public ISourceParser createSourceParser() {
        return new BELScriptSourceParser();
    }

}
