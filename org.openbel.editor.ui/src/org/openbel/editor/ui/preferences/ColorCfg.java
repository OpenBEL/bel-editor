/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.preferences;

import static org.openbel.editor.ui.UIConstants.*;
import static org.openbel.editor.ui.text.BELPartitions.PARTITIONING;

import java.io.InputStream;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openbel.editor.ui.Activator;
import org.openbel.editor.ui.editor.BELDocumentSetupParticipant;

public class ColorCfg extends AbstractScriptEditorColoringConfigurationBlock {

    private static final String[][] SYNTAX_COLOR_MODEL;
    static {
        SYNTAX_COLOR_MODEL = new String[][] {
            { OTHER_KEY, OTHER, "BEL" },
            { HASH_COMMENT_KEY, HASH_COMMENT, "Comments" },
            { STMT_COMMENT_KEY, STMT_COMMENT, "Comments" },
            { KEYWORD_KEY, KEYWORD, "BEL" },
            { OPERATOR_KEY, OPERATOR, "BEL" },
            { STRING_KEY, STRING, "BEL" },
            { FUNCTION_KEY, FUNCTION, "BEL" },
            { RELATIONSHIP_KEY, RELATIONSHIP, "BEL" } };
    }

    private static final String PREVIEW_FILE_NAME = "Example.bel"; //$NON-NLS-1$

    public ColorCfg(OverlayPreferenceStore store) {
        super(store);
    }

    @Override
    protected ProjectionViewer createPreviewViewer(Composite parent,
            IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
        return new ScriptSourceViewer(parent, verticalRuler, overviewRuler,
                showAnnotationsOverview, styles, store);
    }

    @Override
    protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
            IColorManager colorManager, IPreferenceStore preferenceStore,
            ITextEditor editor, boolean configureFormatter) {
        return new SimpleBELSourceViewerConfiguration(colorManager,
                preferenceStore, editor, PARTITIONING, true);
    }

    @Override
    protected InputStream getPreviewContentReader() {
        return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
    }

    @Override
    protected String[][] getSyntaxColorListModel() {
        return SYNTAX_COLOR_MODEL;
    }

    @Override
    protected ScriptTextTools getTextTools() {
        return Activator.getDefault().getTextTools();
    }

    @Override
    protected void setDocumentPartitioning(IDocument document) {
        BELDocumentSetupParticipant participant = new BELDocumentSetupParticipant();
        participant.setup(document);
    }
}
