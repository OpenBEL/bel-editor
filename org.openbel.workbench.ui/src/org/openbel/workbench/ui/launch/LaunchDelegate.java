package org.openbel.workbench.ui.launch;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.err;
import static org.eclipse.core.externaltools.internal.IExternalToolConstants.ATTR_LAUNCH_IN_BACKGROUND;
import static org.eclipse.debug.core.ILaunchManager.RUN_MODE;
import static org.openbel.workbench.core.common.enums.ExitCode.getExitCode;
import static org.openbel.workbench.ui.Activator.getAbsolutePath;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIConstants.BUILDER_PROCESS_TYPE;
import static org.openbel.workbench.ui.UIConstants.FMT_LAUNCH_TASK_NAME;

import java.util.*;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.ant.internal.core.AbstractEclipseBuildLogger;
import org.eclipse.core.externaltools.internal.launchConfigurations.ExternalToolsCoreUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.*;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.openbel.workbench.core.common.enums.ExitCode;
import org.openbel.workbench.ui.Activator;

/**
 * The launch delegate is the brains of the launcher.
 */
public class LaunchDelegate extends LaunchConfigurationDelegate {
    /** {@value} */
    public final static String ATTR_LAUNCH = "LAUNCH_ATTR";
    /** {@value} */
    public final static String ATTR_PRJ = "PROJECT";
    /** {@value} */
    public final static String ATTR_BLDR = "BUILDER";
    /** {@value} */
    public final static String ATTR_DOC = "DOCS";
    /** {@value} */
    public final static String KAM_NAME = "KAM_NAME";
    /** {@value} */
    public final static String KAM_DESC = "KAM_DESC";

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void launch(ILaunchConfiguration cfg, String mode, ILaunch launch,
            IProgressMonitor monitor) throws CoreException {

        Activator act = getDefault();
        String name = cfg.getName();

        if (monitor == null) {
            // Launching programmatically is unsupported.
            return;
        }

        monitor.setTaskName(format(FMT_LAUNCH_TASK_NAME, name));

        // Debug UI framework makes sure the delegate is not asked to launch
        // a mode it can't handle, but programmatic launching will do no such
        // thing.
        if (!RUN_MODE.equals(mode)) {
            return;
        }

        boolean inBG = cfg.getAttribute(ATTR_LAUNCH_IN_BACKGROUND, true);
        if (inBG) {
            monitor.beginTask(name, 10);
        } else {
            monitor.beginTask(name, 100);
        }

        monitor.subTask("Gathering resources.");

        Map attributes = cfg.getAttributes();
        Map map = (Map) attributes.get(ATTR_LAUNCH);

        String prjAttr = (String) map.get(ATTR_PRJ);
        String bldrAttr = (String) map.get(ATTR_BLDR);
        String docsAttr = (String) map.get(ATTR_DOC);
        String kamname = (String) map.get(KAM_NAME);
        String kamdesc = (String) map.get(KAM_DESC);

        IProject prj = act.getProject(prjAttr);
        IFile bldr = prj.getFile(bldrAttr);
        if (bldr == null) {
            // TODO report problem here
            return;
        }
        String bldrpath = getAbsolutePath(bldr);

        String[] doctokens = tokenize(docsAttr);
        List<IFile> docfiles = fromTokens(doctokens);
        if (monitor.isCanceled() || docfiles.isEmpty()) {
            return;
        }

        DebugPlugin debugPlugin = DebugPlugin.getDefault();
        ILaunchManager launchMgr = debugPlugin.getLaunchManager();

        if (monitor.isCanceled()) {
            return;
        }

        // launch is the top-level debug model artifact that represents a launch
        //launch.addProcess()...

        monitor.subTask("Setting up build.");

        IPath cwd = ExternalToolsCoreUtil.getWorkingDirectory(cfg);
        String basedir = null;
        if (cwd != null) {
            basedir = cwd.toOSString();
        }

        if (monitor.isCanceled()) {
            return;
        }

        monitor.worked(1);

        Map attrs = new HashMap(2);
        attrs.put(IProcess.ATTR_PROCESS_TYPE, BUILDER_PROCESS_TYPE);
        String stamp = valueOf(currentTimeMillis());
        attrs.put(AbstractEclipseBuildLogger.ANT_PROCESS_ID, stamp);

        final AntProcess process = new AntProcess("location", launch, attrs);
        //process.setAttribute(IProcess.ATTR_CMDLINE, command.toString());
        process.setProgressMonitor(monitor);
        if (inBG) {
            err.println("Launching in the background is not supported yet!");
            err.println("(ignoring this and launching in the foreground)");
            // TODO Defer to background thread
            /*
            final AntRunner ar = new AntRunner();
            ar.run(monitor);
            return;
            */
        }

        monitor.subTask("Executing build.");
        final AntRunner ar = new AntRunner();
        ar.setBuildFileLocation(bldrpath);
        ar.setArguments(buildArguments(docfiles, kamname, kamdesc));
        try {
            ar.run(monitor);
        } catch (CoreException e) {
            String msg = e.getMessage();
            if (!msg.contains("exec returned:")) {
                throw e;
            }
            String[] split = msg.split(" ");
            String strcode = split[split.length - 1];
            ExitCode ec = getExitCode(parseInt(strcode));
            if (ec == null) {
                throw e;
            }
            String fmt = "BEL Compiler failure (%d): %s";
            msg = format(fmt, ec.getValue(), ec.getErrorMessage());
            IStatus oldStatus = e.getStatus();
            IStatus newStatus = new Status(IStatus.ERROR,
                    oldStatus.getPlugin(), msg);
            CoreException ce = new CoreException(newStatus);
            ce.initCause(e);
            throw ce;
        } finally {
            process.terminated();
            monitor.done();
        }
    }

    private String[] buildArguments(List<IFile> docfiles, String name,
            String desc) {
        List<String> ret = new ArrayList<String>();
        final StringBuilder bldr = new StringBuilder();

        bldr.append("-DBELFRAMEWORK_HOME=");
        bldr.append(getDefault().getBELFrameworkHome());
        ret.add(bldr.toString());
        bldr.setLength(0);

        bldr.append("-DKAM_NAME=");
        bldr.append(name);
        ret.add(bldr.toString());
        bldr.setLength(0);

        bldr.append("-DKAM_DESCRIPTION=");
        bldr.append(desc);
        ret.add(bldr.toString());
        bldr.setLength(0);

        bldr.append("-DCOMPILER_ARGS=\"");
        for (final IFile file : docfiles) {
            bldr.append(" -f \"");
            bldr.append(getAbsolutePath(file));
            bldr.append("\"");
        }
        bldr.append("\"");
        ret.add(bldr.toString());
        bldr.setLength(0);

        return ret.toArray(new String[0]);
    }

    /**
     * Returns the tokens after tokenizing the string by the {@code ;}
     * delimiter.
     * 
     * @param string {@link String}
     * @return {@code String[]}
     */
    public static String[] tokenize(String string) {
        List<String> ret = new ArrayList<String>();
        StringTokenizer toker = new StringTokenizer(string, ";");
        while (toker.hasMoreTokens()) {
            ret.add(toker.nextToken());
        }
        return ret.toArray(new String[0]);
    }

    private static List<IFile> fromTokens(String[] doctokens) {
        List<IFile> ret = new ArrayList<IFile>();
        for (final String token : doctokens) {
            IResource resource = getDefault().getResource(token);
            if (resource instanceof IFile) {
                ret.add((IFile) resource);
            }
        }
        return ret;
    }
}
