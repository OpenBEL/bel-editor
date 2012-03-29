package org.openbel.workbench.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * 
 * @author julianray
 * 
 */
public class BELConfiguration extends SourceViewerConfiguration {

    private final BELColorManager colorManager;

    /**
     * 
     * @param colorManager
     */
    public BELConfiguration(BELColorManager colorManager) {
        this.colorManager = colorManager;
    }

    /**
	 * 
	 */
    @Override
    public IPresentationReconciler getPresentationReconciler(
            ISourceViewer sourceViewer) {

        PresentationReconciler reconciler = new PresentationReconciler();

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(new BELScanner(
                colorManager));
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        return reconciler;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sv) {

        ContentAssistant ca = new ContentAssistant();
        IContentAssistProcessor cap = new BELCompletionProcessor();
        ca.setContentAssistProcessor(cap, IDocument.DEFAULT_CONTENT_TYPE);
        ca.setInformationControlCreator(getInformationControlCreator(sv));
        return ca;
    }

    @Override
    public ITextHover getTextHover(ISourceViewer sv, String contentType) {
        return new BELTextHover();
    }
}