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

import static org.eclipse.core.runtime.preferences.DefaultScope.INSTANCE;
import static org.eclipse.dltk.compiler.task.TaskTagUtils.initializeDefaultValues;
import static org.eclipse.dltk.ui.CodeFormatterConstants.FORMATTER_INDENTATION_SIZE;
import static org.eclipse.dltk.ui.CodeFormatterConstants.FORMATTER_TAB_CHAR;
import static org.eclipse.dltk.ui.CodeFormatterConstants.FORMATTER_TAB_SIZE;
import static org.eclipse.dltk.ui.CodeFormatterConstants.SPACE;
import static org.eclipse.dltk.ui.PreferenceConstants.*;
import static org.eclipse.dltk.ui.editor.highlighting.SemanticHighlightingUtils.initializeDefaultValues;
import static org.eclipse.jface.preference.PreferenceConverter.setDefault;
import static org.eclipse.ui.editors.text.EditorsUI.useAnnotationsPreferencePage;
import static org.eclipse.ui.editors.text.EditorsUI.useQuickDiffPreferencePage;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIConstants.*;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/*
 * Initializes default workbench preferences.
 */
public class BELUIPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    @SuppressWarnings("deprecation")
    public void initializeDefaultPreferences() {
        IPreferenceStore store = getDefault().getPreferenceStore();

        useAnnotationsPreferencePage(store);
        useQuickDiffPreferencePage(store);

        // Initialize DLTK default values
        initializeDefaultValues(store);

        // Initialize BEL constants
        setDefault(store, OTHER, OTHER_RGB);
        setDefault(store, HASH_COMMENT, HASH_COMMENT_RGB);
        setDefault(store, STMT_COMMENT, STMT_COMMENT_RGB);
        setDefault(store, KEYWORD, KEYWORD_RGB);
        setDefault(store, OPERATOR, OPERATOR_RGB);
        setDefault(store, STRING, STRING_RGB);
        setDefault(store, FUNCTION, FUNCTION_RGB);
        setDefault(store, RELATIONSHIP, RELATIONSHIP_RGB);

        store.setDefault(KEYWORD + EDITOR_BOLD_SUFFIX, true);
        store.setDefault(KEYWORD + EDITOR_ITALIC_SUFFIX, false);

        store.setDefault(FUNCTION + EDITOR_BOLD_SUFFIX, true);
        store.setDefault(FUNCTION + EDITOR_ITALIC_SUFFIX, false);

        store.setDefault(EDITOR_TAB_WIDTH, 4);
        store.setDefault(EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);

        store.setDefault(FORMATTER_TAB_CHAR, SPACE);
        store.setDefault(FORMATTER_TAB_SIZE, "4");
        store.setDefault(FORMATTER_INDENTATION_SIZE, "4");
        store.setDefault(EDITOR_FOLDING_ENABLED, true);

        initializeDefaultValues(store, getDefault().getTextTools()
                .getSemanticHighlightings());
        initializeDefaultValues(INSTANCE.getNode(Activator.PLUGIN_ID));
    }
}
