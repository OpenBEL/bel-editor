/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.index;

import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;
import static org.openbel.editor.core.common.BELUtilities.nulls;

import java.util.List;

import org.openbel.editor.core.common.InvalidArgument;

/**
 * {@link ResourceIndex} holds the catalog of annotation and namespace
 * locations.
 */
public class ResourceIndex {
    private final List<AnnotationInfo> anl;
    private final List<NamespaceInfo> nsl;

    ResourceIndex(final List<AnnotationInfo> anl,
            final List<NamespaceInfo> nsl) {
        if (nulls(anl, nsl)) {
            throw new InvalidArgument("null arguments");
        }

        this.anl = anl;
        this.nsl = nsl;
        sort(this.anl);
        sort(this.nsl);
    }

    public List<AnnotationInfo> getAnnotations() {
        return unmodifiableList(anl);
    }

    public List<NamespaceInfo> getNamespaces() {
        return unmodifiableList(nsl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anl == null) ? 0 : anl.hashCode());
        result = prime * result + ((nsl == null) ? 0 : nsl.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResourceIndex other = (ResourceIndex) obj;
        if (anl == null) {
            if (other.anl != null) {
                return false;
            }
        } else if (!anl.equals(other.anl)) {
            return false;
        }
        if (nsl == null) {
            if (other.nsl != null) {
                return false;
            }
        } else if (!nsl.equals(other.nsl)) {
            return false;
        }
        return true;
    }
}
