package org.openbel.workbench.core;

import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;

/**
 * Problems associated with the OpenBEL Workbench.
 */
public class WorkbenchProblem implements IProblem {
    private boolean warning;
    private boolean error;
    private boolean task;
    private final String msg;

    /**
     * Creates a OpenBEL Workbench problem with the provided message.
     * 
     * @param msg
     *            Description of the problem
     */
    public WorkbenchProblem(final String msg) {
        this.msg = msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getArguments() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IProblemIdentifier getID() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOriginatingFileName() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceEnd() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceLineNumber() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceStart() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isError() {
        return error;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTask() {
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarning() {
        return warning;
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void setSourceEnd(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void setSourceLineNumber(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void setSourceStart(int i) {
        throw new UnsupportedOperationException();
    }

}
