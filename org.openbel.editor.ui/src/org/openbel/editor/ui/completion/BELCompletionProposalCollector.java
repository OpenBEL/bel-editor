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

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.swt.graphics.Image;

public class BELCompletionProposalCollector extends
        ScriptCompletionProposalCollector {

    public BELCompletionProposalCollector(ISourceModule module) {
        super(module);
    }

    @Override
    protected ScriptCompletionProposal createOverrideCompletionProposal(
            IScriptProject scriptProject, ISourceModule compilationUnit,
            String name, String[] paramTypes, int start, int length,
            String label, String string) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ScriptCompletionProposal createScriptCompletionProposal(
            String completion, int replaceStart, int length, Image image,
            String displayString, int i) {
        return new BELCompletionProposal(completion, replaceStart, length,
                image, displayString, i);

    }

    @Override
    protected ScriptCompletionProposal createScriptCompletionProposal(
            String completion, int replaceStart, int length, Image image,
            String displayString, int i, boolean isInDoc) {
        return new BELCompletionProposal(completion, replaceStart, length,
                image, displayString, i);
    }

    @Override
    protected String getNatureId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected char[] getVarTrigger() {
        return new char[] { '$' };
    }

}
