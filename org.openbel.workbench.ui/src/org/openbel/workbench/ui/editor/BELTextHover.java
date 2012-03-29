package org.openbel.workbench.ui.editor;

import static org.openbel.workbench.ui.Activator.logInfo;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;

public class BELTextHover implements ITextHover {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
        logInfo("getHoverInfo");
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
        logInfo("getHoverRegion");
        return null;
    }

}