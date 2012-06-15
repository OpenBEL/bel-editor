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

import static org.openbel.editor.ui.text.BELPartitions.PARTITIONING;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.openbel.editor.ui.Activator;
import org.openbel.editor.ui.text.BELTextTools;

public class BELDocumentSetupParticipant implements IDocumentSetupParticipant {

    @Override
    public void setup(IDocument document) {
        BELTextTools tools = Activator.getDefault().getTextTools();
        tools.setupDocumentPartitioner(document, PARTITIONING);
    }
}
