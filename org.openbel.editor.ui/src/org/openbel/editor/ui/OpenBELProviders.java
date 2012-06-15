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

import static org.openbel.editor.ui.Activator.getBuilderLaunchIcon;
import static org.openbel.editor.ui.UIFunctions.getLaunchFiles;

import java.io.File;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

/**
 * Reusable providers useful throughout the BEL Editor.
 */
public class OpenBELProviders {
    public static class LaunchContent implements ITreeContentProvider {

        /**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void inputChanged(Viewer v, Object arg1, Object arg2) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getChildren(Object o) {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] getElements(Object o) {
            File[] files = getLaunchFiles();
            Object[] ret = new Object[files.length];
            for (int i = 0; i < files.length; i++) {
                ret[i] = files[i].getName().replaceAll(".launch$", "");
            }
            return ret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getParent(Object o) {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasChildren(Object o) {
            return false;
        }
    }

    public static class LaunchLabels implements ILabelProvider {

        /**
         * {@inheritDoc}
         */
        @Override
        public void addListener(ILabelProviderListener lstnr) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isLabelProperty(final Object o, final String s) {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removeListener(ILabelProviderListener lstnr) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Image getImage(final Object o) {
            return getBuilderLaunchIcon();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object o) {
            if (o instanceof String) {
                String s = (String) o;
                if (s.endsWith(".launch")) {
                    return s.replaceAll(".launch$", "");
                }
                return s;
            }
            return o.toString();
        }
    }
}
