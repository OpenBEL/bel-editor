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

import org.eclipse.dltk.ast.declarations.MethodDeclaration;

/**
 * Represents a statement group within the BEL Editor AST.
 * <p>
 * This is analogous to setting a statement group within the context of the body
 * of a BEL Script document. For example, the following BEL Script snippet
 * defines a statement group designed to capture species-specific statements.
 * 
 * <pre>
 * <code>
 * SET DOCUMENT Name = "Corpus"
 * DEFINE ANNOTATION Species AS URL "http://internal/bel-annotations/species"
 * SET STATEMENT_GROUP = "9606"
 * SET Species = "9606"
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class ASTStatementGroup extends MethodDeclaration {

    /**
     * Constructs a statement group AST node.
     * 
     * @param name
     * @param nameStart
     * @param nameEnd
     * @param start
     * @param end
     */
    public ASTStatementGroup(String name, int nameStart, int nameEnd,
            int start,
            int end) {
        super(name, nameStart, nameEnd, start, end);
    }

}
