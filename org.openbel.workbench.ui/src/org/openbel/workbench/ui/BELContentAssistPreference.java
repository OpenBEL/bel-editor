/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

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
