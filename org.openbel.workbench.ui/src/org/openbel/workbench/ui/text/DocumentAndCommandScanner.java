/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.RuleBasedScanner;

/**
 * This scanner extends the normal rule based scanner by adding the ability to
 * read DocumentCommands as well as IDocuments. This ability is toggled on or
 * off depending on whether setRange(document,offset,length) (off) or
 * setRange(document,documentCommand,offset,length) (on) was called.
 * 
 * @author Doug Satchwell
 * @version $Id: DocumentAndCommandScanner.java,v 1.2 2008/12/02 21:59:55
 *          akurtakov Exp $
 */
public class DocumentAndCommandScanner extends RuleBasedScanner {
    private DocumentCommand fDocumentCommand;

    /*
     * @see ICharacterScanner#read()
     */
    @Override
    public int read() {
        try {
            if (fOffset < fRangeEnd) {
                try {
                    return fDocument.getChar(fOffset);
                } catch (BadLocationException e) {
                }
            }
            if ((fDocumentCommand != null)
                    && (fOffset < fRangeEnd + fDocumentCommand.text.length())) {
                return fDocumentCommand.text.charAt((fRangeEnd
                        + fDocumentCommand.text.length() - fOffset) - 1);
            }
            return EOF;
        } finally {
            ++fOffset;
        }
    }

    /**
     * Set this to use as a normal in conjunction with DocumentCommand's
     */
    public void setRange(IDocument document, DocumentCommand documentCommand,
            int offset, int length) {
        setRange(document, offset, length);
        fDocumentCommand = documentCommand;
    }

    /**
     * Set this to use as a normal RuleBasedScanner
     */
    @Override
    public void setRange(IDocument document, int offset, int length) {
        super.setRange(document, offset, length);
        fDocumentCommand = null;
    }
}
