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

import org.eclipse.dltk.ast.references.ConstantReference;

/**
 * Represents the value of a parameter within the OpenBEL Workbench AST.
 * <p>
 * This is analogous to a parameter value within the context of a parameter used
 * in a BEL Script document . For example, the following BEL Script snippet uses
 * the parameter value <i>AKT1</i>.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 */
public class ParameterValue extends ConstantReference {

    /**
     * Constructs a parameter value AST node.
     * 
     * @param start
     * @param end
     * @param name
     */
    public ParameterValue(int start, int end, String name) {
        super(start, end, name);
    }

}
