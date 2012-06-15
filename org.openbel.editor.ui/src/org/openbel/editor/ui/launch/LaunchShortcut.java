/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.launch;

import static java.lang.System.out;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * Allows users to identify a resource in the editor and launch it without
 * bringing up the launch configuration. Useful when defaults are fine to launch
 * in a hurry.
 */
public class LaunchShortcut implements ILaunchShortcut {

    /**
     * Locates a launchable entity in the given editor and launches in the
     * specified mode.
     * <p>
     * This is invoked by an editor part (e.g., in the KAM builder editor).
     * </p>
     * 
     * @param sel
     *            {@link ISelection}
     * @param mode
     *            {@link String}
     */
    @Override
    public void launch(IEditorPart ed, String mode) {
        if (!"run".equals(mode)) return;
        IFile f = (IFile) ed.getAdapter(IFile.class);
        if (f == null) return;
        launch(f);
    }

    /**
     * Locates a launchable entity in the given selection and launches in the
     * specified mode.
     * <p>
     * This is invoked by a workbench selection (e.g., in the project explorer).
     * </p>
     * 
     * @param sel
     *            {@link ISelection}
     * @param mode
     *            {@link String}
     */
    @Override
    public void launch(ISelection sel, String mode) {
        if (!"run".equals(mode)) return;
        if (!(sel instanceof IFile)) return;
        IFile f = (IFile) sel;
        launch(f);
    }

    private void launch(IFile f) {
        out.println("Launching " + f);
    }

}
