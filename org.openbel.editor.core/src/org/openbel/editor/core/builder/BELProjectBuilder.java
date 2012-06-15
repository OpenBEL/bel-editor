/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.builder;

import static org.eclipse.core.resources.IMarker.LINE_NUMBER;
import static org.eclipse.core.resources.IMarker.MESSAGE;
import static org.eclipse.core.resources.IMarker.SEVERITY;
import static org.eclipse.core.resources.IMarker.SEVERITY_ERROR;
import static org.eclipse.core.resources.IMarker.SEVERITY_WARNING;
import static org.eclipse.core.resources.IResourceDelta.ADDED;
import static org.eclipse.core.resources.IResourceDelta.CHANGED;
import static org.eclipse.core.resources.IResourceDelta.REMOVED;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class BELProjectBuilder extends IncrementalProjectBuilder {
    public static final String BUILDER_ID = "org.openbel.editor.belProjectBuilder";
    private static final String MARKER_TYPE = "org.openbel.editor.xmlProblem";
    private SAXParserFactory parserFactory;

    class SampleDeltaVisitor implements IResourceDeltaVisitor {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean visit(IResourceDelta delta) throws CoreException {
            IResource resource = delta.getResource();
            switch (delta.getKind()) {
            case ADDED:
                // handle added resource
                checkXML(resource);
                break;
            case REMOVED:
                // handle removed resource
                break;
            case CHANGED:
                // handle changed resource
                checkXML(resource);
                break;
            }
            // return true to continue visiting children.
            return true;
        }
    }

    class SampleResourceVisitor implements IResourceVisitor {
        @Override
        public boolean visit(IResource resource) {
            checkXML(resource);
            // return true to continue visiting children.
            return true;
        }
    }

    class XMLErrorHandler extends DefaultHandler {

        private IFile file;

        public XMLErrorHandler(IFile file) {
            this.file = file;
        }

        private void addMarker(SAXParseException e, int severity) {
            BELProjectBuilder.this.addMarker(file, e.getMessage(),
                    e.getLineNumber(), severity);
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            addMarker(exception, SEVERITY_ERROR);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            addMarker(exception, SEVERITY_ERROR);
        }

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            addMarker(exception, SEVERITY_WARNING);
        }
    }

    private void addMarker(IFile file, String message, int lineNumber,
            int severity) {
        try {
            IMarker marker = file.createMarker(MARKER_TYPE);
            marker.setAttribute(MESSAGE, message);
            marker.setAttribute(SEVERITY, severity);
            if (lineNumber == -1) {
                lineNumber = 1;
            }
            marker.setAttribute(LINE_NUMBER, lineNumber);
        } catch (CoreException e) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
            throws CoreException {
        if (kind == FULL_BUILD) {
            fullBuild(monitor);
        } else {
            IResourceDelta delta = getDelta(getProject());
            if (delta == null) {
                fullBuild(monitor);
            } else {
                incrementalBuild(delta, monitor);
            }
        }
        return null;
    }

    void checkXML(IResource resource) {
        if (resource instanceof IFile && resource.getName().endsWith(".xml")) {
            IFile file = (IFile) resource;
            deleteMarkers(file);
            XMLErrorHandler reporter = new XMLErrorHandler(file);
            try {
                getParser().parse(file.getContents(), reporter);
            } catch (Exception e1) {
            }
        }
    }

    private void deleteMarkers(IFile file) {
        try {
            file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
        } catch (CoreException ce) {
        }
    }

    protected void fullBuild(final IProgressMonitor monitor) {
        try {
            getProject().accept(new SampleResourceVisitor());
        } catch (CoreException e) {
        }
    }

    private SAXParser getParser() throws ParserConfigurationException,
            SAXException {
        if (parserFactory == null) {
            parserFactory = SAXParserFactory.newInstance();
        }
        return parserFactory.newSAXParser();
    }

    protected void incrementalBuild(IResourceDelta delta,
            IProgressMonitor monitor) throws CoreException {
        // the visitor does the work.
        delta.accept(new SampleDeltaVisitor());
    }
}
