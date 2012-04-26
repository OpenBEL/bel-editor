/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.navigator;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

public interface DocumentsContainer {

    public IFolder getFolder();

    public IProject getProject();
}
