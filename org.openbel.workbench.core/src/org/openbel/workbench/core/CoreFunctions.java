package org.openbel.workbench.core;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.openbel.workbench.core.common.enums.ExitCode.getExitCode;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.openbel.workbench.core.common.enums.ExitCode;

/**
 * OpenBEL Workbench core functions.
 * 
 * @see CoreConstants
 */
public class CoreFunctions {

    /**
     * Returns a compiler exception {@link CoreException}, deriving from the
     * supplied exception as a result of executing the BEL compiler.
     * <p>
     * This method either returns the {@code e} supplied or provides something
     * more informative if {@code e} is has BEL compiler information.
     * </p>
     * 
     * @param e {@link CoreException}
     * @return {@link CoreException}
     */
    public static CoreException compilerException(CoreException e) {
        String msg = e.getMessage();
        if (!msg.contains("exec returned:")) return e;
        String[] split = msg.split(" ");
        String strcode = split[split.length - 1];
        ExitCode ec = getExitCode(parseInt(strcode));
        if (ec == null) return e;
        String fmt = "BEL Compiler failure (%d): %s";
        msg = format(fmt, ec.getValue(), ec.getErrorMessage());
        IStatus oldStatus = e.getStatus();
        IStatus newStatus = new Status(IStatus.ERROR,
                oldStatus.getPlugin(), msg);
        CoreException ce = new CoreException(newStatus);
        ce.initCause(e);
        return ce;
    }

    private CoreFunctions() {
    }
}
