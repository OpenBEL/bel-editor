/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.wizards;

import static org.eclipse.dltk.ui.DLTKUIPlugin.getDefault;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

/**
 * Wizard for creating a new BEL script file.
 */
public class NewBELScriptFileWizard extends NewSourceModuleWizard {

    /**
     * {@link NewBELScriptFileWizard}
     */
    public NewBELScriptFileWizard() {
        setWindowTitle("New BEL Document Wizard");
        setDialogSettings(getDefault().getDialogSettings());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NewSourceModulePage createNewSourceModulePage() {
        return new NewBELScriptFilePage();
    }
}
