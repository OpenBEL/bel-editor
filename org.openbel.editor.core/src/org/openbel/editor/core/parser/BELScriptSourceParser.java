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

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.openbel.editor.core.parser.BELScript_v1Parser.document_return;
import org.openbel.editor.core.parser.ast.ASTDocument;
import org.openbel.editor.core.parser.ast.ASTStatement;
import org.openbel.editor.core.parser.ast.AnnotationDefineField;
import org.openbel.editor.core.parser.ast.AnnotationSetField;
import org.openbel.editor.core.parser.ast.AnnotationSetListField;
import org.openbel.editor.core.parser.ast.DocumentField;
import org.openbel.editor.core.parser.ast.InvalidNode;
import org.openbel.editor.core.parser.ast.Keyword;
import org.openbel.editor.core.parser.ast.NamespaceDefineField;
import org.openbel.editor.core.parser.ast.ObjectIdentExpression;
import org.openbel.editor.core.parser.ast.ParameterDefinitionExpression;
import org.openbel.editor.core.parser.ast.ParameterDefinitionIdExpression;
import org.openbel.editor.core.parser.ast.QuotedValue;
import org.openbel.editor.core.parser.ast.RelationshipLiteral;
import org.openbel.editor.core.parser.ast.TermDefinition;
import org.openbel.editor.core.parser.ast.ValueListExpression;

/**
 * BEL Script implementation of a source parser.
 */
public class BELScriptSourceParser extends AbstractSourceParser {

    private IProblemReporter pr;
    private BELScriptDocument script;

