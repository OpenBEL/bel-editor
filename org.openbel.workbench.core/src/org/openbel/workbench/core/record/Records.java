package org.openbel.workbench.core.record;

import static org.openbel.workbench.core.common.BELUtilities.closeSilently;
import static org.openbel.workbench.core.common.BELUtilities.computeHash64;
import static org.openbel.workbench.core.common.BELUtilities.constrainedHashMap;
import static org.openbel.workbench.core.common.BELUtilities.readable;
import static org.openbel.workbench.core.common.PathConstants.RECORD_EXTENSION;
import static org.openbel.workbench.core.record.RecordMode.READ_ONLY;
import static org.openbel.workbench.core.record.RecordMode.READ_WRITE;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.openbel.workbench.core.common.InvalidArgument;
import org.openbel.workbench.core.common.PathConstants;
import org.openbel.workbench.core.index.*;
import org.openbel.workbench.core.record.AnnotationRecordFile.AnnotationEntry;
import org.openbel.workbench.core.record.AnnotationRecordFile.AnnotationRecord;
import org.openbel.workbench.core.record.NamespaceRecordFile.NamespaceEntry;
import org.openbel.workbench.core.record.NamespaceRecordFile.NamespaceRecord;

public final class Records {
    private final File recordsLocation;
    private final RecordFilter filter;

    public Records(final File recordsLocation)
            throws IOException {
        this.recordsLocation = recordsLocation;
        this.filter = new RecordFilter();
    }

    public void build(final ResourceIndex index) throws IOException {
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
                } while (!line.startsWith("[Values]"));

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

                // read entries and stuff in record
                nsrec = new NamespaceRecordFile(new File(path),
                        READ_WRITE, new NamespaceRecord(new StringColumn(
                                maxlength)));
                for (final NamespaceEntry e : entries) {
                    if (e.getValue() == null || e.getValue().length() == 0) {
                        System.out.println("Invalid entry!");
                    }
                    nsrec.append(e);
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
                } while (!line.startsWith("[Values]"));

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

                // read entries and stuff in record
                anrec = new AnnotationRecordFile(new File(path),
                        READ_WRITE, new AnnotationRecord(new StringColumn(
                                maxlength)));
                for (final AnnotationEntry e : entries) {
                    if (e.getValue() == null || e.getValue().length() == 0) {
                        System.out.println("Invalid entry!");
                    }
                    anrec.append(e);
                }
                // close file resources
            } finally {
                closeSilently(rdr);
                if (nsrec != null) {
                    closeSilently(nsrec.raf);
                }
            }
        }
    }

    public List<String> retrieve(final String resource)
            throws IOException {
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

        if (resource.endsWith(".belanno")) {
            return retrieveAnnotation(recFile);
        } else if (resource.endsWith(".belns")) {
            return retrieveNamespace(recFile);
        }

        return null;
    }

    private List<String> retrieveAnnotation(final File recFile) {
        final RecordFile rec = new RecordFile(recFile, READ_ONLY,
                AnnotationRecord.hashCol, new StringColumn());

        final AnnotationRecord nsrecord = new AnnotationRecord(rec.recordSize,
                new StringColumn());
        Iterator<byte[]> recit = rec.iterator();
        AnnotationEntry e = new AnnotationEntry();
        final List<String> entries = new ArrayList<String>((int) rec.recordCt);
        while (recit.hasNext()) {
            byte[] next = recit.next();
            nsrecord.readToEntry(next, e);
            entries.add(e.value);
        }

        return entries;
    }

    private List<String> retrieveNamespace(final File recFile)
            throws IOException {
        final RecordFile rec = new RecordFile(recFile, READ_ONLY,
                NamespaceRecord.hashCol, NamespaceRecord.encCol,
                new StringColumn());

        final NamespaceRecord nsrecord = new NamespaceRecord(rec.recordSize,
                new StringColumn());
        Iterator<byte[]> recit = rec.iterator();
        NamespaceEntry e = new NamespaceEntry();
        final List<String> entries = new ArrayList<String>();
        while (recit.hasNext()) {
            byte[] next = recit.next();
            nsrecord.readToEntry(next, e);
            entries.add(e.value);
        }

        return entries;
    }

    public static void main(final String[] args) throws IOException,
            XMLStreamException, FactoryConfigurationError {
        long start = System.currentTimeMillis();

        Records recs = new Records(new File("/home/tony/tmp"));
        ResourceIndex ri = Parser.getInstance().parse(
                new URL("file:///home/tony/tmp/index.xml"));
        recs.build(ri);

        long write_end = System.currentTimeMillis();

        final List<AnnotationInfo> anl = ri.getAnnotations();
        final List<NamespaceInfo> nsl = ri.getNamespaces();
        int c = anl.size() + nsl.size();
        final Map<Resource, List<String>> catalog = constrainedHashMap(c);

        final List<Resource> resources = new ArrayList<Resource>(c);
        resources.addAll(anl);
        resources.addAll(nsl);

        for (final Resource r : resources) {
            catalog.put(r, recs.retrieve(r.getResourceLocation()));
        }

        long read_end = System.currentTimeMillis();

        for (final Resource r : catalog.keySet()) {
            System.out.println("for resource: " + r);
            List<String> values = catalog.get(r);
            for (final String val : values) {
                System.out.println(val);
            }
        }

        System.out.println("Write in "
                + ((write_end - start) / (float) 1000)
                + " seconds.");
        System.out.println("Read in "
                + ((read_end - write_end) / (float) 1000)
                + " seconds.");
    }

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
