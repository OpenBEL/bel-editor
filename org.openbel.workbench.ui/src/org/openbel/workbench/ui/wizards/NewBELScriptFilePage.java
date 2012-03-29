package org.openbel.workbench.ui.wizards;

import static java.lang.System.getProperty;
import static org.openbel.workbench.core.Nature.BEL_NATURE;
import static org.openbel.workbench.ui.Activator.logError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public class NewBELScriptFilePage extends NewSourceModulePage {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPageDescription() {
        return "Create a new BEL document.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPageTitle() {
        return "New BEL Document";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileContent() {
        String name = "/org/openbel/workbench/ui/template.bel";
        InputStream is = getClass().getResourceAsStream(name);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        final StringBuilder bldr = new StringBuilder();
        String input = null;
        try {
            while ((input = br.readLine()) != null) {
                bldr.append(input);
                bldr.append("\n");
            }
        } catch (IOException e) {
            logError(e);
            return null;
        }
        String ret = bldr.toString();
        String user = getProperty("user.name");
        ret = ret.replace("YOUR NAME", user);
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRequiredNature() {
        return BEL_NATURE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getFileExtensions() {
        return new String[] { "bel" };
    }
}
