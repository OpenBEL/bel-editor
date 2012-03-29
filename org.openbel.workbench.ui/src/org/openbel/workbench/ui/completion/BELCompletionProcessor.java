package org.openbel.workbench.ui.completion;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

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
