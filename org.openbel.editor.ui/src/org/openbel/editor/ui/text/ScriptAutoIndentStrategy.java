/*******************************************************************************
 * Copyright (c) 2009 Red Hat Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Kurtakov - initial API and implementation
 *     Mat Booth
 *******************************************************************************/
package org.openbel.editor.ui.text;

import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.openbel.editor.ui.Activator;

/**
 * An indent strategy capable of indenting and unindenting on any set of words,
 * depending on the rules that are set.
 * 
 * @see #setRules(IRule[])
 */
public class ScriptAutoIndentStrategy implements IAutoEditStrategy {
    /**
     * Document scanner used to identify indentations.
     */
    private final DocumentAndCommandScanner scanner = new DocumentAndCommandScanner();

    /**
     * Computes the length of a an indentation, counting a tab character as the
     * size until the next tab stop and every other character as one.
     * 
     * @param indent
     *            the string containing the indentation to measure
     * @param tabSize
     *            the visual size of tab characters
     * @return the visual length in number of characters
     */
    private int computeVisualLength(String indent, int tabSize) {
        int length = 0;
        for (int i = 0; i < indent.length(); i++) {
            char ch = indent.charAt(i);
            switch (ch) {
            case '\t':
                if (tabSize > 0) {
                    int reminder = length % tabSize;
                    length += tabSize - reminder;
                }
                break;
            case ' ':
                length++;
                break;
            }
        }
        return length;
    }

