package org.openbel.workbench.core.record;

import java.io.IOException;
import java.util.Iterator;

public interface Lookup<K, E> extends Iterable<E> {

    boolean containsKey(K key) throws IOException;

    E get(K key) throws IOException;

    @Override
    Iterator<E> iterator();
}
