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
import static java.nio.ByteBuffer.allocate;

import java.nio.ByteBuffer;

/**
 * Represents a {@link Column} of type {@link Long} sized to 8 bytes.
 */
public class LongColumn extends Column<Long> {
    private static final LongColumn selfNonNull;
    private static final LongColumn selfNull;
    private static final byte space = 8;
    private static final byte[] nullValue;
    static {
        selfNonNull = new LongColumn(false);
        selfNull = new LongColumn(true);
        final ByteBuffer buffer = allocate(space);
        buffer.putLong(0);
        nullValue = buffer.array();
    }

    /**
     * Creates a long column with a size of {@code 8} bytes.
     */
    private LongColumn(boolean nullable) {
        super(space, nullable);
    }

    /**
     * Returns the {@link LongColumn non-null long column} singleton.
     *
     * @return {@link LongColumn non-null long column}
     */
    public static LongColumn nonNullLongColumn() {
        return selfNonNull;
    }

    /**
     * Returns the {@link LongColumn nullable long column} singleton.
     *
     * @return {@link LongColumn nullable long column}
     */
    public static LongColumn nullLongColumn() {
        return selfNull;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long decodeData(byte[] buffer) {
        final ByteBuffer bytebuf = allocate(size);
        // buffer is guaranteed non-null and proper length
        bytebuf.put(buffer);
        bytebuf.rewind();
        return bytebuf.getLong();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] encodeType(Long t) {
        final ByteBuffer buffer = allocate(size);
        // t is guaranteed non-null by superclass
        buffer.putLong(t);
        return buffer.array();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getNullValue() {
        final byte[] ret = new byte[size];
        arraycopy(nullValue, 0, ret, 0, size);
        return ret;
    }
}
