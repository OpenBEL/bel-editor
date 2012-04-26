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

import static org.openbel.workbench.ui.UIConstants.HASH_COMMENT;
import static org.openbel.workbench.ui.UIConstants.OTHER;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;

/**
 * Highlights comment syntactic elements.
 */
public class CommentScanner extends AbstractScriptScanner {

    /**
     * BEL comment scanner.
     * 
     * @param manager
     *            Color manager
     * @param store
     *            Preference store
     */
    public CommentScanner(IColorManager manager, IPreferenceStore store) {
        super(manager, store);
        this.initialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<IRule> createRules() {
        List<IRule> rules = new ArrayList<IRule>();

        IToken comment = this.getToken(HASH_COMMENT);

        SingleLineRule slr = new SingleLineRule("#", null, comment);
        slr.setColumnConstraint(0);
        rules.add(slr);

        this.setDefaultReturnToken(this.getToken(OTHER));
        return rules;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getTokenProperties() {
        return new String[] { HASH_COMMENT };
    }
}
