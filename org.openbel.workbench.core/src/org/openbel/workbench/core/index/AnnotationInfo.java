package org.openbel.workbench.core.index;

import org.openbel.workbench.core.common.InvalidArgument;

/**
 * {@link AnnotationInfo} captures the information header of a BEL annotation
 * resource.
 */
public class AnnotationInfo extends Resource implements
        Comparable<AnnotationInfo> {
    private final String usage;

    /**
     * Construct using all required fields.
     * 
     * @param resourceLocation the resource location for the annotation, which
     * cannot be {@code null}
     * @param keyword the proposed {@link String keyword} for the annotation,
     * which cannot be {@code null}
     * @param usage the {@link String usage} of the annotation, which cannot be
     * {@code null}
     * @param description the {@link String description} of the annotation,
     * which cannot be {@code null}
     * @throws InvalidArgument Thrown if {@code keyword}, {@code name}, or
     * {@code description} is {@code null}
     */
    AnnotationInfo(final String resourceLocation, final String keyword,
            final String description,
            final String usage) {
        super(resourceLocation, keyword, description);
        this.usage = usage;
    }

    /**
     * Return the usage.
     * 
     * @return the {@link String usage}, will not be {@code null}
     */
    public String getUsage() {
        return usage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((usage == null) ? 0 : usage.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AnnotationInfo other = (AnnotationInfo) obj;
        if (usage == null) {
            if (other.usage != null) return false;
        } else if (!usage.equals(other.usage)) return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(AnnotationInfo o) {
        if (this == o) {
            return 0;
        }
        
        return getName().compareToIgnoreCase(o.getName());
    }
}
