/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core;

import static org.openbel.editor.core.Activator.PLUGIN_ID;

import org.eclipse.dltk.core.ScriptNature;

public class Nature extends ScriptNature {

    /**
     * BEL nature: {@value}
     */
    public static final String BEL_NATURE = PLUGIN_ID + ".nature";

}
