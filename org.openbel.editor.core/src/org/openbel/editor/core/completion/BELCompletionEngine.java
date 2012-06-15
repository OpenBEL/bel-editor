/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.completion;

import static java.lang.Character.isLetter;
import static java.lang.Character.isWhitespace;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static org.openbel.editor.core.CoreConstants.BELSCRIPT_KEYWORDS;
import static org.openbel.editor.core.common.BELUtilities.hasItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.*;

/**
 * The BEL completion engine.
 */
public class BELCompletionEngine extends ScriptCompletionEngine {
    private int actualCompletionPosition;
    private int offset;
    IScriptProject project;
    private CompletionRequestor requestor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void complete(IModuleSource module, int pos, int i) {
        this.actualCompletionPosition = pos;
        this.offset = i;
        char[] buffer = module.getContentsAsCharArray();
        String[] words = previousWord(buffer, pos, 1);
        if (hasItems(words)) {
            String[] possibles = complete(words[0], buffer, pos);
            if (hasItems(possibles)) {
                for (String s : possibles) {
                    createProposal(s, null);
                }
                return;
            }
        }

        for (String keyword : BELSCRIPT_KEYWORDS) {
            createProposal(keyword, null);
        }

        // Completion for model elements.
        try {
            module.getModelElement().accept(new IModelElementVisitor() {
                @Override
                public boolean visit(IModelElement element) {
                    if (element.getElementType() > IModelElement.SOURCE_MODULE) {
                        createProposal(element.getElementName(), element);
                    }
                    return true;
                }
            });
        } catch (ModelException e) {
            if (DLTKCore.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOptions(@SuppressWarnings("rawtypes") Map opt) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProgressMonitor(IProgressMonitor pm) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProject(IScriptProject p) {
        this.project = p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequestor(CompletionRequestor req) {
        this.requestor = req;
    }

    @Override
    public CompletionProposal createProposal(int kind, int completionOffset) {
        return CompletionProposal.create(kind, completionOffset - this.offset);
    }

    private void createProposal(String name, IModelElement element) {
        CompletionProposal proposal = this.createProposal(
                CompletionProposal.KEYWORD, this.actualCompletionPosition);
        proposal.setName(name);
        proposal.setCompletion(name);
        proposal.setReplaceRange(actualCompletionPosition - offset,
                actualCompletionPosition - offset);
        proposal.setKey("key");
        proposal.setExtraInfo(new Object());
        proposal.setRelevance(20);
        proposal.setModelElement(element);
        this.requestor.accept(proposal);
    }

    /**
     * A poor man's completer.
     * 
     * @param token
     *            Previous word; may not be null
     * @param buffer
     *            The character buffer where the previous words are found
     * @param pos
     *            The position in the character buffer
     * @return Array of possible completion words; may be empty
     */
    private static String[] complete(String token, char[] buffer, int pos) {
        // Poor man's completer.
        List<String> ret = new ArrayList<String>();

        if (token.equals("DEFINE")) {
            ret.add("DEFAULT ");
            ret.add("NAMESPACE ");
            ret.add("ANNOTATION ");
            return ret.toArray(new String[0]);
        }

        if (token.equals("DEFAULT")) {
            ret.add("NAMESPACE ");
            return ret.toArray(new String[0]);
        }

        if (token.equals("NAMESPACE") || token.equals("ANNOTATION")) {
            ret.add("AS ");
            return ret.toArray(new String[0]);
        }

        if (token.equals("SET")) {
            ret.add("DOCUMENT ");
            ret.add("STATEMENT_GROUP = ");
        }

        if (token.equals("UNSET")) {
            ret.add("STATEMENT_GROUP");
        }

        String[] previous = previousWord(buffer, pos, 2);
        if (previous.length == 2) {
            if (previous[0].equals("AS")) {
                if (previous[1].equals("NAMESPACE")) {
                    ret.add("URL ");
                    return ret.toArray(new String[0]);
                } else if (previous[1].equals("ANNOTATION")) {
                    ret.add("PATTERN ");
                    ret.add("URL ");
                    return ret.toArray(new String[0]);
                }
            }
        }

        return ret.toArray(new String[0]);
    }

    private static String[] previousWord(char[] buffer, int position, int words) {
        List<String> ret = new ArrayList<String>();
        if (buffer == null || buffer.length == 0)
            return emptyList().toArray(new String[0]);

        int whitespace_char_pos = -1;
        for (int i = (position - 1); i >= 0; i--) {
            if (isWhitespace(buffer[i])) {
                whitespace_char_pos = i;
                break;
            }
        }

        if (whitespace_char_pos == -1)
            return emptyList().toArray(new String[0]);

        int letter_pos = -1;
        for (int i = (whitespace_char_pos - 1); i >= 0; i--) {
            if (isLetter(buffer[i])) {
                letter_pos = i;
                break;
            }
        }

        if (letter_pos == -1)
            return emptyList().toArray(new String[0]);

        String word = "";
        int i = letter_pos;
        for (; i >= 0; i--) {
            char c = buffer[i];
            if (isLetter(c)) {
                word = word.concat(valueOf(c));
                continue;
            }
            break;
        }

        if (word == "") {
            return emptyList().toArray(new String[0]);
        }

        ret.add(new StringBuilder(word).reverse().toString());

        if (words > 1) {
            String[] more = previousWord(buffer, i + 1, words - 1);
            for (String s : more) {
                ret.add(s);
            }
        }

        return ret.toArray(new String[0]);
    }

}
