/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.preferences;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openbel.workbench.ui.editor.BELSourceViewerConfiguration;

public class SimpleBELSourceViewerConfiguration extends
        BELSourceViewerConfiguration {

    private final boolean changeFormatter;

    public SimpleBELSourceViewerConfiguration(IColorManager colorManager,
            IPreferenceStore preferenceStore, ITextEditor editor,
            String partitioning, boolean changeFormatter) {
        super(colorManager, preferenceStore, editor, partitioning);
        this.changeFormatter = changeFormatter;
    }

    @Override
    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
        return null;
    }

    @Override
    public IAutoEditStrategy[] getAutoEditStrategies(
            ISourceViewer sourceViewer, String contentType) {
        return null;
    }

    @Override
    public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer,
            String contentType) {
        return null;
    }

    @Override
    public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
        if (changeFormatter) {
            return super.getContentFormatter(sourceViewer);
        }
        return null;
    }

    public IInformationPresenter getHierarchyPresenter(
            ISourceViewer sourceViewer, boolean doCodeResolve) {
        return null;
    }

    @Override
    public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
        return null;
    }

    @Override
    public IInformationControlCreator getInformationControlCreator(
            ISourceViewer sourceViewer) {
        return null;
    }

    @Override
    public IInformationPresenter getInformationPresenter(
            ISourceViewer sourceViewer) {
        return null;
    }

    @Override
    public IInformationPresenter getOutlinePresenter(
            ISourceViewer sourceViewer, boolean doCodeResolve) {
        return null;
    }

    @Override
    public IAnnotationHover getOverviewRulerAnnotationHover(
            ISourceViewer sourceViewer) {
        return null;
    }

    @Override
    public ITextHover getTextHover(ISourceViewer sourceViewer,
            String contentType) {
        return null;
    }

    @Override
    public ITextHover getTextHover(ISourceViewer sourceViewer,
            String contentType, int stateMask) {
        return null;
    }
}
