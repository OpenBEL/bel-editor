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

import static org.openbel.editor.ui.text.BELPartitions.HASH_COMMENT;
import static org.openbel.editor.ui.text.BELPartitions.STRING;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.*;

/**
 * Responsible for enumerating and implementing the rules that should be used to
 * distinguish tokens such as line delimiters, white space, and generic patterns
 * when scanning a document.
 */
public class BELPartitionScanner extends RuleBasedPartitionScanner {

    /**
     * Creates the partition scanner.
     */
    public BELPartitionScanner() {
        super();
        List<IRule> rules = new ArrayList<IRule>();

        Token t = new Token(HASH_COMMENT);
        EndOfLineRule comments = new EndOfLineRule("#", t);
        comments.setColumnConstraint(0);
        rules.add(comments);

        t = new Token(STRING);
        rules.add(new MultiLineRule("\"", "\"", t, '\\'));

        /*
        t = new Token(RELATIONSHIP);kk
        SingleLineRule relationship = new SingleLineRule(" ", " ", t);
        rules.add(relationship);

        t = new Token(FUNCTION);
        SingleLineRule function = new SingleLineRule(" ", " ", t);
        rules.add(function);

        t = new Token(KEYWORD);
        ;
        SingleLineRule keyword = new SingleLineRule(" ", " ", t);
        rules.add(keyword);
        */

        IPredicateRule[] result = new IPredicateRule[rules.size()];
        rules.toArray(result);
        setPredicateRules(result);
    }
}
