package org.openbel.workbench.ui.text;

/**
 * A type of indent.
 */
public class IndentType {
    /** A single indent decrement */
    public static final IndentType DECREMENT = new IndentType();
    /** A single indent increment */
    public static final IndentType INCREMENT = new IndentType();
    /** An inflexion - both an increment and a decrement */
    public static final IndentType INFLEXION = new IndentType();

    private IndentType() {
    }
}
