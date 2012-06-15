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

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;
import org.openbel.editor.ui.Activator;

public class BELCompletionProposal extends ScriptCompletionProposal {
    public BELCompletionProposal(String replacementString,
            int replacementOffset, int replacementLength, Image image,
            String displayString, int relevance) {
        super(replacementString, replacementOffset, replacementLength, image,
                displayString, relevance);
    }

    public BELCompletionProposal(String replacementString,
            int replacementOffset, int replacementLength, Image image,
            String displayString, int relevance, boolean isInDoc) {
        super(replacementString, replacementOffset, replacementLength, image,
                displayString, relevance, isInDoc);
    }

    @Override
    protected boolean insertCompletion() {
        IPreferenceStore preference = Activator.getDefault()
                .getPreferenceStore();
        return preference
                .getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
    }

    @Override
    protected boolean isSmartTrigger(char trigger) {
        if (trigger == '$') {
            return true;
        }
        return false;
    }
}
