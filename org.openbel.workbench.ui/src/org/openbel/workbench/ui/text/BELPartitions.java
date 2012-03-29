package org.openbel.workbench.ui.text;

import org.eclipse.jface.text.IDocument;

/**
 * Partitioning is used for syntax highlighting.
 */
public interface BELPartitions {

    /** {@value} */
    public final static String PARTITIONING = "__bel_partitioning";

    /** {@value} */
    public final static String HASH_COMMENT = "__hash_comment";

    /** {@value} */
    public final static String STRING = "__string";

    /** {@value} */
    public final static String KEYWORD = "__keyword";

    /** {@value} */
    public final static String FUNCTION = "__function";

    /** {@value} */
    public final static String RELATIONSHIP = "__relationship";

    /** {@value} */
    public final static String OTHER = "__other";

    /**
     * Each partition type.
     */
    public final String[] PARTITION_TYPES = new String[] {
            IDocument.DEFAULT_CONTENT_TYPE,
            HASH_COMMENT,
            STRING,
            KEYWORD,
            FUNCTION,
            RELATIONSHIP,
            OTHER
    };

}
