package org.openbel.workbench.core.parser;

import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

/**
 * Returns instances of the BEL script source parser.
 */
public class BELScriptSourceParserFactory implements ISourceParserFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public ISourceParser createSourceParser() {
        return new BELScriptSourceParser();
    }

}
