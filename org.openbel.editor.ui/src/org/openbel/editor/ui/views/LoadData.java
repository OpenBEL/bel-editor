/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.views;

import static org.openbel.editor.core.common.BELUtilities.createDirectory;
import static org.openbel.editor.core.common.BELUtilities.hasItems;
import static org.openbel.editor.core.common.BELUtilities.nulls;
import static org.openbel.editor.ui.Activator.getDefault;
import static org.openbel.editor.ui.Activator.getPluginLocation;
import static org.openbel.editor.ui.UIFunctions.logError;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.openbel.editor.core.common.InvalidArgument;
import org.openbel.editor.core.index.Resource;
import org.openbel.editor.core.record.RecordFile;
import org.openbel.editor.core.record.Records;

/**
 * {@link LoadData} defines a {@link Runnable runnable} that loads
 * {@link Resource resource} data and populates a {@link Table table}.
 */
final class LoadData implements Runnable {
    private final Resource resource;
    private final Table table;

    /**
     * Construct with the {@link Resource resource} to load from and the
     * {@link Table table} to load into.
     *
     * @param resource the {@link Resource} to load data from, which cannot
     * be {@code null}
     * @param table the {@link Table table} to load data into, which cannot
     * be {@code null}
     * @throws InvalidArgument Thrown if {@code nsi} or {@code table} is
     * {@code null}
     */
    LoadData(final Resource resource, final Table table) {
        if (nulls(resource, table)) {
            throw new InvalidArgument("null parameter(s)");
        }

        this.resource = resource;
        this.table = table;
    }

    /**
     * Load the date for the selected {@link Resource resource} in a separate
     * thread.
     *
     * {@inheritDoc}
     */
    @Override
    public void run() {
        // remove previous DataListener listeners
        Listener[] listeners = table.getListeners(SWT.SetData);
        if (hasItems(listeners)) {
            for (final Listener l : listeners) {
                if (l instanceof DataListener) {
                    table.removeListener(SWT.SetData, l);
                }
            }
        }

        // clear our table data, reset virtual space
        table.clearAll();
        table.removeAll();
        table.setItemCount(0);

        // load plugin resource location
        final String pluginResources = getPluginLocation()
                .append("resources")
                .makeAbsolute().toString();
        createDirectory(pluginResources);
        final File file = new File(pluginResources);

        // set table count for current resource and add a new data listener
        try {
            final Records records = new Records(file, getDefault()
                    .getResourceIndex());
            final String rloc = resource.getResourceLocation();
            table.setItemCount((int) records.count(rloc));
            table.addListener(SWT.SetData, new DataListener(records, rloc));
        } catch (IOException ex) {
            logError(ex);
        }
    }

    /**
     * {@link DataListener} retrieves entries from a
     * {@link RecordFile record file} and sets the {@link TableItem table item}
     * text.
     */
    private final class DataListener implements Listener {
        private final Records records;
        private final String rloc;

        public DataListener(final Records records, final String rloc) {
            this.records = records;
            this.rloc = rloc;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void handleEvent(Event event) {
            final TableItem item = (TableItem) event.item;
            int index = event.index;
            try {
                String value = records.retrieve(rloc, index);
                item.setText(value);
            } catch (IOException e) {
                logError(e);
            }
        }
    }
}
