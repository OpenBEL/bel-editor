package org.openbel.workbench.core.common.lang;

import org.openbel.workbench.core.common.Strings;

/**
 * Specifies the abundance of a protein translated from the fusion of a gene.
 * <p>
 * Function {@link Signature signature(s)}:
 * 
 * <pre>
 * fusion(E:geneAbundance,E:geneAbundance)proteinAbundance
 * </pre>
 * 
 * </p>
 * 
 * @see Signature
 */
public class Fusion extends Function {

    /**
     * {@link Strings#FUSION}
     */
    public final static String NAME;

    /**
     * {@link Strings#FUSION_ABBREV}
     */
    public final static String ABBREVIATION;

    /**
     * Function description.
     */
    public final static String DESC;

    static {
        NAME = Strings.FUSION;
        ABBREVIATION = Strings.FUSION_ABBREV;
        DESC = "Specifies the abundance of a protein translated from the " +
                "fusion of a gene";
    }

    public Fusion() {
        super(NAME, ABBREVIATION, DESC,
                "fusion(E:geneAbundance)fusion",
                "fusion(E:geneAbundance,E:*,E:*)fusion",
                "fusion(E:proteinAbundance)fusion",
                "fusion(E:proteinAbundance,E:*,E:*)fusion",
                "fusion(E:rnaAbundance)fusion",
                "fusion(E:rnaAbundance,E:*,E:*)fusion");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validArgumentCount(int count) {
        switch (count) {
        case 1:
        case 3:
            return true;
        default:
            return false;
        }
    }
}
