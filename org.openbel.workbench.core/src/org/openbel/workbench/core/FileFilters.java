/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A collection of file filters relevant to the OpenBEL Workbench.
 */
public class FileFilters {

    public static class LaunchFilter implements FilenameFilter {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean accept(File dir, String name) {
            if (name.endsWith(".launch")) return true;
            return false;
        }

    }

}
