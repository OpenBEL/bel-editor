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

import static org.openbel.editor.core.common.BELUtilities.*;
import static org.openbel.editor.core.common.PathConstants.RECORD_EXTENSION;
import static org.openbel.editor.core.record.RecordMode.READ_ONLY;
import static org.openbel.editor.core.record.RecordMode.READ_WRITE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openbel.editor.core.common.InvalidArgument;
import org.openbel.editor.core.common.PathConstants;
import org.openbel.editor.core.index.AnnotationInfo;
import org.openbel.editor.core.index.NamespaceInfo;
import org.openbel.editor.core.index.ResourceIndex;
import org.openbel.editor.core.record.AnnotationRecordFile.AnnotationEntry;
import org.openbel.editor.core.record.AnnotationRecordFile.AnnotationRecord;
import org.openbel.editor.core.record.NamespaceRecordFile.NamespaceEntry;
import org.openbel.editor.core.record.NamespaceRecordFile.NamespaceRecord;

/**
 * {@link Records} provides an interface to the {@link RecordFile record file}
 * implementations for namespace and annotation data.  This server as the only
 * API for {@link RecordFile record file} access.
 */
public final class Records {
    private final File recordsLocation;
    private final RecordFilter filter;
    private final Map<String, RecordFile> recCache;

    /**
     * Construct {@link Records} API.
     *
     * @param recordsLocation the {@link File records location} where every
     * {@link RecordFile record files} can be found, which cannot be
     * {@code null}
     * @param index the {@link ResourceIndex resource index} holding what
     * namespaces and annotations are available, which cannot be {@code null}
     */
    public Records(final File recordsLocation, final ResourceIndex index) {
        this.recordsLocation = recordsLocation;
        this.filter = new RecordFilter();

        // create a constrained record cache
        int total = index.getAnnotations().size()
                + index.getNamespaces().size();
        recCache = constrainedHashMap(total);
    }

    /**
     * Builds the {@link RecordFile record files} for all namespaces and
     * annotations in the {@link ResourceIndex resource index}.  The
     * {@link RecordFile record files} will be saved to the file path pointed
     * to by {@link Records#recordsLocation}.
     *
     * @param index the {@link ResourceIndex resource index} holding what
     * namespaces and annotations are available, which cannot be {@code null}
     * @throws IOException Thrown if an error IO occurred reading BEL resources
     * or writing {@link RecordFile record files}.
     */
    public void build(final ResourceIndex index)
            throws IOException {
        if (index == null) {
            return;
        }
        if (!readable(recordsLocation)) {
            throw new IOException("Cannot read records locations: "
                    + recordsLocation.getAbsolutePath());
        }

        // remove existing record files
        for (final File record : recordsLocation.listFiles(filter)) {
            if (!record.delete()) {
                throw new IOException("Cannot remove existing record file: "
                        + record.getAbsolutePath());
            }
        }

        // process namespaces
        NamespaceRecordFile nsrec = null;
        final StringBuilder b = new StringBuilder();
        List<NamespaceInfo> ns = index.getNamespaces();
        for (final NamespaceInfo s : ns) {
            final URL url = new URL(s.getResourceLocation());
            final URLConnection urlc = url.openConnection();

            BufferedReader rdr = null;
            try {
                rdr = new BufferedReader(new InputStreamReader(
                        urlc.getInputStream()));
                String line;

                // read lines until data starts
                do {
                    line = rdr.readLine();
                } while (line != null && !line.startsWith("[Values]"));

                b.setLength(0);
                final String path = b
                        .append(recordsLocation.getAbsolutePath())
                        .append(File.separator)
                        .append(Hasher.INSTANCE.hashValue(s
                                .getResourceLocation()))
                        .append(RECORD_EXTENSION).toString();

                // read in all data and calculate max length
                int maxlength = 0;
                List<NamespaceEntry> entries = new ArrayList<NamespaceEntry>();
                while ((line = rdr.readLine()) != null) {
                    int length = line.split("\\|")[0].length();
                    if (length > maxlength) {
                        maxlength = length;
                    }

                    String[] tokens = line.split("\\|");
                    final String val = tokens[0];
                    String enc = tokens[1];

                    // FIXME Not encoding the encoding value as a bitset
                    entries.add(new NamespaceEntry(computeHash64(val), enc
                            .hashCode(), val));
                }

                // setup namespace record based on maxlength and then read
                // entries and stuff in record
                nsrec = new NamespaceRecordFile(new File(path),
                        READ_WRITE, new NamespaceRecord(new StringColumn(
                                maxlength)));
                for (final NamespaceEntry e : entries) {
                    if (hasLength(e.getValue())) {
                        nsrec.append(e);
                    }
                }
                // close file resources
            } finally {
                closeSilently(rdr);
                if (nsrec != null) {
                    closeSilently(nsrec.raf);
                }
            }
        }

        // process annotations
        AnnotationRecordFile anrec = null;
        List<AnnotationInfo> an = index.getAnnotations();
        for (final AnnotationInfo s : an) {
            final URL url = new URL(s.getResourceLocation());
            final URLConnection urlc = url.openConnection();

            BufferedReader rdr = null;
            try {
                rdr = new BufferedReader(new InputStreamReader(
                        urlc.getInputStream()));
                String line;

                // read lines until data starts
                do {
                    line = rdr.readLine();
                } while (line != null && !line.startsWith("[Values]"));

                b.setLength(0);
                final String path = b
                        .append(recordsLocation.getAbsolutePath())
                        .append(File.separator)
                        .append(Hasher.INSTANCE.hashValue(s
                                .getResourceLocation()))
                        .append(RECORD_EXTENSION).toString();

                // read in all data and calculate max length
                int maxlength = 0;
                List<AnnotationEntry> entries = new ArrayList<AnnotationEntry>();
                while ((line = rdr.readLine()) != null) {
                    int length = line.split("\\|")[0].length();
                    if (length > maxlength) {
                        maxlength = length;
                    }

                    String[] tokens = line.split("\\|");
                    final String val = tokens[0];
                    entries.add(new AnnotationEntry(computeHash64(val), val));
                }

                // setup annotation record file with max length then read
                // entries and stuff in record
                anrec = new AnnotationRecordFile(new File(path),
                        READ_WRITE, new AnnotationRecord(new StringColumn(
                                maxlength)));
                for (final AnnotationEntry e : entries) {
                    if (hasLength(e.getValue())) {
                        anrec.append(e);
                    }
                }

                entries.clear();
                // close file resources
            } finally {
                closeSilently(rdr);
                if (nsrec != null) {
                    closeSilently(nsrec.raf);
                }
            }
        }
    }

