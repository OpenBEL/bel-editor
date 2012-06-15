/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.completion;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class BELCompletionProposalComputer extends
        ScriptCompletionProposalComputer {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ICompletionProposal> computeCompletionProposals(
            ContentAssistInvocationContext context, IProgressMonitor monitor) {
        return super.computeCompletionProposals(context, monitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IContextInformation> computeContextInformation(
            ContentAssistInvocationContext context, IProgressMonitor monitor) {
        return super.computeContextInformation(context, monitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ScriptCompletionProposalCollector createCollector(
            ScriptContentAssistInvocationContext context) {
        return new BELCompletionProposalCollector(context.getSourceModule());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TemplateCompletionProcessor createTemplateProposalComputer(
            ScriptContentAssistInvocationContext context) {
        return null;
    }
}
