package org.openbel.workbench.ui;

import static org.openbel.workbench.ui.Activator.isBuilder;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;

/**
 * A property tester that returns true if a resource is considered a builder
 * resource.
 */
public class BuilderPropertyTester extends PropertyTester {

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
        IFile f = null;
        if (receiver instanceof IFile) {
            f = (IFile) receiver;
        } else if (receiver instanceof IAdaptable) {
            f = (IFile) ((IAdaptable) receiver).getAdapter(IFile.class);
        }
        if (f == null) {
            return false;
        }
        if (isBuilder(f)) {
            return true;
        }
        return false;
    }

}
