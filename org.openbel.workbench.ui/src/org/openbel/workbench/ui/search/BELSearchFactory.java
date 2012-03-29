package org.openbel.workbench.ui.search;

import org.eclipse.dltk.core.search.AbstractSearchFactory;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.matching.MatchLocator;

public class BELSearchFactory extends AbstractSearchFactory {
    @Override
    public IMatchLocatorParser createMatchParser(MatchLocator locator) {
        return new BELMatchLocationParser(locator);
    }
}