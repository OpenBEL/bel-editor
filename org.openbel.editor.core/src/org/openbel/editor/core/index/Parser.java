/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.index;

import static javax.xml.stream.XMLInputFactory.IS_NAMESPACE_AWARE;
import static org.openbel.editor.core.common.BELUtilities.closeSilently;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.openbel.editor.core.common.InvalidArgument;

/**
 * {@link Parser} defines an XML parser capable of parsing a resource index
 * document from the BEL Framework.
 *
 * <p>
 * Validation of the defined schema is not currently supported.
 * </p>
 */
public class Parser {
    private static final String ANNOTATION_TAG = "annotationdefinition";
    private static final String NAMESPACE_TAG = "namespace";
    private static Parser instance;
    private final XMLInputFactory xmlf;

    /**
     * Parse a resource index document for its annotations and namespaces.  A
     * {@link ResourceIndex resource index} is then created from them.
     *
     * @param url the {@link URL url} of the resource index to parse
     * @return the {@link ResourceIndex resource index} created from the
     * annotations and namespaces
     * @throws IOException Thrown if an IO error occurred while reading from
     * the url connection
     * @throws XMLStreamException Thrown if the XML stream could not be parsed
     * successfully
     * @throws FactoryConfigurationError Thrown if the {@link XMLInputFactory}
     * if configured incorrectly
     */
    public ResourceIndex parse(final URL url) throws IOException,
    XMLStreamException, FactoryConfigurationError {
        if (url == null) {
            throw new InvalidArgument("url", url);
        }

        final URLConnection urlc = url.openConnection();
        final InputStream input = urlc.getInputStream();

        final List<AnnotationInfo> anl = new ArrayList<AnnotationInfo>();
        final List<NamespaceInfo> nsl = new ArrayList<NamespaceInfo>();

        XMLStreamReader xmlr = null;
        try {
            xmlr = xmlf.createXMLStreamReader(input);
            while (xmlr.hasNext()) {

                int ev = xmlr.next();
                switch (ev) {
                case XMLStreamConstants.START_ELEMENT:
                    final String localTag = xmlr.getLocalName();
                    if (ANNOTATION_TAG.equals(localTag)) {
                        String ares = xmlr.getAttributeValue(0);
                        anl.add(parseAnnotationResource(ares));
                        break;
                    } else if (NAMESPACE_TAG.equals(localTag)) {
                        String nres = xmlr.getAttributeValue(0);
                        nsl.add(parseNamespaceResource(nres));
                        break;
                    }
                }
            }
        } finally {
            // explicit close because XMLStreamReader does not extend Closeable
            if (xmlr != null) {
                try {
                    xmlr.close();
                } catch (XMLStreamException e) {
                }
            }
            closeSilently(input);
        }

        return new ResourceIndex(anl, nsl);
    }

    /**
     * Return the singleton instance of {@link Parser} creating if necessary.
     *
     * @return the {@link Parser parser} singleton instance
     */
    public static synchronized Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }

        return instance;
    }

    /**
     * Private constructor that creates an {@link XMLInputFactory}.  Validation
     * of the defined schema is not currently supported.
     */
    private Parser() {
        xmlf = XMLInputFactory.newInstance();
        xmlf.setProperty(IS_NAMESPACE_AWARE, true);
    }

    private NamespaceInfo parseNamespaceResource(final String resource)
            throws IOException {
        final URL url = new URL(resource);
        final URLConnection urlc = url.openConnection();

        BufferedReader rdr = null;
        String keyword, name, description, queryValueURL;
        keyword = name = description = queryValueURL = null;
        try {
            rdr = new BufferedReader(new InputStreamReader(
                    urlc.getInputStream()));
            String line;
            while ((line = rdr.readLine()) != null) {

                // break if values block hit
                if (line.startsWith("[Values]")) {
                    break;
                }

                if (line.startsWith("Keyword=")) {
                    keyword = line.substring(8).trim();
                } else if (line.startsWith("NameString=")) {
                    name = line.substring(11).trim();
                } else if (line.startsWith("DescriptionString=")) {
                    description = line.substring(18).trim();
                } else if (line.startsWith("QueryValueURL=")) {
                    queryValueURL = line.substring(14);
                }
            }

            return new NamespaceInfo(resource, keyword, name, description,
                    queryValueURL);
        } finally {
            closeSilently(rdr);
        }
    }

    private AnnotationInfo parseAnnotationResource(final String resource)
            throws IOException {
        final URL url = new URL(resource);
        final URLConnection urlc = url.openConnection();

        BufferedReader rdr = null;
        String keyword, description, usage;
        keyword = description = usage = null;
        try {
            rdr = new BufferedReader(new InputStreamReader(
                    urlc.getInputStream()));
            String line;
            while ((line = rdr.readLine()) != null) {
                // break if values block hit
                if (line.startsWith("[Values]")) {
                    break;
                }

                if (line.startsWith("Keyword=")) {
                    keyword = line.substring(8).trim();
                } else if (line.startsWith("DescriptionString=")) {
                    description = line.substring(18).trim();
                } else if (line.startsWith("UsageString=")) {
                    usage = line.substring(12).trim();
                }
            }

            return new AnnotationInfo(resource, keyword, description, usage);
        } finally {
            closeSilently(rdr);
        }
    }

    /**
     * Test thread safety of {@link Parser}.
     */
    public static void main(String[] args) throws Exception {
        final URL url = new URL(
                "http://resource.belframework.org/belframework/1.0/index.xml");

        for (int i = 0; i < 10; i++) {
            final Runnable r = new Runnable() {

                @Override
                public void run() {
                    try {
                        ResourceIndex idx = Parser.getInstance().parse(url);
                        System.out.println("Annotations: " + idx.getAnnotations());
                        System.out.println("Namespaces: " + idx.getNamespaces());
                    } catch (Exception e) {
                        final String tname = Thread.currentThread().getName();
                        System.out.println("Error in '"+tname+": " + e.getMessage());
                    }
                }
            };
            new Thread(r).start();
        }
    }
}
