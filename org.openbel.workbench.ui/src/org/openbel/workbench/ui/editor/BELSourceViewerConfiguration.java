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

import static org.openbel.workbench.ui.text.BELPartitions.*;

import org.eclipse.dltk.ui.text.*;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.BELContentAssistPreference;
import org.openbel.workbench.ui.UIConstants;
import org.openbel.workbench.ui.completion.BELCompletionProcessor;
import org.openbel.workbench.ui.text.BELCodeScanner;
import org.openbel.workbench.ui.text.CommentScanner;
import org.openbel.workbench.ui.text.ScriptAutoIndentStrategy;

public class BELSourceViewerConfiguration extends
        ScriptSourceViewerConfiguration {

    private AbstractScriptScanner codeScanner;
    private AbstractScriptScanner commentScanner;
    private AbstractScriptScanner stringScanner;

    public BELSourceViewerConfiguration(IColorManager colorManager,
            IPreferenceStore preferenceStore, ITextEditor editor,
            String partitioning) {
        super(colorManager, preferenceStore, editor, partitioning);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void alterContentAssistant(ContentAssistant assistant) {
        IContentAssistProcessor scriptProcessor = new BELCompletionProcessor(
                getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(scriptProcessor,
                IDocument.DEFAULT_CONTENT_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAutoEditStrategy[] getAutoEditStrategies(
            ISourceViewer sourceViewer, String contentType) {
        ScriptAutoIndentStrategy strategy = new ScriptAutoIndentStrategy();
        return new IAutoEditStrategy[] { strategy };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ContentAssistPreference getContentAssistPreference() {
        return BELContentAssistPreference.getDefault();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getIndentPrefixes(ISourceViewer sourceViewer,
            String contentType) {
        return new String[] { "\t", "        " };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IInformationControlCreator getOutlinePresenterControlCreator(
            ISourceViewer sourceViewer, final String commandId) {
        return new IInformationControlCreator() {
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                int shellStyle = SWT.RESIZE;
                int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
                return new ScriptOutlineInformationControl(parent, shellStyle,
                        treeStyle, commandId, Activator.getDefault()
                                .getPreferenceStore());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPresentationReconciler getPresentationReconciler(
            ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new ScriptPresentationReconciler();
        String docpart = this.getConfiguredDocumentPartitioning(sourceViewer);
        reconciler.setDocumentPartitioning(docpart);

        DefaultDamagerRepairer dr;

        dr = new DefaultDamagerRepairer(stringScanner);
        reconciler.setDamager(dr, STRING);
        reconciler.setRepairer(dr, STRING);

        dr = new DefaultDamagerRepairer(codeScanner);
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        reconciler.setDamager(dr, HASH_COMMENT);
        reconciler.setRepairer(dr, HASH_COMMENT);

        reconciler.setDamager(dr, RELATIONSHIP);
        reconciler.setRepairer(dr, RELATIONSHIP);

        reconciler.setDamager(dr, KEYWORD);
        reconciler.setRepairer(dr, KEYWORD);

        reconciler.setDamager(dr, OTHER);
        reconciler.setRepairer(dr, OTHER);

        reconciler.setDamager(dr, FUNCTION);
        reconciler.setRepairer(dr, FUNCTION);

        reconciler.setDamager(dr, RELATIONSHIP);
        reconciler.setRepairer(dr, RELATIONSHIP);

        /*
        dr = new DefaultDamagerRepairer(fxScanner);
        reconciler.setDamager(dr, FUNCTION);
        reconciler.setRepairer(dr, FUNCTION);
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        dr = new DefaultDamagerRepairer(commentScanner);
        reconciler.setDamager(dr, HASH_COMMENT);
        reconciler.setRepairer(dr, HASH_COMMENT);
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        */

        return reconciler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlePropertyChangeEvent(PropertyChangeEvent event) {
        if (codeScanner.affectsBehavior(event))
            codeScanner.adaptToPreferenceChange(event);
        if (commentScanner.affectsBehavior(event))
            commentScanner.adaptToPreferenceChange(event);
        if (stringScanner.affectsBehavior(event))
            stringScanner.adaptToPreferenceChange(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean affectsTextPresentation(PropertyChangeEvent event) {
        if (codeScanner.affectsBehavior(event)) return true;
        if (commentScanner.affectsBehavior(event)) return true;
        if (stringScanner.affectsBehavior(event)) return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeScanners() {
        IColorManager mgr = this.getColorManager();
        IPreferenceStore prefs = this.fPreferenceStore;

        codeScanner = new BELCodeScanner(mgr, prefs);
        commentScanner = new CommentScanner(mgr, prefs);
        stringScanner = new SingleTokenScriptScanner(mgr, prefs, UIConstants.STRING);
    }
}
