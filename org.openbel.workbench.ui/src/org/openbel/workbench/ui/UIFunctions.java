package org.openbel.workbench.ui;

import static java.io.File.createTempFile;
import static java.lang.String.valueOf;
import static java.lang.System.getProperty;
import static java.lang.System.nanoTime;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.eclipse.core.runtime.IStatus.ERROR;
import static org.eclipse.core.runtime.IStatus.INFO;
import static org.eclipse.core.runtime.IStatus.WARNING;
import static org.openbel.workbench.core.common.BELUtilities.noItems;
import static org.openbel.workbench.core.common.BELUtilities.noLength;
import static org.openbel.workbench.core.common.BELUtilities.typedList;
import static org.openbel.workbench.core.common.PathConstants.BEL_SCRIPT_EXTENSION;
import static org.openbel.workbench.core.common.PathConstants.XBEL_EXTENSION;
import static org.openbel.workbench.ui.Activator.PLUGIN_ID;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIConstants.BUILDER_EXTENSION;
import static org.openbel.workbench.ui.UIConstants.SYSCFG_EXTENSION;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.openbel.workbench.core.FileFilters.LaunchFilter;

/**
 * OpenBEL Workbench UI functions.
 * 
 * @see UIConstants
 */
public class UIFunctions {
    private static OS platform;
    static {
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

    /**
     * Returns a {@link Shell shell} which may be null.
     * 
     * @return {@link Shell}; may be null
     */
    public static Shell shell() {
        return getDefault().getShell();
    }

    private UIFunctions() {
    }

    public static enum OS {
        Linux, Windows, Mac
    }
}
