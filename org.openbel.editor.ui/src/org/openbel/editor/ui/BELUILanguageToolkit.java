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

import static org.openbel.editor.core.BELLanguageToolkit.getDefault;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;
import org.openbel.editor.core.BELLanguageToolkit;

/**
 * The BEL-specific UI toolkit.
 */
public class BELUILanguageToolkit extends AbstractDLTKUILanguageToolkit {
    public static final String BEL_CONTENT_TYPE = BELLanguageToolkit.BEL_CONTENT_TYPE;
    public static final String XBEL_CONTENT_TYPE = "org.openbel.editor.xbel-content-type";

    /**
     * {@inheritDoc}
     */
    @Override
    public IDLTKLanguageToolkit getCoreToolkit() {
        return getDefault();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInterpreterPreferencePage() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

    /**
     * Overridden to retrieve an editor for multiple source modules in DLTK.
     * 
     * @see http://dev.eclipse.org/mhonarc/lists/dltk-dev/msg02137.html
     * 
     *      {@inheritDoc}
     */
    @Override
    public String getEditorId(Object inputElement) {
        // not a dltk source module, rely on dltk
        if (!(inputElement instanceof ISourceModule)) {
            return super.getEditorId(inputElement);
        }

        // look up default editor for file name.
        final ISourceModule src = (ISourceModule) inputElement;
        final IEditorRegistry regedit = PlatformUI.getWorkbench()
                .getEditorRegistry();
        IEditorDescriptor eddesc = regedit.getDefaultEditor(src
                .getElementName());

        // no default editor, rely on DLTK
        if (eddesc == null) {
            return super.getEditorId(inputElement);
        }

        // return editor id found in registry
        return eddesc.getId();
    }
}
