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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class OpenBELWorkbenchLabelProvider extends LabelProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof BaseDocumentsFolder) {
            return getImage("icons/script.png");
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object element) {
        if (element instanceof IProject) {
            return ((IProject) element).getName();
        } else if (element instanceof Element) {
            return ((Element) element).getName();
        } else if (element instanceof IResource) {
            return ((IResource) element).getName();
        }

        return null;
    }
}
