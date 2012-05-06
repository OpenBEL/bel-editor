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

import org.eclipse.dltk.ast.declarations.FieldDeclaration;

/**
 * Represents a field of a BEL Script document in the OpenBEL Workbench AST.
 * <p>
 * This is analogous to setting a BEL Script document property within the
 * context of the header of a BEL Script document. For example, the following
 * BEL Script snippet defines the required properties of a BEL Script document.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * SET DOCUMENT Description = "Description"
 * SET DOCUMENT Version = "1.0"
 * </code>
 * </pre>
 * 
 * </p>
 */
public class DocumentField extends FieldDeclaration {

    /**
     * Constructs a document field AST node.
     * 
     * @param name
     * @param nameStart
     * @param nameEnd
     * @param declStart
     * @param declEnd
     */
    public DocumentField(String name, int nameStart, int nameEnd,
            int declStart, int declEnd) {
        super(name, nameStart, nameEnd, declStart, declEnd);
    }

}
