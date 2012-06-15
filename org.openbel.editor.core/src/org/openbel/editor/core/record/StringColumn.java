/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.record;

import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;
import static java.util.regex.Pattern.compile;
import static org.openbel.editor.core.common.Strings.UTF_8;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * Represents a {@link Column} of type {@link String} where the length of the
 * string is arbitrary.
 * <p>
 * This column implementation pads strings with spaces during encoding if the
 * strings are less than the column's size. During encoding, the padding is
 * removed. Encoding strings with length greater than the column's size will
 * result in truncation.
 * </p>
 *
 * @author Nick Bargnesi
 */
public class StringColumn extends Column<String> {
    static final byte space = 32;
    static final Pattern pattern;
    static final byte[] nullValue;
    static {
        pattern = compile("[ ]*$");
        nullValue = new byte[space];
        fill(nullValue, space);
        nullValue[0] = (byte) 0;
    }

    /**
     * Creates a non-null string column of the specified {@code length}.
     *
     * @param length Maximum length of the string
     */
    public StringColumn(int length) {
        super(length, false);
    }

    /**
     * Creates a string column of the specified {@code length} and nullability.
     *
     * @param length Maximum length of the string
     * @param nullable {@code true} if the columns is nullable, {@code false}
     * otherwise
     */
    public StringColumn(int length, boolean nullable) {
        super(length, nullable);
    }

    /**
     * Creates a string column of an unknown length and the specified
     * nullability.
     *
     * @param nullable {@code true} if the columns is nullable, {@code false}
     * otherwise
     */
    public StringColumn(boolean nullable) {
        super(nullable);
    }

    /**
     * Creates a non-null string column of an unknown length.
     */
    public StringColumn() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String decodeData(byte[] buffer) {
        // buffer is guaranteed non-null and proper length
        String ret = new String(buffer);
        ret = pattern.matcher(ret).replaceAll("");
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] encodeType(String t) {
        byte[] ret = new byte[size];
        fill(ret, space);
        byte[] tbytes;
        try {
            tbytes = t.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }

        int length;
        if (tbytes.length <= size) {
            length = tbytes.length;
        } else {
            length = size;
        }

        arraycopy(tbytes, 0, ret, 0, length);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getNullValue() {
        byte[] ret = new byte[size];
        arraycopy(nullValue, 0, ret, 0, size);
        return ret;
    }
}
