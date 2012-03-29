package org.openbel.workbench.ui.editor;

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