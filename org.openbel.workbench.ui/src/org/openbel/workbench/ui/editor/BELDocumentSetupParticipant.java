package org.openbel.workbench.ui.editor;

import static org.openbel.workbench.ui.text.BELPartitions.PARTITIONING;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.text.BELTextTools;

public class BELDocumentSetupParticipant implements IDocumentSetupParticipant {

    @Override
    public void setup(IDocument document) {
        BELTextTools tools = Activator.getDefault().getTextTools();
        tools.setupDocumentPartitioner(document, PARTITIONING);
    }
}
