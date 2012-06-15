/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.record;

/**
 * MissingEncodingException defines a
 * {@link BELUncheckedException unchecked exception} that represents a
 * character encoding not available in the current environment.
 * 
 * @author Anthony Bargnesi &lt;abargnesi@selventa.com&gt;
 */
public class MissingEncodingException extends RuntimeException {
    private static final long serialVersionUID = 6448780981733610067L;

    public MissingEncodingException(String encoding) {
        super(createMessage(encoding));
    }
    
    public MissingEncodingException(String encoding, Throwable cause) {
        super(createMessage(encoding), cause);
    }

    private static String createMessage(String encoding) {
        return "Missing character encoding '" + encoding + "'.";
    }
}
