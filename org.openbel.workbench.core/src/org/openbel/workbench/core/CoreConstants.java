package org.openbel.workbench.core;

/**
 * OpenBEL Workbench core constants.
 * 
 * @see CoreFunctions
 */
public class CoreConstants {

    /** {@value} */
    public final static String BUILDER_FILE_NAME = "kam_builder.xml";

    /**
     * The set of BEL Script keywords.
     */
    public static String[] BELSCRIPT_KEYWORDS = {
        "SET",
        "DEFINE",
        "UNSET",
        "DOCUMENT",
        "ANNOTATION",
        "NAMESPACE",
        "AS",
        "URL",
        "PATTERN",
        "LIST",
        "STATEMENT_GROUP",
        "DEFAULT"
    };

    /**
     * The set of BEL functions and any abbreviations.
     */
    public static String[] FUNCTIONS = {
        "a",
        "abundance",
        "act",
        "biologicalProcess",
        "bp",
        "cat",
        "catalyticActivity",
        "cellSecretion",
        "cellSurfaceExpression",
        "chap",
        "chaperoneActivity",
        "complex",
        "complexAbundance",
        "composite",
        "compositeAbundance",
        "deg",
        "degradation",
        "fus",
        "fusion",
        "g",
        "geneAbundance",
        "gtp",
        "gtpBoundActivity",
        "kin",
        "kinaseActivity",
        "list",
        "m",
        "microRNAAbundance",
        "molecularActivity",
        "p",
        "path",
        "pathology",
        "pep",
        "peptidaseActivity",
        "phos",
        "phosphataseActivity",
        "pmod",
        "products",
        "proteinAbundance",
        "proteinModification",
        "r",
        "reactants",
        "reaction",
        "ribo",
        "ribosylationActivity",
        "rnaAbundance",
        "rxn",
        "sec",
        "sub",
        "substitution",
        "surf",
        "tloc",
        "tport",
        "transcriptionalActivity",
        "translocation",
        "transportActivity",
        "trunc",
        "truncation",
        "tscript"
    };

    /**
     * The set of BEL relationships and any abbreviations.
     */
    public static String[] RELATIONSHIPS = {
        "=>",
        "=|",
        ">>",
        "->",
        "-|",
        "--",
        ":>",
        "analogous",
        "association",
        "biomarkerFor",
        "causesNoChange",
        "decreases",
        "directlyDecreases",
        "directlyIncreases",
        "hasComponent",
        "hasComponents",
        "hasMember",
        "hasMembers",
        "increases",
        "isA",
        "negativeCorrelation",
        "orthologous",
        "positiveCorrelation",
        "prognosticBiomarkerFor",
        "rateLimitingStepOf",
        "subProcessOf",
        "transcribedTo",
        "translatedTo"
    };

}