    /**
     * {@inheritDoc}
     */
    @Override
    public IModuleDeclaration parse(IModuleSource src, IProblemReporter pr) {
        this.pr = pr;
        char[] content = src.getContentsAsCharArray();
        String str = new String(content);
        ANTLRStringStream stream = new ANTLRStringStream(str);

        BELScript_v1Lexer lexer = new BELScript_v1Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BELScript_v1Parser parser = new BELScript_v1Parser(tokens);
        document_return DOC = null;
        try {
            DOC = parser.document();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

        /*
         * BELScriptProblem p = new BELScriptProblem("42.txt",
         * "Your answer is."); p.setWarning(true); arg1.reportProblem(p);
         */
        System.out.println("parsed document");
        script = new BELScriptDocument(str.length());
        transformScript((Tree) DOC.getTree());
        return script;
    }

    public void transformScript(Tree root) {
        Assert.isNotNull(root);
        prettyPrint(1, root);

        visitNode(root);
    }

    private ASTNode visitNode(Tree node) {
        ASTNode accept = visit(node);
        for (int i = 0; i < node.getChildCount(); i++) {
            visitNode(node.getChild(i));
        }
        return accept;
    }

    private ASTNode visit(Tree node) {
        Assert.isNotNull(node);
        switch (node.getType()) {
        case BELScript_v1Parser.DOCDEF:
            return visitDocDef(node);
        case BELScript_v1Parser.DOCSET_QV:
            return visitSetDocument(node);
        case BELScript_v1Parser.ANNO_DEF_URL:
            return visitDefineAnnotation(node);
        case BELScript_v1Parser.NSDEF:
            return visitDefineNamespace(node);
        case BELScript_v1Parser.ANNO_SET_LIST:
            return visitAnnotationSetList(node);
        case BELScript_v1Parser.ANNO_SET_QV:
            return visitAnnotationSet(node);
        case BELScript_v1Parser.PARAM_DEF_QV:
            return visitParameterExpression(node);
        case BELScript_v1Parser.TERMDEF:
            return visitTermDefinition(node);
        case BELScript_v1Parser.T__64:
        case BELScript_v1Parser.T__65:
        case BELScript_v1Parser.T__66:
        case BELScript_v1Parser.T__67:
        case BELScript_v1Parser.T__68:
        case BELScript_v1Parser.T__69:
        case BELScript_v1Parser.T__70:
        case BELScript_v1Parser.T__71:
        case BELScript_v1Parser.T__72:
        case BELScript_v1Parser.T__73:
        case BELScript_v1Parser.T__74:
        case BELScript_v1Parser.T__75:
        case BELScript_v1Parser.T__76:
        case BELScript_v1Parser.T__77:
        case BELScript_v1Parser.T__78:
        case BELScript_v1Parser.T__79:
        case BELScript_v1Parser.T__80:
        case BELScript_v1Parser.T__81:
        case BELScript_v1Parser.T__82:
        case BELScript_v1Parser.T__83:
        case BELScript_v1Parser.T__84:
        case BELScript_v1Parser.T__85:
        case BELScript_v1Parser.T__86:
        case BELScript_v1Parser.T__87:
        case BELScript_v1Parser.T__88:
        case BELScript_v1Parser.T__89:
        case BELScript_v1Parser.T__90:
        case BELScript_v1Parser.T__91:
        case BELScript_v1Parser.T__92:
        case BELScript_v1Parser.T__93:
        case BELScript_v1Parser.T__94:
        case BELScript_v1Parser.T__95:
        case BELScript_v1Parser.T__96:
        case BELScript_v1Parser.T__97:
        case BELScript_v1Parser.T__98:
        case BELScript_v1Parser.T__99:
        case BELScript_v1Parser.T__100:
        case BELScript_v1Parser.T__101:
        case BELScript_v1Parser.T__102:
        case BELScript_v1Parser.T__103:
        case BELScript_v1Parser.T__104:
        case BELScript_v1Parser.T__105:
        case BELScript_v1Parser.T__106:
        case BELScript_v1Parser.T__107:
        case BELScript_v1Parser.T__108:
        case BELScript_v1Parser.T__109:
        case BELScript_v1Parser.T__110:
        case BELScript_v1Parser.T__111:
        case BELScript_v1Parser.T__112:
        case BELScript_v1Parser.T__113:
        case BELScript_v1Parser.T__114:
        case BELScript_v1Parser.T__115:
        case BELScript_v1Parser.T__116:
        case BELScript_v1Parser.T__117:
        case BELScript_v1Parser.T__118:
        case BELScript_v1Parser.T__119:
        case BELScript_v1Parser.T__120:
        case BELScript_v1Parser.T__121:
        case BELScript_v1Parser.T__122:
        case BELScript_v1Parser.T__123:
        case BELScript_v1Parser.T__124:
        case BELScript_v1Parser.T__125:
        case BELScript_v1Parser.T__126:
        case BELScript_v1Parser.T__127:
        case BELScript_v1Parser.T__128:
        case BELScript_v1Parser.T__129:
        case BELScript_v1Parser.T__130:
        case BELScript_v1Parser.T__131:
        case BELScript_v1Parser.T__132:
        case BELScript_v1Parser.T__133:
        case BELScript_v1Parser.T__134:
        case BELScript_v1Parser.T__135:
        case BELScript_v1Parser.T__136:
        case BELScript_v1Parser.T__137:
        case BELScript_v1Parser.T__138:
        case BELScript_v1Parser.T__139:
        case BELScript_v1Parser.T__140:
        case BELScript_v1Parser.T__141:
        case BELScript_v1Parser.T__142:
        case BELScript_v1Parser.T__143:
        case BELScript_v1Parser.T__144:
        case BELScript_v1Parser.T__145:
        case BELScript_v1Parser.T__146:
        case BELScript_v1Parser.T__147:
        case BELScript_v1Parser.T__148:
        case BELScript_v1Parser.T__149:
        case BELScript_v1Parser.T__150:
            return visitRelationshipLiteral(node);
        case BELScript_v1Parser.KWRD_DESC:
        case BELScript_v1Parser.KWRD_VERSION:
        case BELScript_v1Parser.KWRD_COPYRIGHT:
        case BELScript_v1Parser.KWRD_CONTACTINFO:
        case BELScript_v1Parser.KWRD_AUTHORS:
        case BELScript_v1Parser.KWRD_LICENSES:
        case BELScript_v1Parser.KWRD_NAME:
            return visitKeyword(node);
        case BELScript_v1Parser.OBJECT_IDENT:
            return visitObjectIdentExpression(node);
        case BELScript_v1Parser.PARAM_DEF_ID:
            return visitParamaterDefinitionIdExpression(node);
        case BELScript_v1Parser.QUOTED_VALUE:
            return visitQuotedValueExpression(node);
        case BELScript_v1Parser.STMTDEF:
            return visitStatementExpression(node);
        case BELScript_v1Parser.VALUE_LIST:
            return visitValueListExpression(node);

        default:
            return visitUnknown(node);
        }
    }

    private ASTNode visitDocDef(Tree node) {
        ASTDocument field = new ASTDocument();
        script.setDocDef(field);
        return field;
    }

    private ASTNode visitSetDocument(Tree node) {
        DocumentField field = new DocumentField();
        field.setKeyword((Keyword) visit(node.getChild(0)));
        field.setValue((QuotedValue) visit(node.getChild(1)));
        script.getSetDocumentInfo().add(field);
        return field;
    }

    private ASTNode visitKeyword(Tree node) {
        Keyword field = new Keyword();
        field.setText(node.getText());
        script.getKeywords().add(field);
        return field;
    }

    private ASTNode visitDefineAnnotation(Tree node) {
        AnnotationDefineField field = new AnnotationDefineField();
        field.setName((Expression) visit(node.getChild(0)));
        field.setValue((QuotedValue) visit(node.getChild(1)));
        script.getAnnotationDefineFields().add(field);
        return field;
    }

    private ASTNode visitDefineNamespace(Tree node) {
        NamespaceDefineField field = new NamespaceDefineField();
        field.setName((Expression) visit(node.getChild(0)));
        field.setValue((QuotedValue) visit(node.getChild(1)));
        script.getNamespaceDefineFields().add(field);
        return field;
    }

    private ASTNode visitAnnotationSetList(Tree node) {
        AnnotationSetListField field = new AnnotationSetListField();
        field.setObjectIdent((ObjectIdentExpression) visit(node.getChild(0)));
        field.setValueList((ValueListExpression) visit(node.getChild(1)));
        script.getAnnotationSetListFields().add(field);
        return field;
    }

    private ASTNode visitAnnotationSet(Tree node) {
        AnnotationSetField field = new AnnotationSetField();
        field.setName((Expression) visit(node.getChild(0)));
        field.setValue((QuotedValue) visit(node.getChild(1)));
        script.getAnnotationListFields().add(field);

        return field;
    }

    private ASTNode visitParameterExpression(Tree node) {
        ParameterDefinitionExpression field = new ParameterDefinitionExpression();
        field.setName((Expression) visit(node.getChild(0)));
        field.setValue((QuotedValue) visit(node.getChild(1)));
        script.getParameterExpressions().add(field);

        return field;
    }

    private ASTNode visitRelationshipLiteral(Tree node) {
        RelationshipLiteral field = new RelationshipLiteral(
                node.getTokenStartIndex(), node.getTokenStopIndex());
        script.getRelationshipLiterals().add(field);
        return field;
    }

    private ASTNode visitTermDefinition(Tree node) {
        TermDefinition field = new TermDefinition();
        field.setRelationshipLiteral((RelationshipLiteral) visit(node
                .getChild(0)));
        int type = node.getChild(1).getType();
        //nested term
        if (type == BELScript_v1Parser.TERMDEF) {
            field.setTermDefinition((TermDefinition) visit(node
                    .getChild(1)));
        }
        //parameter
        if (type == BELScript_v1Parser.PARAM_DEF_QV) {
            field.setParameterExpression((ParameterDefinitionExpression) visit(node
                    .getChild(1)));
        }
        //parameter
        if (type == BELScript_v1Parser.PARAM_DEF_ID) {
            field.addParameterDefinitionIdExpression((ParameterDefinitionIdExpression) visit(node
                    .getChild(1)));
        }
        script.getTermDefinitions().add(field);

        return field;
    }

    private ASTNode visitObjectIdentExpression(Tree node) {
        ObjectIdentExpression field = new ObjectIdentExpression();
        field.setName(node.getText());
        script.getObjectIdentExpressions().add(field);
        return field;
    }

    private ASTNode visitParamaterDefinitionIdExpression(Tree node) {
        ParameterDefinitionIdExpression field = new ParameterDefinitionIdExpression();
        script.getParameterDefinitionIdExpressions().add(field);
        return field;
    }

    private ASTNode visitQuotedValueExpression(Tree node) {
        QuotedValue field = new QuotedValue(node.getText());
        script.getQuotedValues().add(field);
        return field;
    }

    private ASTNode visitValueListExpression(Tree node) {
        ValueListExpression field = new ValueListExpression();
        field.setText(node.getText());
        script.getValueListExpressions().add(field);
        return field;
    }

    private ASTNode visitStatementExpression(Tree node) {
        ASTStatement field = new ASTStatement();
        if (node.getChildCount() == 3) {
            if (node
                    .getChild(0).getType() == BELScript_v1Parser.TERMDEF) {
                field.setLeftTerm((TermDefinition) visit(node
                        .getChild(0)));
            }
            field.setRelationship((RelationshipLiteral) visit(node
                    .getChild(1)));
            field.setRightTerm((TermDefinition) visit(node
                    .getChild(2)));

        }
        if (node.getChildCount() == 1) {
            field.setLeftTerm((TermDefinition) visit(node.getChild(0)));
        }

        script.getStatementsList().add(field);
        return field;
    }

    private ASTNode visitUnknown(Tree node) {
        System.out.println("Unknown token "
                + BELScript_v1Parser.tokenNames[node.getType()] + " ("
                + node.getText()
                + ")");
        return new InvalidNode(node.toString());
    }

    private void prettyPrint(int indent, Tree tree) {

        for (int i = 0; i < indent; i++) {
            System.out.print('-');
        }
        System.out.println(tree.getText() + "=" + tree.getType());
        for (int i = 0; i < tree.getChildCount(); i++) {
            prettyPrint(indent + 4, tree.getChild(i));
        }
    }

}
