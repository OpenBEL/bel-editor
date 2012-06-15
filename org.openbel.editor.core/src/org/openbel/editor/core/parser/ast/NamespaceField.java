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
package org.openbel.editor.core.parser.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;

/**
 * Represents the definition of a namespace within a BEL Script document.
 * <p>
 * This field is analogous to the definition of a namespace within the context
 * of a BEL Script document. For example, the following BEL Script snippet
 * defines a namespace <i>HGNC</i>.
 * 
 * <pre>
 * <code>
 *  DEFINE NAMESPACE HGNC AS URL "http://internal/bel-namespaces/hgnc-symbols-2012-May.belns"
 * </code>
 * </pre>
 * 
 * </p>
 */
public class NamespaceField extends FieldDeclaration {

    /**
     * Constructs a namespace field AST node.
     * 
     * @param name
     * @param nameStart
     * @param nameEnd
     * @param declStart
     * @param declEnd
     */
    public NamespaceField(String name, int nameStart, int nameEnd,
            int declStart, int declEnd) {
        super(name, nameStart, nameEnd, declStart, declEnd);
    }

}
