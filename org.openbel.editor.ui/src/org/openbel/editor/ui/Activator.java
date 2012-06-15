/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui;

import static java.lang.System.getenv;
import static java.lang.Thread.yield;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.openbel.editor.core.common.BELUtilities.noItems;
import static org.openbel.editor.core.common.BELUtilities.noLength;
import static org.openbel.editor.core.common.BELUtilities.typedList;
import static org.openbel.editor.ui.UIFunctions.*;
import static org.openbel.editor.ui.util.ValidationUtilities.validateFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.openbel.editor.core.Nature;
import org.openbel.editor.core.index.Resource;
import org.openbel.editor.core.index.ResourceIndex;
import org.openbel.editor.core.record.RecordFile;
import org.openbel.editor.ui.text.BELTextTools;
import org.openbel.editor.ui.util.ValidationUtilities.FileState;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends DLTKUIPlugin {
    public static final String ENV_BELFRAMEWORK_HOME;
    private static Activator plugin;
    /** The plug-in identifier: {@value} */
    public static final String PLUGIN_ID = "org.openbel.editor.ui";
    /** {@value} */
    public static final String BF_PREF_KEY = "bel_framework_home_pref";
    /** {@value} */
    public static final String CY_PREF_KEY = "cytoscape_pref";
    private static IWorkbench workbench;
    /** Initialize a single {@link ResourceLoader} instance. */
    private static final ResourceLoader resourceLoader;
    static {
        String s = getenv("BELFRAMEWORK_HOME");
        ENV_BELFRAMEWORK_HOME = s == null ? "" : s;
        resourceLoader = new ResourceLoader();
    }
    private BELTextTools fBELTextTools;
    private Listener listener;

    private ResourceIndex resourceIndex;
    private Map<Resource, RecordFile> recordFiles;
    private List<String> selectedProjects;

    /**
     * Returns the configured BEL framework home.
     * 
     * @return String; may be null
     */
    public String getBELFrameworkHome() {
        return getPreferenceStore().getString(BF_PREF_KEY);
    }

    /**
     * Returns the configured Cytoscape home.
     * 
     * @return String; may be null
     */
    public String getCytoscapeHome() {
        return getPreferenceStore().getString(CY_PREF_KEY);
    }

    /**
     * Returns the {@link Shell shell} associated with the editor's active
     * window.
     * 
     * @return {@link Shell}; may be null
     */
    public Shell getShell() {
        IWorkbench workbench = this.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window == null) return null;
        return window.getShell();
    }

    /**
     * Returns a {@link IProject}, {@link IFolder}, or {@link IFile}, depending
     * on what the provided resource denotes with respect to the
     * {@link Workspace workspace} {@link Workspace#getRoot() root}.
     * 
     * @param resource Non-null {@link String}
     * @return {@link IResource}; may be null
     * @see #denotesFile(String)
     * @see #denotesFolder(String)
     * @see #denotesProject(String)
     */
    public IResource getResource(String resource) {
        IWorkspaceRoot root = getWorkspace().getRoot();
        if (denotesProject(resource)) {
            String[] tokens = resource.split("/");
            if (tokens.length < 2) return null;
            return root.getProject(tokens[1]);
        } else if (denotesFolder(resource)) {
            resource = resource.substring(2);
            return root.getFolder(new Path(resource));
        } else if (denotesFile(resource)) {
            resource = resource.substring(2);
            return root.getFile(new Path(resource));
        }
        return null;
    }

    /**
     * Returns all projects that have the BEL {@link Nature}.
     * 
     * @return {@link IProject IProject[]}; non-null, maybe empty
     */
    public IProject[] getBELProjects() {
        List<IProject> ret = new ArrayList<IProject>();
        IProject[] projects = getWorkspace().getRoot().getProjects();
        for (final IProject p : projects) {
            if (!isOpenBELProject(p)) {
                continue;
            }
            ret.add(p);
        }
        return ret.toArray(new IProject[ret.size()]);
    }

    /**
     * Returns all {@link IFile} builders (files with the
     * {@link UIConstants#BUILDER_EXTENSION builder extension}).
     * 
     * @return {@code IFile[]}; may be zero-length
     */
    public IFile[] getBuilders() {
        List<IFile> ret = new ArrayList<IFile>();
        IProject[] projects = getOpenedBELProjects();

        for (final IProject p : projects) {
            IResource[] members;
            try {
                members = p.members();
            } catch (CoreException e) {
                logError(e);
                continue;
            }
            List<IFile> files = typedList(asList(members), IFile.class);
            for (IFile file : files) {
                if (isBuilder(file)) {
                    ret.add(file);
                }
            }
        }
        return ret.toArray(new IFile[ret.size()]);
    }

    /**
     * Gets the first available builder for a project.
     * 
     * @param p {@link IProject}
     * @return {@link IFile}; may be null
     */
    public IFile getBuilder(IProject p) {
        IResource[] members;
        try {
            members = p.members();
        } catch (CoreException e) {
            logError(e);
            return null;
        }
        List<IFile> files = typedList(asList(members), IFile.class);
        for (IFile file : files) {
            if (isBuilder(file)) {
                return file;
            }
        }
        return null;
    }

    /**
     * Returns all {@link IFile} system configurations (files with the
     * {@link UIConstants#SYSCFG_EXTENSION system configuration extension}) in
     * the specified project..
     * 
     * @return {@code IFile[]}; may be zero-length
     */
    public IFile[] getSystemConfigurations(IProject p) {
        List<IFile> ret = new ArrayList<IFile>();
        IResource[] members;
        try {
            members = p.members();
        } catch (CoreException e) {
            logError(e);
            return new IFile[0];
        }
        List<IFile> files = typedList(asList(members), IFile.class);
        for (IFile file : files) {
            if (isSystemConfiguration(file)) {
                ret.add(file);
            }
        }
        return ret.toArray(new IFile[0]);
    }

    /**
     * Returns the closed projects that have the BEL {@link Nature}.
     * 
     * @return {@link IProject IProject[]}; non-null, maybe empty
     */
    public IProject[] getClosedBELProjects() {
        List<IProject> ret = new ArrayList<IProject>();
        IProject[] projects = getWorkspace().getRoot().getProjects();
        for (final IProject p : projects) {
            if (!isOpenBELProject(p)) {
                continue;
            }
            if (p.isOpen()) {
                continue;
            }
            ret.add(p);
        }
        return ret.toArray(new IProject[ret.size()]);
    }

    /**
     * Returns all {@link IFolder} document groups (directories underneath each
     * project).
     * 
     * @return {@code IFolder[]}; may be zero-length
     */
    public IFolder[] getDocumentGroups() {
        List<IFolder> ret = new ArrayList<IFolder>();
        IProject[] projects = getOpenedBELProjects();

        for (final IProject p : projects) {
            IResource[] members;
            try {
                members = p.members();
            } catch (CoreException e) {
                logError(e);
                continue;
            }
            List<IFolder> folders = typedList(asList(members), IFolder.class);
            for (IFolder folder : folders) {
                ret.add(folder);
            }
        }
        return ret.toArray(new IFolder[ret.size()]);
    }

    /**
     * Returns the opened projects that have the BEL {@link Nature}.
     * 
     * @return {@link IProject IProject[]}; non-null, maybe empty
     */
    public IProject[] getOpenedBELProjects() {
        List<IProject> ret = new ArrayList<IProject>();
        IProject[] projects = getWorkspace().getRoot().getProjects();
        for (final IProject p : projects) {
            if (!isOpenBELProject(p)) {
                continue;
            }
            if (!p.isOpen()) {
                continue;
            }
            ret.add(p);
        }
        return ret.toArray(new IProject[ret.size()]);
    }

    /**
     * Returns the project by name, regardless of its open/close state.
     * 
     * @param name {@link String} project name
     * @return {@link IProject}; may be null
     */
    public IProject getProject(String name) {
        IProject[] projects = getWorkspace().getRoot().getProjects();
        for (final IProject p : projects) {
            if (p.getName().equals(name)) return p;
        }
        return null;
    }

    /**
     * Returns the loaded {@link ResourceIndex resource index}.
     * 
     * @return {@link ResourceIndex} the resource index or {@code null} if it is
     *         not loaded
     */
    public ResourceIndex getResourceIndex() {
        return resourceIndex;
    }

    /**
     * Returns the {@link RecordFile record files} built from the
     * {@link ResourceIndex resource index}.
     * 
     * @return the {@link Map map} of {@link RecordFile}
     */
    public Map<Resource, RecordFile> getRecordFiles() {
        return recordFiles;
    }

    /**
     * Returns the current projects selected in the project explorer.
     * 
     * @return List of strings; may be empty
     */
    public List<String> getSelectedProjects() {
        if (selectedProjects == null) {
            return emptyList();
        }
        return selectedProjects;
    }

    /**
     * Returns the text tools.
     * 
     * @return {@link BELTextTools}
     */
    public synchronized BELTextTools getTextTools() {
        if (fBELTextTools == null) {
            fBELTextTools = new BELTextTools(true);
        }
        return fBELTextTools;
    }

    /**
     * Returns {@code true} if the project has the {@link Nature#BEL_NATURE},
     * and {@code false} otherwise.
     * 
     * @param p {@link IProject}
     * @return boolean
     */
    public boolean isOpenBELProject(IProject p) {
        try {
            if (p.hasNature(Nature.BEL_NATURE)) {
                return true;
            }
        } catch (CoreException e) {
            logError(e);
        }
        return false;
    }

    /**
     * Reloads the resource view returning {@code success} on success and
     * {@code false} on error.
     * 
     * @return boolean
     */
    public void reloadResources() {
        resourceLoader.loadResourceIndex();
    }

    /**
     * Sets the resource index.
     * 
     * @param resourceIndex
     */
    public void setResourceIndex(final ResourceIndex resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    /**
     * Sets the {@link RecordFile record files} for the resource in the
     * {@link ResourceIndex resource index}.
     * 
     * @param recordFiles the {@link RecordFile record files} map
     */
    public void setRecordFiles(
            final Map<Resource, RecordFile> recordFiles) {
        this.recordFiles = recordFiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext ctxt) throws Exception {
        super.start(ctxt);
        this.listener = new Listener();
        plugin = this;

        runAsync(new Runnable() {
            @Override
            public void run() {
                while (workbench == null) {
                    yield();
                    workbench = getWorkbench();
                }
                IWorkbenchWindow win = workbench.getActiveWorkbenchWindow();
                win = workbench.getActiveWorkbenchWindow();
                ISelectionService selsvc = win.getSelectionService();
                selsvc.addSelectionListener(listener);
                IWorkspace ws = getWorkspace();
                ws.addResourceChangeListener(listener);
            }
        });

        IPreferenceStore ps = getPreferenceStore();
        String bfhome = ps.getString(BF_PREF_KEY);
        if (noLength(bfhome)) {
            bfhome = ENV_BELFRAMEWORK_HOME;
        }

        if (validateFramework(bfhome) != FileState.OK) {
            // TODO should generate editor error here
        } else {
            ps.setValue(BF_PREF_KEY, bfhome);
            reloadResources();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext ctxt) throws Exception {
        plugin = null;
        super.stop(ctxt);
        // XXX cleanup listeners here
    }

    /**
     * Returns the BEL logo.
     * 
     * @return {@link Image}
     */
    public static Image getBELLogo() {
        return getImageDescriptor("icons/logo.png").createImage();
    }

    /**
     * Returns the BEL logo icon.
     * 
     * @return {@link Image}
     */
    public static Image getBELLogoIcon() {
        return getImageDescriptor("icons/bel.gif").createImage();
    }

    /**
     * Returns the BEL Script icon.
     * 
     * @return {@link Image}
     */
    public static Image getBELScriptIcon() {
        return getImageDescriptor("icons/belscript.gif").createImage();
    }

    /**
     * Returns the builder icon.
     * 
     * @return {@link Image}
     */
    public static Image getBuilderIcon() {
        return getImageDescriptor("icons/builder.gif").createImage();
    }

    /**
     * Returns the builder launch icon.
     * 
     * @return {@link Image}
     */
    public static Image getBuilderLaunchIcon() {
        return getImageDescriptor("icons/builder-launch.png").createImage();
    }

    /**
     * Returns the shared instance.
     * 
     * @return {@link Activator}
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns the document group icon.
     * 
     * @return {@link Image}
     */
    public static Image getDocumentGroupIcon() {
        return getImageDescriptor("icons/document-group.png").createImage();
    }

    /**
     * Get an image given a path relative to this plugin.
     * 
     * @param path the image {@link String path}
     * @return an {@link Image image} located at the path
     */
    public static Image getImage(String path) {
        Image image = getDefault().getImageRegistry().get(path);

        if (image != null) {
            return image;
        }

        ImageDescriptor descriptor = getImageDescriptor(path);

        if (descriptor != null) {
            getDefault().getImageRegistry().put(path, descriptor);

            return getDefault().getImageRegistry().get(path);
        }

        return null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * Returns the import icon.
     * 
     * @return {@link Image}
     */
    public static Image getImportIcon() {
        return getImageDescriptor("icons/import.png").createImage();
    }

    /**
     * Returns the {@code .metadata/.plugins/[plugin-id]} path, defers to
     * {@link Activator#getStateLocation()}.
     * 
     * @return {@link IPath}
     */
    public static IPath getPluginLocation() {
        return getDefault().getStateLocation();
    }

    /**
     * Returns the project icon.
     * 
     * @return {@link Image}
     */
    public static Image getProjectIcon() {
        return getImageDescriptor("icons/project.png").createImage();
    }

    /**
     * Returns the system configuration icon.
     * 
     * @return {@link Image}
     */
    public static Image getSysCfgIcon() {
        return getImageDescriptor("icons/syscfg.gif").createImage();
    }

    /**
     * Returns the XBEL icon.
     * 
     * @return {@link Image}
     */
    public static Image getXBELIcon() {
        return getImageDescriptor("icons/xbel.gif").createImage();
    }

    private class Listener implements ISelectionListener,
            IResourceChangeListener {
        /**
         * {@inheritDoc}
         */
        @Override
        public void resourceChanged(IResourceChangeEvent e) {
            // TODO generate errors if kam_builder.xml is removed
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void selectionChanged(IWorkbenchPart part, ISelection sel) {
            if (sel instanceof TreeSelection) {
                selectedProjects = new ArrayList<String>();
                TreeSelection ts = (TreeSelection) sel;
                TreePath[] paths = ts.getPaths();
                if (noItems(paths)) {
                    return;
                }
                for (TreePath path : paths) {
                    if (path.getSegmentCount() != 1) {
                        continue;
                    }
                    Object o = path.getFirstSegment();
                    if (!(o instanceof Project)) {
                        continue;
                    }
                    Project p = (Project) o;
                    selectedProjects.add(p.getName());
                }
            }
        }
    }

    public static enum OS {
        Linux, Windows, Mac
    }
}
