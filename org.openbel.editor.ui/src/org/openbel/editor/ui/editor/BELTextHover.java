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

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.text.hover.DocumentationHover;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * A text hover implementation that takes into consideration not only the valid
 * Java identifier elements, but it also handles abbreviations. It extends the
 * {@link DocumentationHover} class to inherit the UI and logic of the class.
 * 
 * @author a.bumbas@gmail.com
 */
@SuppressWarnings("restriction")
public class BELTextHover extends DocumentationHover {
    private final ITextEditor editor;

    /**
     * Default constructor.
     * 
     * @param editor - the {@link ITextEditor} instance used by the underlying
     *            class.
     */
    public BELTextHover(ITextEditor editor) {
        this.editor = editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
        return findWord(textViewer.getDocument(), offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ISourceModule getEditorInputModelElement() {
        //hack used to set the editor in the AbstractScriptEditorTextHover class
        setEditor(editor);
        return super.getEditorInputModelElement();
    }

    /**
     * Utility method use to find the {@link IRegion} of an text on the mouse
     * hover event.
     */
    private IRegion findWord(IDocument document, int offset) {

        int start = -2;
        int end = -1;

        try {
            int pos = offset;
            char c;

            while (pos >= 0) {
                c = document.getChar(pos);
                if (!isValidChar(c))
                    break;
                --pos;
            }
            start = pos;

            pos = offset;
            int length = document.getLength();

            while (pos < length) {
                c = document.getChar(pos);
                if (!isValidChar(c))
                    break;
                ++pos;
            }
            end = pos;

        } catch (BadLocationException x) {
        }

        if ((start >= -1) && (end > -1)) {
            if ((start == offset) && (end == offset))
                return new Region(offset, 0);
            else if (start == offset)
                return new Region(start, end - start);
            else
                return new Region(start + 1, end - start - 1);
        }

        return null;
    }

    /**
     * Determines if the given char is to be taken into consideration for the
     * hover. The char is valid when is a valid Java identifier or is a part of
     * an abbreviation.
     * 
     * @param c - the character to be checked
     * @return true if the character is valid, false otherwise
     */
    private static boolean isValidChar(char c) {
        if (Character.isJavaIdentifierPart(c) || (c == '-') || (c == '>')
                || (c == '|') || (c == ':') || (c == '=')) {
            return true;
        }
        return false;
    }

}