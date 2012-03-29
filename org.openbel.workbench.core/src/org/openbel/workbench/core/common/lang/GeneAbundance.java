package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Denotes the abundance of a gene.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * geneAbundance(E:geneAbundance)geneAbundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class GeneAbundance extends Function {

    /**
     * {@link Strings#GENE_ABUNDANCE}
     */
    public final static String NAME;

    /**
     * {@link Strings#GENE_ABUNDANCE_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.GENE_ABUNDANCE;
        ABBREVIATION = Strings.GENE_ABUNDANCE_ABBREV;
        DESC = "Denotes the abundance of a gene";
    }

    public GeneAbundance() {
        super(NAME, ABBREVIATION, DESC,
                "geneAbundance(E:geneAbundance)geneAbundance",
                "geneAbundance(E:geneAbundance,F:fusion)geneAbundance");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 1:
        case 2:
            return true;
        default:
            return false;
        }
    }
}
