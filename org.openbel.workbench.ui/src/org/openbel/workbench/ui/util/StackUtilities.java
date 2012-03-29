package org.openbel.workbench.ui.util;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.getAllStackTraces;

/**
 * Utility methods for accessing and leveraging the stack-based architecture of
 * the virtual machine.
 */
public class StackUtilities {
    private final static String CLASS = StackUtilities.class.getName();

    /**
     * Returns the {@link StackTraceElement} of the caller.
     * <p>
     * You should consider use of this method carefully. Unwinding the stack
     * should be considered an expensive operation.
     * </p>
     * 
     * @return StackTraceElement of the caller
     * @see StackTraceElement
     */
    public static StackTraceElement callerFrame() {
        final Thread me = currentThread();
        StackTraceElement[] se = me.getStackTrace();
        for (int i = 0; i < se.length; i++) {
            String sClass = se[i].getClassName();
            if (!sClass.equals(CLASS))
                continue;
            String method = se[i].getMethodName();
            if (!"callerFrame".equals(method))
                continue;
            if (i + 2 < se.length)
                return se[i + 2];
        }
        throw new IllegalStateException("bad stack");
    }

    /**
     * Returns your {@link StackTraceElement}.
     * <p>
     * You should consider use of this method carefully. Unwinding the stack
     * should be considered an expensive operation.
     * </p>
     * 
     * @return StackTraceElement of the caller
     * @see StackTraceElement
     */
    public static StackTraceElement myFrame() {
        final Thread me = currentThread();
        StackTraceElement[] se = getAllStackTraces().get(me);
        for (int i = 0; i < se.length; i++) {
            String sClass = se[i].getClassName();
            if (!sClass.equals(CLASS))
                continue;
            String method = se[i].getMethodName();
            if (!"myFrame".equals(method))
                continue;
            if (i + 1 < se.length)
                return se[i + 1];
        }
        throw new IllegalStateException("bad stack");
    }

    /**
     * Default private constructor.
     */
    private StackUtilities() {

    }
}
