/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;

/**
 * Scans command substitution document partitions. I.e., the sections between
 * back-ticks or enclosed in $()s.
 */
public class EvalScanner extends AbstractScriptScanner {

    public EvalScanner(IColorManager manager, IPreferenceStore store) {
        super(manager, store);
        initialize();
    }

    @Override
    protected List<IRule> createRules() {
        List<IRule> rules = new ArrayList<IRule>();
        return rules;
    }

    @Override
    protected String[] getTokenProperties() {
        return new String[0];
    }

}
