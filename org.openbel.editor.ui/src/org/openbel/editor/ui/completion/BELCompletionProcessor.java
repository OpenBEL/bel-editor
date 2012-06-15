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

import static org.openbel.editor.core.Nature.BEL_NATURE;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

public class BELCompletionProcessor extends ScriptCompletionProcessor {
    public BELCompletionProcessor(IEditorPart editor,
            ContentAssistant assistant, String partition) {
        super(editor, assistant, partition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getNatureId() {
        return BEL_NATURE;
    }

}
