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

import static org.openbel.workbench.core.common.BELUtilities.nulls;

import org.openbel.workbench.core.common.InvalidArgument;

public class Resource {
    private final String resourceLocation;
    private final String name;
    private final String description;

    Resource(final String resourceLocation, final String name,
            final String description) {
        if (nulls(resourceLocation, name, description)) {
            throw new InvalidArgument("null args");
        }
        
        this.resourceLocation = resourceLocation;
        this.name = name;
        this.description = description;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime
                * result
                + ((resourceLocation == null) ? 0 : resourceLocation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Resource other = (Resource) obj;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (resourceLocation == null) {
            if (other.resourceLocation != null) return false;
        } else if (!resourceLocation.equals(other.resourceLocation))
            return false;
        return true;
    }
}