    /**
     * This implementation attempts to auto-indent and auto-unindent after
     * keywords that require it.
     */
    @Override
    public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
        int delim = TextUtilities.endsWith(d.getLegalLineDelimiters(), c.text);
        if ((c.length == 0) && (c.text != null) && (delim != -1)) {
            smartIndentAfterNewLine(d, c);
        } else if (c.text.length() == 1) {
            smartIndentAfterKeypress(d, c);
        }
    }

    /**
     * Returns the first offset greater than <code>offset</code> and smaller
     * than <code>end</code> whose character is not a space or tab character. If
     * no such offset is found, <code>end</code> is returned.
     * 
     * @param document
     *            the document to search in
     * @param offset
     *            the offset at which searching start
     * @param end
     *            the offset at which searching stops
     * @return the offset in the specified range whose character is not a space
     *         or tab
     * @exception BadLocationException
     *                if offset is an invalid position in the given document
     */
    private int findEndOfWhiteSpace(IDocument document, int offset, int end)
            throws BadLocationException {
        while (offset < end) {
            char c = document.getChar(offset);
            if ((c != ' ') && (c != '\t')) {
                return offset;
            }
            offset++;
        }
        return end;
    }

    /**
     * Calculate the indentation needed for a new line based on the contents of
     * the previous line.
     * 
     * @param previous
     *            a string containing the indentation of the previous line
     * @param additional
     *            number of desired addition indentations, may be negative
     * @return a string containing the indentation to use on the new line
     */
    private String generateIndentation(String previous, int additional) {
        // Get the indentation preferences
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        String tabChar = prefs
                .getString(CodeFormatterConstants.FORMATTER_TAB_CHAR);
        int indentSize = prefs
                .getInt(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE);
        int tabSize = prefs.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE);

        // Size in characters of the indentation of the previous line
        int preLength = computeVisualLength(previous, tabSize);

        // Number of addition characters needed
        int addLength = indentSize * additional;

        // Target size of the indentation for the new line
        int endLength = Math.max(0, preLength + addLength);

        // Trim previous indentation back to nearest tab stop
        int minLength = Math.min(endLength, preLength);
        int maxCopyLength = tabSize > 0 ? minLength - (minLength % tabSize)
                : minLength; // maximum indent to copy
        String indent = stripExtraChars(previous, maxCopyLength, tabSize);

        // Add additional indentation
        int missing = endLength - maxCopyLength;
        final int tabs, spaces;
        if (CodeFormatterConstants.SPACE.equals(tabChar)) {
            // Each indent is a number of spaces equal to indent size
            tabs = 0;
            spaces = missing;
        } else if (CodeFormatterConstants.TAB.equals(tabChar)) {
            // Missing should always be in multiples of indent size, so this
            // means "one tab per indent" and indent size is essentially ignored
            tabs = missing / indentSize;
            spaces = 0;
        } else if (CodeFormatterConstants.MIXED.equals(tabChar)) {
            // If the missing indent is a multiple of tab size then tabs will be
            // used, otherwise use spaces
            tabs = tabSize > 0 ? missing / tabSize : 0;
            spaces = tabSize > 0 ? missing % tabSize : missing;
        } else {
            tabs = 0;
            spaces = 0;
        }
        for (int i = 0; i < tabs; i++)
            indent += "\t";
        for (int i = 0; i < spaces; i++)
            indent += " ";
        return indent;
    }

    /**
     * Returns the bracket count of a section of text. The count is incremented
     * when an opening bracket is encountered and decremented when a closing
     * bracket is encountered.
     * 
     * @param document
     *            - the document being parsed
     * @param command
     *            - if not null, the inserted text specified by the command will
     *            be taken into account as if it were part of the document
     * @param start
     *            - the start position for the search
     * @param end
     *            - the end position for the search
     * @param ignoreInflexions
     *            - whether or not to ignore inflexions in the count
     * @return the resulting bracket count, a positive value means we've
     *         encountered more opening than closing brackets
     */
    private int getBracketCount(IDocument document, DocumentCommand command,
            int start, int end, boolean ignoreInflexions) {
        int bracketcount = 0;
        if (command != null)
            scanner.setRange(document, command, start, end - start);
        else
            scanner.setRange(document, start, end - start);

        while (true) {
            IToken token = scanner.nextToken();
            if (token.isEOF())
                break;

            if (token.isOther()) {
                IndentType type = (IndentType) token.getData();
                if (type == IndentType.INCREMENT) {
                    ++bracketcount;
                } else if (type == IndentType.DECREMENT) {
                    --bracketcount;
                } else if ((type == IndentType.INFLEXION) && ignoreInflexions) {
                    ++bracketcount;
                } else if ((type == IndentType.INFLEXION) && !ignoreInflexions) {
                    --bracketcount;
                }
            }
        }
        return bracketcount;
    }

    /**
     * Returns the indentation of the specified line in <code>document</code>.
     * 
     * @param document
     *            - the document being parsed
     * @param line
     *            - the line number being searched
     * @return the string containing the indentation from the specified line
     */
    private String getIndentOfLine(IDocument document, int line)
            throws BadLocationException {
        if (line > -1) {
            int start = document.getLineOffset(line);
            int end = start + document.getLineLength(line);
            int whiteend = findEndOfWhiteSpace(document, start, end);
            return document.get(start, whiteend - start);
        }
        return "";
    }

    /**
     * Set the rules that will be used in a document scanner to identify where
     * indentations should occur. Typically you'd have one rule to describe each
     * type of indentation.
     * 
     * @param rules
     *            the list of rules
     * @see IndentType
     */
    public void setRules(IRule[] rules) {
        scanner.setRules(rules);
    }

    /**
     * Set the indent of the current line when the user hits a key. The indent
     * will either be unchanged or decremented if the user types
     * 
     * @param document
     *            the document being parsed
     * @param c
     *            the command being performed
     */
    protected void smartIndentAfterKeypress(IDocument document,
            DocumentCommand c) {
        if ((c.offset == -1) || (document.getLength() == 0))
            return;
        try {
            StringBuffer buf = new StringBuffer();
            int p = c.offset == document.getLength() ? c.offset - 1 : c.offset;
            int line = document.getLineOfOffset(p);
            int start = document.getLineOffset(line);
            int whiteEnd = findEndOfWhiteSpace(document, start, c.offset);

            int bracketCount = getBracketCount(document, c, start, c.offset,
                    false);
            buf.append(generateIndentation(getIndentOfLine(document, line),
                    bracketCount >= 0 ? 0 : -1));
            buf.append(document.get(whiteEnd, c.offset - whiteEnd));
            buf.append(c.text);
            // Alter the command
            c.length = c.offset - start;
            c.offset = start;
            c.text = buf.toString();
        } catch (BadLocationException x) {
            x.printStackTrace();
        }
    }

    /**
     * Set the indent of a new line when the user hits carriage return. The new
     * indent will either be the same as the previous line or incremented if the
     * user has hit carriage return on a line that contains a incrementing
     * keyword.
     * 
     * @param document
     *            the document being parsed
     * @param c
     *            the command being performed
     */
    protected void smartIndentAfterNewLine(IDocument document, DocumentCommand c) {
        if ((c.offset == -1) || (document.getLength() == 0))
            return;
        try {
            StringBuffer buf = new StringBuffer(c.text);
            int p = c.offset == document.getLength() ? c.offset - 1 : c.offset;
            int line = document.getLineOfOffset(p);
            int start = document.getLineOffset(line);
            int bracketCount = getBracketCount(document, null, start, c.offset,
                    true);
            buf.append(generateIndentation(getIndentOfLine(document, line),
                    bracketCount <= 0 ? 0 : 1));
            c.text = buf.toString();
        } catch (BadLocationException x) {
            x.printStackTrace();
        }
    }

    /**
     * Strips any characters off the end of an indentation that exceed a
     * specified maximum visual indentation length.
     * 
     * @param indent
     *            the string containing the indentation to measure
     * @param max
     *            the maximum visual indentation length
     * @param tabSize
     *            the visual size of tab characters
     * @return a string containing the stripped indentation
     */
    private String stripExtraChars(String indent, int max, int tabSize) {
        int measured = 0;
        int i = 0;
        for (; (measured < max) && (i < indent.length()); i++) {
            char ch = indent.charAt(i);
            switch (ch) {
            case '\t':
                if (tabSize > 0) {
                    int reminder = measured % tabSize;
                    measured += tabSize - reminder;
                }
                break;
            case ' ':
                measured++;
                break;
            }
        }
        return indent.substring(0, measured > max ? i - 1 : i);
    }
}
