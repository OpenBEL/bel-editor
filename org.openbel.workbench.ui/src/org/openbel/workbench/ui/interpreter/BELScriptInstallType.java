package org.openbel.workbench.ui.interpreter;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;
import org.openbel.workbench.ui.Activator;

public class BELScriptInstallType extends AbstractInterpreterInstallType {
    private static final String[] INTERPRETER_NAMES = { "bel", "xbel" };

    @Override
    protected IPath createPathFile(IDeployment deployment) throws IOException {
        return null;
    }

    @Override
    protected IInterpreterInstall doCreateInterpreterInstall(String id) {
        return new BELScriptInstall(this, id);
    }

    @Override
    public synchronized LibraryLocation[] getDefaultLibraryLocations(
            IFileHandle installLocation, EnvironmentVariable[] variables,
            IProgressMonitor monitor) {
        return new LibraryLocation[0];
    }

    @Override
    protected ILog getLog() {
        return Activator.getDefault().getLog();
    }

    @Override
    public String getName() {
        return "BEL Interpreter";
    }

    @Override
    public String getNatureId() {
        return BEL_NATURE;
    }

    @Override
    protected String getPluginId() {
        return Activator.PLUGIN_ID;
    }

    @Override
    protected String[] getPossibleInterpreterNames() {
        return INTERPRETER_NAMES;
    }

}
