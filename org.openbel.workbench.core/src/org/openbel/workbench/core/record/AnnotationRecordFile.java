/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.record;

import static java.lang.String.format;
import static org.openbel.workbench.core.record.LongColumn.nonNullLongColumn;

import java.io.File;
import java.io.IOException;

import org.openbel.workbench.core.common.InvalidArgument;

public class AnnotationRecordFile extends RecordFile {
    private final AnnotationRecord anrec;

    public AnnotationRecordFile(final File path, final RecordMode mode,
            final AnnotationRecord anrec) {
        super(path, mode, anrec.columns);
        this.anrec = anrec;
    }

    public AnnotationEntry readEntry(final long record) throws IOException {
        final byte[] recbytes = super.read(record);
        return anrec.read(recbytes);
    }

    public void readToEntry(final long record, final AnnotationEntry e)
            throws IOException {
        final byte[] recbytes = super.read(record);
        anrec.readTo(recbytes, e);
    }

    public void append(final AnnotationEntry e) throws IOException {
        final byte[] ebytes = anrec.write(e);
        append(ebytes);
    }

    public static class AnnotationRecord extends Record<AnnotationEntry> {
        static final LongColumn hashCol = nonNullLongColumn();
        StringColumn valCol = new StringColumn();

        public AnnotationRecord(final StringColumn valCol) {
            super(hashCol, valCol);
            this.valCol = valCol;
        }

        public AnnotationRecord(final int recordSize, final StringColumn valCol) {
            super(recordSize, hashCol, valCol);
            this.valCol = valCol;
        }

        @Override
        protected AnnotationEntry read(byte[] buffer) {
            long hash = hashCol.decode(readColumn(buffer, 0));
            String value = valCol.decode(readColumn(buffer, 1));
            return new AnnotationEntry(hash, value);
        }

        @Override
        protected void readTo(byte[] buffer, AnnotationEntry e) {
            e.hash = hashCol.decode(readColumn(buffer, 0));
            e.value = valCol.decode(readColumn(buffer, 1));
        }

        @Override
        protected byte[] write(AnnotationEntry e) {
            byte[] record = emptyRecord();
            writeColumn(hashCol.encode(e.hash), 0, record);
            writeColumn(valCol.encode(e.value), 1, record);
            return record;
        }

        @Override
        protected void writeTo(AnnotationEntry e, byte[] buffer) {
            // validate byte array length matches expected record size
            if (buffer.length != recordSize) {
                final String fmt =
                        "invalid buffer length of %d bytes, expected %d";
                final String msg = format(fmt, buffer.length, recordSize);
                throw new InvalidArgument(msg);
            }

            writeColumn(hashCol.encode(e.hash), 0, buffer);
            writeColumn(valCol.encode(e.value), 1, buffer);
        }
    }

    public static final class AnnotationEntry {
        long hash;
        String value;

        public AnnotationEntry() {

        }

        public AnnotationEntry(long hash, String value) {
            this.hash = hash;
            this.value = value;
        }

        public long getHash() {
            return hash;
        }

        public String getValue() {
            return value;
        }
    }
}
