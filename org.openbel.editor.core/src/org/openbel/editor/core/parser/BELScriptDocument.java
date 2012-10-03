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
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.IElementRequestor.FieldInfo;
import org.openbel.editor.core.parser.ast.ASTDocument;
import org.openbel.editor.core.parser.ast.ASTStatement;
import org.openbel.editor.core.parser.ast.AnnotationSetListField;
import org.openbel.editor.core.parser.ast.Keyword;
import org.openbel.editor.core.parser.ast.ObjectIdentExpression;
import org.openbel.editor.core.parser.ast.ParameterDefinitionExpression;
import org.openbel.editor.core.parser.ast.ParameterDefinitionIdExpression;
import org.openbel.editor.core.parser.ast.QuotedValue;
import org.openbel.editor.core.parser.ast.RelationshipLiteral;
import org.openbel.editor.core.parser.ast.SetStatementGroupExpression;
import org.openbel.editor.core.parser.ast.UnsetStatementGroupExpression;
import org.openbel.editor.core.parser.ast.UnsetStatementIdExpression;
import org.openbel.editor.core.parser.ast.ValueListExpression;

/**
 * This is the top-level representation of a BEL Script document.
 */
public class BELScriptDocument extends ModuleDeclaration {

    private List<FunctionInfo> functionsInfo;
    private List<FieldInfo> variablesInfo;

    private List<AnnotationSetListField> annotationSetListFields;

    private List<ParameterDefinitionExpression> parameterExpressions;
    private List<ObjectIdentExpression> identExpressions;
    private List<ParameterDefinitionIdExpression> definitionIdExpressions;
    private List<QuotedValue> quotedValues;
    private List<RelationshipLiteral> relationshipliterals;
    private List<ASTStatement> statements;
    private List<Keyword> keywords;
    private List<ValueListExpression> valueListExpressions;
    private ASTDocument docDef;
    private List<UnsetStatementGroupExpression> unsetStatementGroupExpressions;
    private List<SetStatementGroupExpression> setStatementGroupExpressions;
    private List<UnsetStatementIdExpression> unsetStatementIdExpressions;

    public BELScriptDocument(int sourceLength) {
        super(sourceLength);
        functionsInfo = new ArrayList<FunctionInfo>();
        variablesInfo = new ArrayList<FieldInfo>();
        annotationSetListFields = new ArrayList<AnnotationSetListField>();
        parameterExpressions = new ArrayList<ParameterDefinitionExpression>();
        identExpressions = new ArrayList<ObjectIdentExpression>();
        definitionIdExpressions = new ArrayList<ParameterDefinitionIdExpression>();
        relationshipliterals = new ArrayList<RelationshipLiteral>();
        quotedValues = new ArrayList<QuotedValue>();
        statements = new ArrayList<ASTStatement>();
        keywords = new ArrayList<Keyword>();
        valueListExpressions = new ArrayList<ValueListExpression>();
        docDef = new ASTDocument();
        unsetStatementGroupExpressions = new ArrayList<UnsetStatementGroupExpression>();
        setStatementGroupExpressions = new ArrayList<SetStatementGroupExpression>();
        unsetStatementIdExpressions = new ArrayList<UnsetStatementIdExpression>();
    }

    public List<FieldInfo> getFieldsInfo() {
        return variablesInfo;
    }

    public List<FunctionInfo> getFunctionsInfo() {
        return functionsInfo;
    }

    @SuppressWarnings("unchecked")
    public void setFunctions(List<MethodDeclaration> functions) {
        getFunctionList().addAll(functions);
        for (MethodDeclaration method : functions) {
            FunctionInfo mInfo = new FunctionInfo();
            mInfo.name = method.getName();
            mInfo.nameSourceStart = method.getNameStart();
            mInfo.nameSourceEnd = method.getNameEnd();
            mInfo.declarationStart = method.sourceStart();
            mInfo.declarationEnd = method.sourceEnd();
            functionsInfo.add(mInfo);
        }
    }

    @SuppressWarnings("unchecked")
    public void setVariables(List<FieldDeclaration> variables) {
        getVariablesList().addAll(variables);
        for (FieldDeclaration method : variables) {
            FieldInfo vInfo = new FieldInfo();
            vInfo.name = method.getName();
            vInfo.nameSourceStart = method.getNameStart();
            vInfo.nameSourceEnd = method.getNameEnd();
            vInfo.declarationStart = method.sourceStart();
            variablesInfo.add(vInfo);
        }
    }

    public List<AnnotationSetListField> getAnnotationSetListFields() {
        return annotationSetListFields;
    }

    public void setAnnotationSetListFields(
            List<AnnotationSetListField> annotationSetListFields) {
        this.annotationSetListFields = annotationSetListFields;
    }

    public List<ParameterDefinitionExpression> getParameterExpressions() {
        return parameterExpressions;
    }

    public void setParameterExpressions(
            List<ParameterDefinitionExpression> parameterExpressions) {
        this.parameterExpressions = parameterExpressions;
    }

    public List<ParameterDefinitionIdExpression> getParameterDefinitionIdExpressions() {
        return definitionIdExpressions;
    }

    public void setDefinitionIdExpressions(
            List<ParameterDefinitionIdExpression> definitionIdExpressions) {
        this.definitionIdExpressions = definitionIdExpressions;
    }

    public List<ObjectIdentExpression> getObjectIdentExpressions() {
        return identExpressions;
    }

    public void setIdentExpressions(List<ObjectIdentExpression> identExpressions) {
        this.identExpressions = identExpressions;
    }

    public List<QuotedValue> getQuotedValues() {
        return quotedValues;
    }

    public void setQuotedValues(List<QuotedValue> quotedValues) {
        this.quotedValues = quotedValues;
    }

    public List<RelationshipLiteral> getRelationshipLiterals() {
        return relationshipliterals;
    }

    public void setRelationshipLiterals(List<RelationshipLiteral> literals) {
        this.relationshipliterals = literals;
    }

    public List<ASTStatement> getStatementsList() {
        return statements;
    }

    public void setStatementsList(List<ASTStatement> statements) {
        this.statements = statements;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<ValueListExpression> getValueListExpressions() {
        return valueListExpressions;
    }

    public void setValueListExpressions(
            List<ValueListExpression> valueListExpressions) {
        this.valueListExpressions = valueListExpressions;
    }

    public ASTDocument getDocDef() {
        return docDef;
    }

    public void setDocDef(ASTDocument docDef) {
        this.docDef = docDef;
    }

    public List<UnsetStatementGroupExpression> getUnsetStatementGroupExpressions() {
        return unsetStatementGroupExpressions;
    }

    public void setUnsetStatementGroupExpressions(
            List<UnsetStatementGroupExpression> unsetStatementGroupExpressions) {
        this.unsetStatementGroupExpressions = unsetStatementGroupExpressions;
    }

    public List<SetStatementGroupExpression> getSetStatementGroupExpressions() {
        return setStatementGroupExpressions;
    }

    public void setSetStatementGroupExpressions(
            List<SetStatementGroupExpression> setStatementGroupExpressions) {
        this.setStatementGroupExpressions = setStatementGroupExpressions;
    }

    public List<UnsetStatementIdExpression> getUnsetStatementIdExpressions() {
        return unsetStatementIdExpressions;
    }

    public void setUnsetStatementIdExpressions(
            List<UnsetStatementIdExpression> unsetStatementIdExpressions) {
        this.unsetStatementIdExpressions = unsetStatementIdExpressions;
    }

}
