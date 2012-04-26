/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.editor;

import static org.openbel.workbench.core.BELLanguageToolkit.getDefault;
import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.text.BELPartitions;
import org.openbel.workbench.ui.text.BELTextTools;

@SuppressWarnings("deprecation")
public class BELScriptEditor extends ScriptEditor {

    public static final String EDITOR_CONTEXT = "#BELScriptEditorContext";
    public static final String EDITOR_ID = "org.openbel.workbench.ui.editor.bel";
    private final BELOutlinePage outline = new BELOutlinePage();

    public BELScriptEditor() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void connectPartitioningToElement(IEditorInput input,
            IDocument document) {
        if (document instanceof IDocumentExtension3) {
            IDocumentExtension3 extension = (IDocumentExtension3) document;
            if (extension
                    .getDocumentPartitioner(BELPartitions.PARTITIONING) == null) {
                BELTextTools tools = Activator.getDefault().getTextTools();
                tools.setupDocumentPartitioner(document,
                        BELPartitions.PARTITIONING);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FoldingActionGroup createFoldingActionGroup() {
        return new FoldingActionGroup(this, getViewer(), Activator.getDefault()
                .getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IFoldingStructureProvider createFoldingStructureProvider() {
        return new AbstractASTFoldingStructureProvider() {

            @Override
            protected String getCommentPartition() {
                return BELPartitions.HASH_COMMENT;
            }

            @Override
            protected ILog getLog() {
                return Activator.getDefault().getLog();
            }

            @Override
            protected String getNatureId() {
                return BEL_NATURE;
            }

            @Override
            protected String getPartition() {
                return BELPartitions.PARTITIONING;
            }

            @Override
            protected IPartitionTokenScanner getPartitionScanner() {
                return new RuleBasedPartitionScanner();
            }

            @Override
            protected String[] getPartitionTypes() {
                return BELPartitions.PARTITION_TYPES;
            }

        };
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class cls) {
        if (IContentOutlinePage.class.equals(cls)) {
            return outline;
        }
        return super.getAdapter(cls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEditorId() {
        return EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDLTKLanguageToolkit getLanguageToolkit() {
        return getDefault();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPreferenceStore getScriptPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScriptTextTools getTextTools() {
        return Activator.getDefault().getTextTools();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        setEditorContextMenuId(EDITOR_CONTEXT);
    }

}
