/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.editor;

import static org.openbel.editor.ui.UIFunctions.logInfo;

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