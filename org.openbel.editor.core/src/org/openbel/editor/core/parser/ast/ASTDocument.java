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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.expressions.Expression;
import org.openbel.editor.core.parser.BELScript_v1Parser;

/**
 * Represents the root element of a BEL Script document.
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#DOCDEF}
 * </p>
 */
public class ASTDocument extends Expression {
    private List<ASTStatement> statementsList = new ArrayList<ASTStatement>();
    private List<DocumentField> setDocumentInfo = new ArrayList<DocumentField>();
    private List<NamespaceDefineField> namespaceDefineFields = new ArrayList<NamespaceDefineField>();
    private List<AnnotationDefineField> annotationDefineFields = new ArrayList<AnnotationDefineField>();
    private List<AnnotationDefineListField> annotationDefineListFields = new ArrayList<AnnotationDefineListField>();

    @Override
    public int getKind() {
        return BELScript_v1Parser.DOCDEF;
    }

    public List<ASTStatement> getStatementsList() {
        return statementsList;
    }

    public void setStatementsList(List<ASTStatement> statementsList) {
        this.statementsList = statementsList;
    }

    public List<DocumentField> getSetDocumentInfo() {
        return setDocumentInfo;
    }

    public void setSetDocumentInfo(List<DocumentField> setDocumentInfo) {
        this.setDocumentInfo = setDocumentInfo;
    }

    public List<NamespaceDefineField> getNamespaceDefineFields() {
        return namespaceDefineFields;
    }

    public void setNamespaceDefineFields(
            List<NamespaceDefineField> namespaceDefineFields) {
        this.namespaceDefineFields = namespaceDefineFields;
    }

    public List<AnnotationDefineField> getAnnotationDefineFields() {
        return annotationDefineFields;
    }

    public void setAnnotationDefineFields(
            List<AnnotationDefineField> annotationDefineFields) {
        this.annotationDefineFields = annotationDefineFields;
    }

    public List<AnnotationDefineListField> getAnnotationDefineListFields() {
        return annotationDefineListFields;
    }

    public void setAnnotationDefineListFields(
            List<AnnotationDefineListField> annotationDefineListFields) {
        this.annotationDefineListFields = annotationDefineListFields;
    }

}
