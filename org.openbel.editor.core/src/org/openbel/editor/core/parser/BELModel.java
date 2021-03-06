/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class BELModel {

    private List<MethodDeclaration> functions;
    private List<MethodDeclaration> statements;
    private List<FieldDeclaration> variables;

    public BELModel() {
        functions = new ArrayList<MethodDeclaration>();
        variables = new ArrayList<FieldDeclaration>();
        statements = new ArrayList<MethodDeclaration>();
    }

    public void addFunction(MethodDeclaration funtion) {
        functions.add(funtion);
    }

    public void addStatement(MethodDeclaration statement) {
        statements.add(statement);
    }

    public void addVariable(FieldDeclaration variable) {
        variables.add(variable);
    }

    public List<MethodDeclaration> getFunctions() {
        return functions;
    }

    public List<MethodDeclaration> getStatements() {
        return statements;
    }

    public List<FieldDeclaration> getVariables() {
        return variables;
    }
}
