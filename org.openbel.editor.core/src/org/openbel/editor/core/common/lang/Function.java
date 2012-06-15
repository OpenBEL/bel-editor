/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.common.lang;

import static java.util.Collections.unmodifiableSet;
import static org.openbel.editor.core.common.BELUtilities.constrainedHashMap;
import static org.openbel.editor.core.common.BELUtilities.constrainedHashSet;
import static org.openbel.editor.core.common.BELUtilities.nulls;
import static org.openbel.editor.core.common.enums.SemanticStatus.VALID;

import java.util.Map;
import java.util.Set;

import org.openbel.editor.core.common.enums.SemanticStatus;

/**
 * The base class of the immutable BEL function model.
 * <p>
 * TODO: The function model currently lacks unit tests
 * </p>
 */
public abstract class Function {
    private final Set<Signature> signatures;
    private final String name;
    private final String abbreviation;
    private final String briefDescription;
    private final int hash;

    /**
     * Creates a function.
     * 
     * @param name
     *            Function name
     * @param abbr
     *            The function's abbreviation; its short notation (may be null)
     * @param brfDesc
     *            A brief, <b>one-line</b> description of the function
     * @param sigs
     *            The supported signatures
     * @see Signature Refer here for the format of {@code sigs}
     */
    protected Function(String name, String abbr, String brfDesc, String... sigs) {
        if (nulls(name, brfDesc, sigs) || sigs.length == 0) {
            final String me = getClass().getName();
            throw new RuntimeException(me + " is not a valid function");
        }

        this.name = name;
        this.abbreviation = abbr;
        this.briefDescription = brfDesc;
        hash = getClass().getName().hashCode();

        signatures = constrainedHashSet(sigs.length);
        for (final String signature : sigs) {
            final Signature internalsig = new Signature(signature);
            signatures.add(internalsig);
        }
    }

    /**
     * Returns a read-only version of the function's signature set.
     * 
     * @return Read-only {@link Set set} of {@link Signature signatures}
     */
    public Set<Signature> getSignatures() {
        return unmodifiableSet(signatures);
    }

    /**
     * Returns the function's name.
     * 
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the function's abbreviation.
     * 
     * @return {@link String}, may be null
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns the function's brief description.
     * 
     * @return {@link String}
     */
    public String getBriefDescription() {
        return briefDescription;
    }

    /**
     * Returns {@code true} if the provided signature is valid for this
     * function, {@code false} otherwise.
     * <p>
     * You can access the semantic status of invalid signatures (those returning
     * {@code false} here) via {@link #getStatus(Signature) getStatus}.
     * </p>
     * 
     * @param sig
     *            {@link Signature}
     * @return boolean
     * @see #getStatus(Signature)
     */
    public boolean validSignature(final Signature sig) {
        if (signatures.contains(sig))
            return true;

        for (final Signature signature : signatures) {
            final SemanticStatus status = signature.matches(sig);
            if (status == VALID)
                return true;
        }
        return false;
    }

    /**
     * Returns a map of {@link Signature signatures} to {@link SemanticStatus
     * semantic status}. The provided signature is checked against all
     * signatures of this function.
     * 
     * @param sig
     *            {@link Signature}
     * @return Map of signatures to semantic status
     * @see #validSignature(Signature)
     */
    public Map<Signature, SemanticStatus> getStatus(final Signature sig) {
        final int sigs_size = signatures.size();
        Map<Signature, SemanticStatus> ret = constrainedHashMap(sigs_size);

        for (final Signature signature : signatures) {
            final SemanticStatus status = signature.matches(sig);
            if (status == null)
                continue;
            ret.put(signature, status);
        }

        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append("Function [\n");

        bldr.append("\tname: ");
        bldr.append(name);
        bldr.append("\n");

        bldr.append("\tabbreviation: ");
        if (abbreviation != null)
            bldr.append(abbreviation);
        bldr.append("\n");

        bldr.append("\tbrief description: ");
        bldr.append(briefDescription);
        bldr.append("\n");

        bldr.append("\tsignatures:\n");
        for (final Signature sig : signatures) {
            bldr.append("\t\t");
            bldr.append(sig);
            bldr.append("\n");
        }

        bldr.append("]");
        return bldr.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == null)
            return false;
        return this.getClass().equals(obj.getClass());
    }

    /**
     * Returns {@code true} if the argument {@code count} is valid for this
     * function, {@code false} otherwise.
     * 
     * @param count
     *            Argument count
     * @return boolean
     */
    public abstract boolean validArgumentCount(int count);
}
