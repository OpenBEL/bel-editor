/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

/**
 * 
 */
package org.openbel.editor.core;

import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemIdentifier;

/**
 * Problems associated with BELScript documents.
 */
public class BELScriptProblem implements IProblem {
    private boolean warning;
    private boolean error;
    private boolean task;
    private int linenum;
    private int srcstart;
    private int srcend;
    private final String filename;
    private final String msg;

    /**
     * Creates a BEL script problem for the provided filename and with the
     * provided message.
     * 
     * @param filename
     *            File name for which this problem occurs
     * @param msg
     *            Description of the problem
     */
    public BELScriptProblem(final String filename, final String msg) {
        this.filename = filename;
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
        return filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceEnd() {
        return srcend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceLineNumber() {
        return linenum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSourceStart() {
        return srcstart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isError() {
        return error;
    }

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
     * {@inheritDoc}
     */
    @Override
    public void setSourceEnd(int i) {
        srcend = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSourceLineNumber(int i) {
        linenum = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSourceStart(int i) {
        srcstart = i;
    }

    public void setError(boolean b) {
        error = b;
    }

    public void setWarning(boolean b) {
        warning = b;
    }

    public void setTask(boolean b) {
        task = b;
    }

}
