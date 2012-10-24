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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceRange;
import org.openbel.editor.core.parser.BELScript_v1Parser;

/**
 * Represents a statement in BEL Script.
 * <p>
 * The following is an example of a BEL statement represented by this AST type,
 * in the form of {@code term relationship term}.
 * 
 * <pre>
 * <code>
 * proteinAbundance(HGNC:AKT1,proteinModification(P,T,308)) directlyIncreases kinaseActivity(proteinAbundance(HGNC:AKT1))
 * </code>
 * </pre>
 * 
 * </p>
 * <p>
 * The ANTLR corresponding node is {@link BELScript_v1Parser#STMTDEF}
 * </p>
 * 
 */
public class ASTStatement extends Statement {
    private final ASTNode parent;
    private LinkedList<TermDefinition> termsList = new LinkedList<TermDefinition>();
    private LinkedList<RelationshipLiteral> relationshipList = new LinkedList<RelationshipLiteral>();
    private List<AnnotationSetField> annotationsList = new ArrayList<AnnotationSetField>();
    private List<AnnotationSetListField> annotationSetList = new ArrayList<AnnotationSetListField>();
    private StatementComment comment;

    public ASTStatement(ASTNode parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String debugString() {
        return super.debugString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKind() {
        return BELScript_v1Parser.STMTDEF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ISourceRange getSourceRange() {
        return super.getSourceRange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean locationMatches(ASTNode other) {
        return super.locationMatches(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void traverse(ASTVisitor visitor) throws Exception {
        super.traverse(visitor);
        for (TermDefinition term : termsList) {
            if (term != null) {
                term.traverse(visitor);
            }
        }
        for (RelationshipLiteral rel : relationshipList) {
            if (rel != null) {
                rel.traverse(visitor);
            }
        }
        if (comment != null) {
            comment.traverse(visitor);
        }
    }

    public ASTNode getParent() {
        return parent;
    }

    public List<AnnotationSetField> getAnnotationsList() {
        return annotationsList;
    }

    public void setAnnotationsList(List<AnnotationSetField> annotationsList) {
        this.annotationsList = annotationsList;
    }

    public List<AnnotationSetListField> getAnnotationSetList() {
        return annotationSetList;
    }

    public void setAnnotationSetList(
            List<AnnotationSetListField> annotationSetList) {
        this.annotationSetList = annotationSetList;
    }

    public LinkedList<TermDefinition> getTermsList() {
        return termsList;
    }

    public void setTermsList(LinkedList<TermDefinition> termsList) {
        this.termsList = termsList;
    }

    public LinkedList<RelationshipLiteral> getRelationshipList() {
        return relationshipList;
    }

    public void setRelationshipList(
            LinkedList<RelationshipLiteral> relationshipList) {
        this.relationshipList = relationshipList;
    }

    public StatementComment getComment() {
        return comment;
    }

    public void setComment(StatementComment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < termsList.size(); i++) {
            if (termsList.size() > 2 && i == 1) {
                ret.append("(");
            }
            ret.append(termsList.get(i).toString());
            if (relationshipList.size() > i) {
                ret.append(" " + relationshipList.get(i).toString() + " ");
            }
        }
        if (termsList.size() > 2) {
            ret.append(")");
        }
        if (comment != null) {
            ret.append(comment.getComment());
        }
        return ret.toString();
    }

}
