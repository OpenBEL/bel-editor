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

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class MainPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    public MainPreferencePage() {
        // TODO Auto-generated constructor stub
    }

    public MainPreferencePage(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    public MainPreferencePage(String title, ImageDescriptor image) {
        super(title, image);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Control createContents(Composite parent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(IWorkbench workbench) {
        // TODO Auto-generated method stub

    }

}
