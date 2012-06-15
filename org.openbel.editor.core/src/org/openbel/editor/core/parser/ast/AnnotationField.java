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
 * Represents the definition of an annotation within a BEL Script document.
 * <p>
 * This field is analogous to the definition of an annotation within the context
 * of a BEL Script document. For example, the following BEL Script snippet
 * defines a <i>Species</i> annotation.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * DEFINE ANNOTATION Species AS URL "http://internal/bel-annotations/species"
 * </code>
 * </pre>
 * 
 * </p>
 */
public class AnnotationField extends FieldDeclaration {

    /**
     * Construct an annotation field AST node.
     * 
     * @param name
     * @param nameStart
     * @param nameEnd
     * @param declStart
     * @param declEnd
     */
    public AnnotationField(String name, int nameStart, int nameEnd,
            int declStart, int declEnd) {
        super(name, nameStart, nameEnd, declStart, declEnd);
    }

}
