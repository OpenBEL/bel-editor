/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.editor;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

/**
 * @author julianray
 */
class BELCompletionProcessor implements IContentAssistProcessor {

    /**
     *
     */
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
            int offset) {

        ArrayList<ICompletionProposal> results = new ArrayList<ICompletionProposal>();

        /*
         * if (path != null) {
         *
         * String filename = path.toOSString(); IDocument document =
         * viewer.getDocument();
         *
         * CodeContext codeContext = codeContexts.get(filename); int linenumber
         * = 0;
         *
         * try { // the following starts on line 0?, so we add 1 to the result
         * linenumber = document.getLineOfOffset(offset)+1; } catch
         * (BadLocationException e1) { e1.printStackTrace(); }
         *
         * if (codeContext == null) {
         *
         * try { codeContext = CodeContext.getCodeContext(filename,
         * document.get()); } catch (IOException e) { e.printStackTrace(); }
         * kkkk }
         *
         * String prefix = lastWord(document, offset); String indent =
         * lastIndent(document, offset); String line =
         * document.get().split("\\r?\\n")[linenumber];
         *
         * if(codeContext!=null){
         *
         * List<Node> units = codeContext.getCompletionsForString(line, prefix,
         * linenumber);
         *
         * for(Node unit:units){ IContextInformation info = new
         * ContextInformation(unit.getDocumentation(),unit.getDocumentation());
         *
         * int prefix_len = prefix.lastIndexOf('.');
         * if(unit.getInsertionText()!=null){ int len =
         * unit.getInsertionText().length(); if(len>0){ results.add(new
         * CompletionProposal( unit.getInsertionText(), offset, 0,
         * unit.getInsertionText().length() - 1, unit.getImage(),
         * unit.getName(), info, unit.getDocumentation())); } } } } else{ // The
         * following is a simple place holder for a more complex // completion
         * Keywords[] values = Keywords.values(); for (int i = 0; i <
         * values.length; i++) { String keyword = values[i].getValue(); if
         * (keyword.startsWith(prefix)) { IContextInformation info = new
         * ContextInformation(keyword, "XXX");
         * //MessageFormat.format(JavaEditorMessages
         * .getString("CompletionProcessor.Proposal.ContextInfo.pattern"), new
         * Object[] { fgProposals[i] })); //$NON-NLS-1$ results.add(new
         * CompletionProposal(keyword.substring( prefix.length(),
         * keyword.length()) + " ", offset, 0, keyword.length() -
         * prefix.length() + 1, null, keyword, info, values[i]
         * .getDescription())); } } } return results.toArray(new
         * ICompletionProposal[results.size()]);
         *
         * }
         */
        return null;
    }

    /**
     *
     * @param doc
     * @param offset
     * @return
     */
    @SuppressWarnings("unused")
    private String lastWord(IDocument doc, int offset) {
        try {
            for (int n = offset - 1; n >= 0; n--) {
                char c = doc.getChar(n);
                if (!Character.isJavaIdentifierPart(c))
                    return doc.get(n + 1, offset - n - 1);
            }
        } catch (BadLocationException e) {
            // ... log the exception ...
        }
        return "";
    }

    /**
     *
     * @param doc
     * @param offset
     * @return
     */
    @SuppressWarnings("unused")
    private String lastIndent(IDocument doc, int offset) {
        try {
            int start = offset - 1;
            while ((start >= 0) && (doc.getChar(start) != '\n')) {
                start--;
            }
            int end = start;
            while ((end < offset) && Character.isSpaceChar(doc.getChar(end))) {
                end++;
            }
            return doc.get(start + 1, end - start - 1);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     */
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer,
            int offset) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] { '.' };
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        // TODO Auto-generated method stub
        return null;
    }
}
