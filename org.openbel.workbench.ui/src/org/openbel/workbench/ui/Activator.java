package org.openbel.workbench.ui;

import static java.io.File.createTempFile;
import static java.lang.String.valueOf;
import static java.lang.System.err;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.lang.System.nanoTime;
import static java.lang.Thread.yield;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.eclipse.core.runtime.IStatus.ERROR;
import static org.eclipse.core.runtime.IStatus.INFO;
import static org.eclipse.core.runtime.IStatus.WARNING;
import static org.openbel.workbench.core.common.BELUtilities.noItems;
import static org.openbel.workbench.core.common.BELUtilities.noLength;
import static org.openbel.workbench.core.common.BELUtilities.readable;
import static org.openbel.workbench.core.common.BELUtilities.typedList;
import static org.openbel.workbench.core.common.PathConstants.BEL_SCRIPT_EXTENSION;
import static org.openbel.workbench.core.common.PathConstants.SYSCONFIG_FILENAME;
import static org.openbel.workbench.core.common.PathConstants.XBEL_EXTENSION;
import static org.openbel.workbench.ui.UIConstants.BUILDER_EXTENSION;
import static org.openbel.workbench.ui.UIConstants.SYSCFG_EXTENSION;
import static org.openbel.workbench.ui.util.StackUtilities.callerFrame;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateFramework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.*;
import org.openbel.workbench.core.FileFilters.LaunchFilter;
import org.openbel.workbench.core.Nature;
import org.openbel.workbench.core.index.Parser;
import org.openbel.workbench.core.index.Resource;
import org.openbel.workbench.core.index.ResourceIndex;
import org.openbel.workbench.ui.text.BELTextTools;
import org.openbel.workbench.ui.util.ValidationUtilities.FileState;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends DLTKUIPlugin {
    public static final String ENV_BELFRAMEWORK_HOME;
    private static Activator plugin;
    /** The plug-in identifier: {@value} */
    public static final String PLUGIN_ID = "org.openbel.workbench.ui";
    /** {@value} */
    public static final String BF_PREF_KEY = "bel_framework_home_pref";
    /** {@value} */
    public static final String CY_PREF_KEY = "cytoscape_pref";
    private static IWorkbench workbench;
    private static OS platform;
    static {
        String s = getenv("BELFRAMEWORK_HOME");
        ENV_BELFRAMEWORK_HOME = s == null ? "" : s;
        String osname = getProperty("os.name").toLowerCase();
        if (osname.contains("linux"))
            platform = OS.Linux;
        else if (osname.contains("win"))
            platform = OS.Windows;
        else if (osname.contains("mac"))
            platform = OS.Mac;
        else
            throw new RuntimeException("unknown OS: " + osname);
    }
    private BELTextTools fBELTextTools;
    private Listener listener;

    private ResourceIndex resourceIndex;
    private Map<Resource, List<String>> resourceCatalog;
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
     * Returns the resource index.
     * 
     * @return {@link ResourceIndex}
     */
    public ResourceIndex getResourceIndex() {
        if (resourceIndex == null) {
            err.println("resource index is null");
            err.println("(be sure to tell " + callerFrame() + ")");
            reloadResourceView();
        }
        return resourceIndex;
    }

    /**
     * Returns the resource catalog.
     * 
     * @return the {@link Map map} of {@link List resource data}
     */
    public Map<Resource, List<String>> getResourceCatalog() {
        return resourceCatalog;
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
    public synchronized boolean reloadResourceView() {
        IPreferenceStore ps = getPreferenceStore();
        final StringBuilder b = new StringBuilder(ps.getString(BF_PREF_KEY))
                .append(File.separator).append("config")
                .append(File.separator).append(SYSCONFIG_FILENAME);

        final File configFile = new File(b.toString());
        if (!readable(configFile)) {
            err.println(configFile + " not readable");
            return false;
        }

        try {
            final Properties scprops = new Properties();
            scprops.load(new FileReader(configFile));

            final String rurl = scprops.getProperty("resource_index_url");
            final URL url = new URL(rurl);

            ResourceIndex resourceIndex = Parser.getInstance().parse(url);
            setResourceIndex(resourceIndex);
        } catch (Exception e) {
            e.printStackTrace();
            logError(e);
            return false;
        }

        return true;
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
     * Sets the resource catalog.
     * 
     * @param resourceCatalog the resource catalog
     */
    public void setResourceCatalog(
            final Map<Resource, List<String>> resourceCatalog) {
        this.resourceCatalog = resourceCatalog;
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
            // TODO should generate workbench error here
        } else {
            ps.setValue(BF_PREF_KEY, bfhome);
            reloadResourceView();
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
     * Returns the absolute path of an {@link IResource}.
     * 
     * @param f {@link IResource}; may not be null
     * @return {@link String}
     */
    public static String getAbsolutePath(IResource r) {
        return r.getLocation().toFile().getAbsolutePath();
    }

    /**
     * Returns {@code true} if the provided string denotes an Eclipse project
     * resource, {@code false} otherwise.
     * 
     * @param s Non-null {@link String}
     * @return boolean
     */
    public static boolean denotesProject(final String s) {
        if (s.startsWith("P/") && (s.length() > 2)) return true;
        return false;
    }

    /**
     * Returns {@code true} if the provided string denotes an Eclipse folder
     * resource, {@code false} otherwise.
     * 
     * @param s Non-null {@link String}
     * @return boolean
     */
    public static boolean denotesFolder(final String s) {
        if (s.startsWith("F/") && (s.length() > 2)) return true;
        return false;
    }

    /**
     * Returns {@code true} if the provided string denotes an Eclipse file
     * resource, {@code false} otherwise.
     * 
     * @param s Non-null {@link String}
     * @return boolean
     */
    public static boolean denotesFile(final String s) {
        if (s.startsWith("L/") && (s.length() > 2)) return true;
        return false;
    }

    /**
     * Defers to {@link Display#getDefault() the default display} to execute the
     * runnable.
     * 
     * @param runnable {@link Runnable}
     */
    public static void runAsync(final Runnable runnable) {
        Display.getDefault().asyncExec(runnable);
    }

    /**
     * Returns all {@link IFile} BEL documents underneath the provided folder.
     * 
     * @return {@code IFile[]}; may be zero-length
     */
    public static IFile[] getBELDocuments(IFolder f) {
        List<IFile> ret = new ArrayList<IFile>();

        IResource[] members;
        try {
            members = f.members();
        } catch (CoreException e) {
            logError(e);
            return new IFile[0];
        }
        List<IFile> files = typedList(asList(members), IFile.class);
        for (IFile file : files) {
            if (isBELDocument(file)) {
                ret.add(file);
            }
        }
        return ret.toArray(new IFile[ret.size()]);
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
     * Returns all {@link IFolder} document groups underneath the provided
     * project.
     * 
     * @return {@code IFolder[]}; may be zero-length
     */
    public static IFolder[] getDocumentGroups(IProject p) {
        List<IFolder> ret = new ArrayList<IFolder>();
        IResource[] members;
        try {
            members = p.members();
        } catch (CoreException e) {
            logError(e);
            return new IFolder[0];
        }
        List<IFolder> folders = typedList(asList(members), IFolder.class);
        for (IFolder folder : folders) {
            ret.add(folder);
        }
        return ret.toArray(new IFolder[ret.size()]);
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
     * Returns the {@link #getLaunchFiles() launch file} ending with
     * {@code fname}.launch.
     * 
     * @param fname {@link String}
     * @return {@link File}; may be null
     */
    public static File getLaunchFile(String fname) {
        if (noLength(fname)) return null;
        fname = fname.concat(".launch");
        File[] files = getLaunchFiles();
        if (noItems(files)) {
            return null;
        }
        for (final File f : files) {
            if (f.getName().endsWith(fname)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Returns the launch files available in the current workspace.
     * 
     * @return {@code File[]}; may be empty
     */
    public static File[] getLaunchFiles() {
        IPath stateLocation = DebugPlugin.getDefault().getStateLocation();
        File file = stateLocation.toFile();
        if (!file.isDirectory()) {
            return emptyList().toArray(new File[0]);
        }
        file = new File(file, ".launches");
        if (!file.isDirectory()) {
            return emptyList().toArray(new File[0]);
        }
        File[] files = file.listFiles(new LaunchFilter());
        if (noItems(files)) {
            return emptyList().toArray(new File[0]);
        }
        List<File> ret = new ArrayList<File>();
        for (final File f : files) {
            Properties p = new Properties();
            try {
                p.load(new FileReader(f));
            } catch (IOException e) {
                continue;
            }
            Object obj = p.get("<launchConfiguration");
            if ((obj == null) || !(obj instanceof String)) {
                continue;
            }
            String s = (String) obj;
            if (s.contains("openbel.workbench")) {
                ret.add(f);
            }
        }
        return ret.toArray(new File[0]);
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

    /**
     * Returns {@code true} if the {@link IFile} is either a BEL Script document
     * or an XBEL document, {@code false} otherwise.
     * 
     * @param f {@link IFile}
     * @return boolean
     */
    public static boolean isBELDocument(IFile f) {
        return isBELScriptDocument(f) || isXBELDocument(f);
    }

    /**
     * Returns {@code true} if the {@link IFile} is a BEL Script document,
     * {@code false} otherwise.
     * 
     * @param f {@link IFile}
     * @return boolean
     */
    public static boolean isBELScriptDocument(IFile f) {
        String ext = f.getFileExtension();
        if (ext == null) return false;
        if (BEL_SCRIPT_EXTENSION.equals(ext)) return true;
        ext = ".".concat(ext);
        return (BEL_SCRIPT_EXTENSION.equals(ext));
    }

    /**
     * Returns {@code true} if the {@link IFile} is a builder, {@code false}
     * otherwise.
     * 
     * @param f {@link IFile}
     * @return boolean
     */
    public static boolean isBuilder(IFile f) {
        String ext = f.getFileExtension();
        if (ext == null) return false;
        if (BUILDER_EXTENSION.equals(ext)) return true;
        ext = ".".concat(ext);
        return (BUILDER_EXTENSION.equals(ext));
    }

    /**
     * Returns {@code true} if the {@link IFile} is a system configuration,
     * {@code false} otherwise.
     * 
     * @param f {@link IFile}
     * @return boolean
     */
    public static boolean isSystemConfiguration(IFile f) {
        String ext = f.getFileExtension();
        if (ext == null) return false;
        if (SYSCFG_EXTENSION.equals(ext)) return true;
        ext = ".".concat(ext);
        return (SYSCFG_EXTENSION.equals(ext));
    }

    /**
     * Returns {@code true} if the {@link IFile} is an XBEL document,
     * {@code false} otherwise.
     * 
     * @param f {@link IFile}
     * @return boolean
     */
    public static boolean isXBELDocument(IFile f) {
        String ext = f.getFileExtension();
        if (ext == null) return false;
        if (XBEL_EXTENSION.equals(ext)) return true;
        ext = ".".concat(ext);
        return (XBEL_EXTENSION.equals(ext));
    }

    /**
     * Returns the platform.
     * 
     * @return {@link OS}
     */
    public static OS getPlatform() {
        return platform;
    }

    /**
     * Returns {@code true} if running on Linux or Mac, {@code false} otherwise.
     * 
     * @return boolean
     */
    public static boolean isNix() {
        return (platform == OS.Linux) || (platform == OS.Mac);
    }

    /**
     * Returns {@code true} if running on Windows, {@code false} otherwise.
     * 
     * @return boolean
     */
    public static boolean isWindows() {
        return platform == OS.Windows;
    }

    /**
     * Log the given error message to the Eclipse log.
     */
    public static void logError(String message) {
        getDefault().getLog().log(new Status(ERROR, PLUGIN_ID, message));
    }

    /**
     * Log the given exception to the Eclipse log.
     * 
     * @param t the exception to log
     */
    public static void logError(Throwable t) {
        String msg = t.getMessage();
        getDefault().getLog().log(new Status(ERROR, PLUGIN_ID, msg, t));
    }

    /**
     * Log the given info message to the Eclipse log.
     */
    public static void logInfo(String message) {
        getDefault().getLog().log(new Status(INFO, PLUGIN_ID, message));
    }

    /**
     * Log the given info message to the Eclipse log.
     */
    public static void logInfo(Throwable t) {
        String msg = t.getMessage();
        getDefault().getLog().log(new Status(INFO, PLUGIN_ID, msg, t));
    }

    /**
     * Log the given warning message to the Eclipse log.
     */
    public static void logWarning(String message) {
        getDefault().getLog().log(new Status(WARNING, PLUGIN_ID, message));
    }

    /**
     * Create a temporary directory.
     * 
     * @return {@link File}
     * @throws IOException Thrown if the temporary directory could not be
     *             created
     */
    public static File createTempDirectory() throws IOException {
        File temp = createTempFile("tmp", valueOf(nanoTime()));
        if (!temp.delete()) throw new IOException();
        if (!temp.mkdir()) throw new IOException("can't create temp path");
        return temp;
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
    };
}
