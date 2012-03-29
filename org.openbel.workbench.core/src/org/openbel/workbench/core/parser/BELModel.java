package org.openbel.workbench.core.parser;

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
