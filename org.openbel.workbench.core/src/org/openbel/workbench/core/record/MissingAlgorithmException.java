/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.record;

/**
 * MissingAlgorithmException defines a
 * {@link BELUncheckedException unchecked exception} that represents an
 * algorithm not available in the current environment.
 * 
 * @author Anthony Bargnesi &lt;abargnesi@selventa.com&gt;
 */
public class MissingAlgorithmException extends RuntimeException {
    private static final long serialVersionUID = 5051280519540127766L;

    public MissingAlgorithmException(String algorithm) {
        super(createMessage(algorithm));
    }
    
    public MissingAlgorithmException(String algorithm, Throwable cause) {
        super(createMessage(algorithm), cause);
    }

    private static String createMessage(String algorithm) {
        return "Missing algorithm '" + algorithm + "'.";
    }
}
