/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.index;

import org.openbel.workbench.core.common.InvalidArgument;

/**
 * {@link NamespaceInfo} captures the information header of a BEL namespace
 * resource.
 */
public class NamespaceInfo extends Resource implements
Comparable<NamespaceInfo> {
    private final String keyword;
    private final String queryValueURL;

    /**
     * Construct using all required fields.
     *
     * @param resourceLocation the resource location for the annotation, which
     * cannot be {@code null}
     * @param keyword the proposed {@link String keyword} for the namespace,
     * which cannot be {@code null}
     * @param name the {@link String name} of the namespace, which cannot be
     * {@code null}
     * @param description the {@link String description} of the namespace,
     * which cannot be {@code null}
     * @param queryValueURL the query value {@link String url} for this
     * namespace with value replacement, which cannot be {@code null}
     * @throws InvalidArgument Thrown if {@code keyword}, {@code name}, or
     * {@code description} is {@code null}
     */
    NamespaceInfo(final String resourceLocation, final String keyword,
            final String name,
            final String description,
            final String queryValueURL) {
        super(resourceLocation, name, description);

        if (keyword == null) {
            throw new InvalidArgument("keyword", keyword);
        }
        this.keyword = keyword;
        this.queryValueURL = queryValueURL;
    }

    /**
     * Return the keyword.
     *
     * @return the {@link String keyword}, will not be {@code null}
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Return the query value {@link String url}.
     *
     * @return the query value {@link String url}, will not be {@code null}
     */
    public String getQueryValueURL() {
        return queryValueURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
        result += prime * result
                + ((queryValueURL == null) ? 0 : queryValueURL.hashCode());
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NamespaceInfo other = (NamespaceInfo) obj;
        if (keyword == null) {
            if (other.keyword != null) {
                return false;
            }
        } else if (!keyword.equals(other.keyword)) {
            return false;
        }
        if (queryValueURL == null) {
            if (other.queryValueURL != null) {
                return false;
            }
        } else if (!queryValueURL.equals(other.queryValueURL)) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(NamespaceInfo o) {
        if (this == o) {
            return 0;
        }

        return getName().compareToIgnoreCase(o.getName());
    }
}
