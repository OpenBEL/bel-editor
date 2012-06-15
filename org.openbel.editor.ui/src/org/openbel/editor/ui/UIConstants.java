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

import static org.eclipse.dltk.ui.text.DLTKColorConstants.*;

import org.eclipse.swt.graphics.RGB;

/**
 * BEL Editor UI constants.
 * 
 * @see UIFunctions
 */
public class UIConstants {

    /** {@value} */
    public static String NO_KAM_BUILDERS = "Create a KAM builder by selecting "
            + "'Run Configurations...' from the 'Run' menu.";

    /** {@value} */
    public static String BUILDER_PROCESS_TYPE = "org.openbel.editor.build";

    /** {@value} */
    public static String FMT_LAUNCH_TASK_NAME = "Compiling %s";

    /** KAM builder file extensions ({@value} ). */
    public static String BUILDER_EXTENSION = "xml";

    /** System configuration file extensions ({@value} ). */
    public static String SYSCFG_EXTENSION = "cfg";

    /** Other coloring. */
    public static final String OTHER;
    public static final String OTHER_KEY;
    public static final RGB OTHER_RGB;

    /** Comment coloring for hashes. */
    public static final String HASH_COMMENT;
    public static final String HASH_COMMENT_KEY;
    public static final RGB HASH_COMMENT_RGB;

    /** Statement comment coloring. */
    public static final String STMT_COMMENT;
    public static final String STMT_COMMENT_KEY;
    public static final RGB STMT_COMMENT_RGB;

    /** Keyword coloring. */
    public static final String KEYWORD;
    public static final String KEYWORD_KEY;
    public static final RGB KEYWORD_RGB;

    /** Operator coloring. */
    public static final String OPERATOR;
    public static final String OPERATOR_KEY;
    public static final RGB OPERATOR_RGB;

    /** String coloring. */
    public static final String STRING;
    public static final String STRING_KEY;
    public static final RGB STRING_RGB;

    /** Function coloring. */
    public static final String FUNCTION;
    public static final String FUNCTION_KEY;
    public static final RGB FUNCTION_RGB;

    /** Relationship coloring. */
    public static final String RELATIONSHIP;
    public static final String RELATIONSHIP_KEY;
    public static final RGB RELATIONSHIP_RGB;

    static {
        OTHER = DLTKDOC_DEFAULT;
        HASH_COMMENT = DLTK_SINGLE_LINE_COMMENT;
        STMT_COMMENT = DLTK_MULTI_LINE_COMMENT;
        KEYWORD = DLTK_KEYWORD;
        OPERATOR = DLTK_OPERATOR;
        STRING = DLTK_STRING;
        FUNCTION = DLTK_FUNCTION_DEFINITION;
        RELATIONSHIP = DLTK_ARGUMENT;

        HASH_COMMENT_KEY = "Line comments";
        STMT_COMMENT_KEY = "Statement comments";
        KEYWORD_KEY = "Keywords";
        OPERATOR_KEY = "Operators";
        STRING_KEY = "Strings";
        FUNCTION_KEY = "Functions";
        RELATIONSHIP_KEY = "Relationships";
        OTHER_KEY = "Other";

        HASH_COMMENT_RGB = new RGB(136, 138, 133);
        STMT_COMMENT_RGB = new RGB(46, 52, 54);
        KEYWORD_RGB = new RGB(52, 101, 164);
        OPERATOR_RGB = new RGB(46, 52, 54);
        STRING_RGB = new RGB(78, 154, 6);
        FUNCTION_RGB = new RGB(239, 41, 41);
        RELATIONSHIP_RGB = new RGB(164, 0, 0);
        OTHER_RGB = new RGB(0, 0, 0);
    }

    private UIConstants() {
    }
}
