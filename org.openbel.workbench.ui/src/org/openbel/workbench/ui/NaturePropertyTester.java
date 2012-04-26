/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;

/**
 * A property tester that returns true if the project containing any given
 * resource is a BEL-natured project.
 */
public class NaturePropertyTester extends PropertyTester {

    // Properties made available for test by this tester
    private static final String HAS_BEL_NATURE = "hasBELNature"; //$NON-NLS-1$

    /**
     * Executes the property test determined by the parameter property.
     * 
     * @param receiver
     *            The receiver of the property to test
     * @param property
     *            The property to test
     * @param args
     *            Additional arguments to evaluate the property. May have
     *            zero-length.
     * @param expectedValue
     *            Expected value of the property - either {@link String} or a
     *            boxed primitive
     * @return {@code true} if the property is equal to the expected value,
     *         {@code false} otherwise
     */
    @Override
    public boolean test(Object receiver, String property, Object[] args,
            Object expectedValue) {
        IResource resource = (IResource) ((IAdaptable) receiver)
                .getAdapter(IResource.class);
        if (resource == null) {
            return false;
        }
        if (property.equals(HAS_BEL_NATURE)) {
            try {
                IProject proj = resource.getProject();
                return proj.isAccessible() && proj.hasNature(BEL_NATURE);
            } catch (CoreException e) {
                return false;
            }
        }
        return false;
    }
}
