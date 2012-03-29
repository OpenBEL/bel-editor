package org.openbel.workbench.core.record;

import static org.openbel.workbench.core.common.BELUtilities.constrainedHashMap;

import java.util.Map;

/**
 * Enumerated representation of record mode.
 * <p>
 * Portions of this enum have been automatically generated.
 * </p>
 *
 * @author Nick Bargnesi
 */
public enum RecordMode {

    /**
     * Read-only mode.
     */
    READ_ONLY(0, "READ_ONLY"),

    /**
     * Read/write mode.
     */
    READ_WRITE(1, "READ_WRITE");

    /* Value unique to each enumeration. */
    private final Integer value;
    /* Enumeration display value. */
    private String displayValue;

    /* Static cache of enum by string representation. */
    private static final Map<String, RecordMode> STRINGTOENUM =
            constrainedHashMap(values().length);

    static {
        for (final RecordMode e : values()) {
            STRINGTOENUM.put(e.toString(), e);
        }
    }

    /**
     * Constructor for setting enum and display value.
     *
     * @param value Enum value
     * @param displayValue Display value
     */
    private RecordMode(Integer value, String displayValue) {
        this.value = value;
        this.displayValue = displayValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return displayValue;
    }

    /**
     * Returns the record mode's value.
     *
     * @return value
     * @see java.lang.Enum#ordinal() Contrast with {@code ordinal}
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Returns the record mode's display value.
     *
     * @return display value
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * Returns the record mode by its string representation.
     *
     * @param s {@link RecordMode} string representation
     * @return {@link RecordMode}, may be null if the provided string has no
     * {@link RecordMode} representation
     */
    public static RecordMode getRecordMode(final String s) {
        RecordMode e = STRINGTOENUM.get(s);
        if (e != null) {
            return e;
        }

        for (final String dispval : STRINGTOENUM.keySet()) {
            if (dispval.equalsIgnoreCase(s)) {
                return STRINGTOENUM.get(dispval);
            }
        }

        return null;
    }
}
