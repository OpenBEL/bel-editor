package org.openbel.workbench.ui;

import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class BELContentAssistPreference extends ContentAssistPreference {
    private static BELContentAssistPreference instance;

    public static ContentAssistPreference getDefault() {
        if (instance == null) {
            instance = new BELContentAssistPreference();
        }
        return instance;
    }

    @Override
    protected ScriptTextTools getTextTools() {
        return Activator.getDefault().getTextTools();
    }

}
