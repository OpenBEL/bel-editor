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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class BELDocumentProvider extends FileDocumentProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDocument createDocument(Object element) throws CoreException {
        IDocument document = super.createDocument(element);
        /*
         * if (document != null) { IDocumentPartitioner partitioner = new
         * FastPartitioner(); partitioner.connect(document);
         * document.setDocumentPartitioner(partitioner); }
         */
        return document;
    }

}