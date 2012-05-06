/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Selventa - initial API and implementation
 */
package org.openbel.workbench.core.parser.ast;

import org.eclipse.dltk.ast.declarations.TypeDeclaration;

/**
 * Represents a BEL Script document in the OpenBEL Workbench AST.
 * <p>
 * This is analogous to a complete BEL Script document.
 * </p>
 */
public class ASTDocument extends TypeDeclaration {

    /**
     * Constructs a document AST node.
     * 
     * @param name
     * @param nameStart
     * @param nameEnd
     * @param start
     * @param end
     */
    public ASTDocument(String name, int nameStart, int nameEnd, int start,
            int end) {
        super(name, nameStart, nameEnd, start, end);
    }

}
