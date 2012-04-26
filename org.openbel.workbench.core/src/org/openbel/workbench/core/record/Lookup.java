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

import java.io.IOException;
import java.util.Iterator;

public interface Lookup<K, E> extends Iterable<E> {

    boolean containsKey(K key) throws IOException;

    E get(K key) throws IOException;

    @Override
    Iterator<E> iterator();
}