    /**
     * Find the count of records in the {@link RecordFile record file} for
     * the resource location.
     *
     * @param resource the {@link String resource location}
     * @return {@code long} count of records
     * @throws IOException Thrown is an IO error occurred reading the
     * {@link RecordFile record file}.
     */
    public long count(final String resource) throws IOException {
        final RecordFile rec = getRecordFile(resource);
        return rec.recordCt;
    }

    /**
     * Retrieves the value for a specific resource at the given record number.
     *
     * @param resource the {@link String resource location}
     * @param record {@code int} record number
     * @return the {@link String resource value}
     * @throws IOException Thrown if an IO error occurred reading the
     * {@link RecordFile record file}.
     * @throws UnsupportedOperationException Thrown if the {@code resource} is
     * not an annotation or namespace location.
     */
    public String retrieve(final String resource, final int record) throws IOException {
        final RecordFile rec = getRecordFile(resource);
        if (resource.endsWith(".belanno")) {
            final AnnotationRecord ar = new AnnotationRecord(rec.recordSize,
                    new StringColumn());
            final AnnotationEntry entry = ar.readBuffer(rec.read(record));
            return entry.value;
        } else if (resource.endsWith(".belns")) {
            final NamespaceRecord nr = new NamespaceRecord(rec.recordSize,
                    new StringColumn());
            final NamespaceEntry entry = nr.readBuffer(rec.read(record));
            return entry.value;
        } else {
            throw new UnsupportedOperationException("Do not support resource '"
                    + resource
                    + "'.  Must be either an Annotation or a Namespace.");
        }
    }

    /**
     * Retrieves the {@link RecordFile record file} for the
     * {@link String resource location}.  First check the
     * {@link Records#recCache} to see if it is already loaded otherwise load
     * the {@link RecordFile record file}.
     *
     * @param resource the {@link String resource location} that identifies the
     * {@link RecordFile record file}
     * @return the {@link RecordFile record file}
     * @throws IOException Thrown if an IO error occurred creating the
     * {@link RecordFile record file}
     * @throws UnsupportedOperationException Thrown if the {@code resource} is
     * not an annotation or namespace location.
     */
    private RecordFile getRecordFile(final String resource) throws IOException {
        RecordFile rec = recCache.get(resource);
        if (rec == null) {
            final File recFile = findRecordFile(resource);

            if (resource.endsWith(".belanno")) {
                rec = new RecordFile(recFile, READ_ONLY, AnnotationRecord.hashCol, new StringColumn());
            } else if (resource.endsWith(".belns")) {
                rec = new RecordFile(recFile, READ_ONLY, NamespaceRecord.hashCol,
                        NamespaceRecord.encCol,
                        new StringColumn());
            } else {
                throw new UnsupportedOperationException("Do not support resource '"
                        + resource
                        + "'.  Must be either an Annotation or a Namespace.");
            }

            recCache.put(resource, rec);
        }

        return rec;
    }

    /**
     * Finds the {@link File record file's file} for the resource location.
     *
     * @param resource the {@link String resource location}, which cannot be
     * {@code null}
     * @return the {@link File record file's file}
     * @throws InvalidArgument Thrown if {@code resource} is null
     * @throws IOException Thrown if an the {@link File records location} or
     * the {@link RecordFile record file} could not be read.
     */
    private File findRecordFile(final String resource) throws IOException {
        if (resource == null) {
            throw new InvalidArgument("resource", resource);
        }

        if (!readable(recordsLocation)) {
            throw new IOException("Cannot read records locations: "
                    + recordsLocation.getAbsolutePath());
        }

        final StringBuilder b = new StringBuilder();
        final String path = b
                .append(recordsLocation.getAbsolutePath())
                .append(File.separator)
                .append(Hasher.INSTANCE.hashValue(resource))
                .append(RECORD_EXTENSION).toString();

        final File recFile = new File(path);
        if (!readable(recFile)) {
            throw new IOException("Cannot read record: "
                    + recFile.getAbsolutePath());
        }
        return recFile;
    }

    /**
     * {@link RecordFilter} restricts {@link File files} by
     * {@link PathConstants#RECORD_EXTENSION}.
     */
    private class RecordFilter implements FilenameFilter {

        /**
         * Filter to include files ending with the
         * {@link PathConstants#RECORD_EXTENSION}.
         *
         * {@inheritDoc}
         */
        @Override
        public boolean accept(File dir, String name) {
            return name != null && name.endsWith(RECORD_EXTENSION);
        }
    }
}
