/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui;

import static java.lang.System.out;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class EmptyContentProvider implements ITreeContentProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        out.println("dispose");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        out.println("input changed");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getChildren(Object arg0) {
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getElements(Object arg0) {
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParent(Object arg0) {
        return new Object();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChildren(Object arg0) {
        return false;
    }

}
