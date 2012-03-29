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
