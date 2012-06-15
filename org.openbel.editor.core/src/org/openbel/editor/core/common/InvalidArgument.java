/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.common;

/**
 * BEL runtime exception indicating an invalid argument was provided.
 * <p>
 * This class should be used in favor of {@link IllegalArgumentException} as it
 * forces a standard for runtime exception reporting.
 * </p>
 */
public class InvalidArgument extends RuntimeException {
    private static final long serialVersionUID = 3550275196600044808L;
    public static final String DEFAULT_MSG = "Invalid argument";

    /**
     * Creates an invalid argument exception with {@value #DEFAULT_MSG} as its
     * detail message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause(Throwable)}.
     * <p>
     * The constructors {@link #InvalidArgument(String)} and
     * {@link #InvalidArgument(String, Throwable)} are favored in preference to
     * using the default message.
     * </p>
     * 
     * @deprecated You should be explaining {@link #InvalidArgument(String) why}
     *             the argument is invalid.
     */
    @Deprecated
    public InvalidArgument() {
        super(DEFAULT_MSG);
    }

    /**
     * Creates an invalid argument exception with the specified cause and
     * {@value #DEFAULT_MSG} as its detail message.
     * 
     * @param cause
     *            The cause of the exception
     */
    public InvalidArgument(Throwable cause) {
        super(DEFAULT_MSG, cause);
    }

    /**
     * Creates an invalid argument exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause(Throwable)}.
     * 
     * @param message
     *            The detailed exception message
     */
    public InvalidArgument(String message) {
        super(message);
    }

    /**
     * Creates an invalid argument exception with the following detail message:
     * <dl>
     * <dt>If {@code argument} is null:</dt>
     * <dd>{@code <argumentName> is null}</dd>
     * <dt>If {@code argument} is non-null:</dt>
     * <dd>{@code invalid <argumentName>}</dd>
     * </dl>
     * 
     * @param argumentName
     *            The invalid argument's name
     * @param argument
     *            The invalid argument (may be null)
     */
    public InvalidArgument(String argumentName, Object argument) {
        super(makeDetail(argumentName, argument));
    }

    /**
     * Creates an invalid argument exception with the specified cause and detail
     * message.
     * 
     * @param message
     *            The detailed exception message
     * @param cause
     *            The cause of the exception
     */
    public InvalidArgument(String message, Throwable cause) {
        super(message, cause);
    }

    private static String makeDetail(final String name, final Object o) {
        if (o == null)
            return name.concat(" is null");
        return "invalid ".concat(name);
    }
}
