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

import org.eclipse.dltk.ast.references.VariableKind;
import org.eclipse.dltk.ast.references.VariableReference;

/**
 * Represents the reference to a namespace defined within a BEL Script document.
 * <p>
 * This reference is analogous to the namespace prefix occurring within the
 * context of a parameter in a BEL Script document. For example, the following
 * BEL Script snippet uses the <i>HGNC</i> namespace reference.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class NamespaceReference extends VariableReference {

    /**
     * Constructs a namespace reference AST node.
     * 
     * @param start
     * @param end
     * @param name
     */
    public NamespaceReference(int start, int end, String name) {
        super(start, end, name);
    }

    /**
     * Constructs a namespace reference AST node.
     * 
     * @param start
     * @param end
     * @param name
     * @param kind
     */
    public NamespaceReference(int start, int end, String name, VariableKind kind) {
        super(start, end, name, kind);
    }

}
