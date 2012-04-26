/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.text;

import static org.openbel.workbench.ui.text.BELPartitions.PARTITIONING;
import static org.openbel.workbench.ui.text.BELPartitions.PARTITION_TYPES;

import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openbel.workbench.ui.editor.BELSourceViewerConfiguration;

public class BELTextTools extends ScriptTextTools {
    private final IPartitionTokenScanner scanner;

    public BELTextTools(boolean autoDisposeOnDisplayDispose) {
        super(PARTITIONING, PARTITION_TYPES, autoDisposeOnDisplayDispose);
        scanner = new BELPartitionScanner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPartitionTokenScanner createPartitionScanner() {
        return scanner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
            IPreferenceStore preferenceStore, ITextEditor editor,
            String partitioning) {
        return new BELSourceViewerConfiguration(getColorManager(),
                preferenceStore, editor, partitioning);
    }

}
