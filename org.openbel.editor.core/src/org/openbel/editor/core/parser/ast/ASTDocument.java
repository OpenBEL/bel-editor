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

import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

/**
 * Represents a BEL Script document in the BEL Editor AST.
 * <p>
 * This is analogous to a complete BEL Script document.
 * </p>
 */
public class ASTDocument extends Expression {

    @Override
    public int getKind() {
        return BELScript_v1Parser.DOCDEF;
    }

}
