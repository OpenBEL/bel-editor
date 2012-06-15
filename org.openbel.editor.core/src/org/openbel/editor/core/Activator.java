/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core;

import static org.eclipse.core.runtime.IStatus.ERROR;
import static org.eclipse.core.runtime.IStatus.INFO;
import static org.eclipse.core.runtime.IStatus.WARNING;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends Plugin {
    private static Activator plugin;
    /* Plug-in identifier: {@value} */
    public static final String PLUGIN_ID = "org.openbel.editor.core";

    /**
     * Returns the shared instance.
     * 
     * @return {@link Activator}
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * The constructor
     */
    public Activator() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
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
     * Log the given error message to the Eclipse log.
     */
    public static void logError(String message) {
        getDefault().getLog().log(new Status(ERROR, PLUGIN_ID, message));
    }

    /**
     * Log the given exception to the Eclipse log.
     * 
     * @param t
     *            the exception to log
     */
    public static void logError(Throwable t) {
        String msg = t.getMessage();
        getDefault().getLog().log(new Status(ERROR, PLUGIN_ID, msg, t));
    }
}
