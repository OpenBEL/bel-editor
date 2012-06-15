/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.selection;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.openbel.editor.core.common.enums.FunctionEnum;
import org.openbel.editor.core.common.enums.RelationshipType;
import org.openbel.editor.core.common.lang.Signature;
import org.openbel.editor.ui.util.StackUtilities;

/**
 * Provide hover-help documentation to BELScript source.
 */
public class BELDocumentationProvider implements IScriptDocumentationProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getInfo(IMember element, boolean lookIntoParents,
            boolean lookIntoExternal) {
        // TODO show comments for functions if there is any
        return new StringReader(StackUtilities.myFrame().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getInfo(String content) {
        FunctionEnum fx = FunctionEnum.fromString(content);
        if (fx != null) {
            final StringBuilder bldr = new StringBuilder();
            String description = description(fx);
            if (description != null) {
                bldr.append(description);
                bldr.append("<p>");
            }
            bldr.append("<dl><dt><b>Function</b></dt><dd>");
            bldr.append(fx.getDisplayValue());
            String abbrev = fx.getAbbreviation();
            if (abbrev != null) {
                bldr.append(" (abbreviated <i>");
                bldr.append(abbrev);
                bldr.append("</i>)</dd>");
            }
            bldr.append("</dd><dt><b>Returns</b></dt><dd>");
            bldr.append(fx.getReturnType().getDisplayValue());
            bldr.append("</dd>");
            bldr.append("<dt><b>Signatures</b></dt><dd><tt>");
            for (Signature sig : fx.getFunction().getSignatures()) {
                bldr.append(sig);
                bldr.append("<br/>");
            }
            bldr.append("</tt></dd></dl>");
            return new StringReader(bldr.toString());
        }

        RelationshipType rel = RelationshipType.fromString(content);
        if (rel == null) {
            rel = RelationshipType.fromAbbreviation(content);
        }
        if (rel != null) {
            final StringBuilder bldr = new StringBuilder();
            bldr.append("<dl><dt>");
            bldr.append(rel.getDisplayValue());
            String abbrev = rel.getAbbreviation();
            if (abbrev != null) {
                bldr.append(" (abbreviated <i>");
                bldr.append(abbrev);
                bldr.append("</i>)");
            }
            bldr.append("</dt><dd>");
            bldr.append(description(rel));
            bldr.append("</dd></dl>");
            return new StringReader(bldr.toString());
        }

        return null;
        // logInfo("%s: unrecognized content: " + content);
    }

    private static String description(RelationshipType rel) {
        switch (rel) {
        case ACTS_IN:
            return "This relationship links an abundance term to the activity term for the same abundance. This relationship is direct because it is a self relationship, the abundance acts in its own activity. For protein abundance p(A) and its molecular activity kin(p(A), p(A) actsIn kin(p(A)). This relationship is introduced by the BEL compiler and does not need to be specified by statements in BEL documents.";
        case ANALOGOUS:
            return "For terms A and B, A analogousTo B indicates that A and B represent abundances or molecular activities in different species which function in a similar manner.";
        case ASSOCIATION:
            return "For terms A and B, A association B or A -- B indicates that A and B are associated in an unspecified manner. This relationship is used when not enough information about the association is available to describe it using more specific relationships, like increases or positiveCorrelation.";
        case BIOMARKER_FOR:
            return "For term A and process term P, A biomarkerFor P indicates that changes in or detection of A is used in some way to be a biomarker for pathology or biological process P.";
        case CAUSES_NO_CHANGE:
            return "For terms A and B, A causesNoChange B indicates that B was observed not to change in response to changes in A. Statements using this relationship correspond to cases where explicit measurement of B demonstrates lack of significant change, not for cases where the state of B is unknown.";
        case DECREASES:
            return "For terms A and B, A decreases B or A -| B indicates that increases in A have been observed to cause decreases in B. A decreases B also represents cases where decreases in A have been observed to cause increases in B, for example, in recording the results of gene deletion or other inhibition experiments. A is a BEL Term and B is either a BEL Term or a BEL Statement. The relationship does not indicate that the changes in A are either necessary for changes in B, nor does it indicate that changes in A are sufficient to cause changes in B.";
        case DIRECTLY_DECREASES:
            return "For terms A and B, A directlyDecreases B or A =| B indicates A decreases B and that the mechanism of the causal relationship is based on physical interaction of entities related to A and B. This is a direct version of the decreases relationship.";
        case DIRECTLY_INCREASES:
            return "For terms A and B, A directlyIncreases B or A => B indicate A increases B and that the mechanism of the causal relationship is based on physical interaction of entities related to A and B. This is a direct version of the increases relationship.";
        case HAS_COMPONENT:
            return "For complexAbundance term A and abundance term B, A hasComponent B designates B as a component of A, that complexes that are instances of A have instances of B as possible components. Note that, the stoichiometry of A is not described, nor is it stated that B is a required component. The use of hasComponent relationships is complementary to the use of functionally composed complexes and is intended to enable the assignment of components to complexes designated by names in external vocabularies. The assignment of components can potentially enable the reconciliation of equivalent complexes at knowledge assembly time.";
        case HAS_COMPONENTS:
            return "The hasComponents relationship is a special form which enables the assignment of multiple complex components in a single statement where the object of the statement is a set of abundance terms. A statement using hasComponents is exactly equivalent to multiple hasComponent statements. A term may not appear in both the subject and object of the same hasComponents statement. For the abundance terms A, B, C and D, A hasComponents B, C, D indicates that A has components B, C and D.";
        case HAS_MEMBER:
            return "For term abundances A and B, A hasMember B designates B as a member class of A. A member class is a distinguished sub-class. A is defined as a group by all of the members assigned to it. The member classes may or may not be overlapping and may or may not entirely cover all instances of A. A term may not appear in both the subject and object of the same hasMember statement.";
        case HAS_MEMBERS:
            return "The hasMembers relationship is a special form which enables the assignment of multiple member classes in a single statement where the object of the statement is a set of abundance terms. A statement using hasMembers is exactly equivalent to multiple hasMember statements. A term may not appear in both the subject and object of a of the same hasMembers statement. For the abundance terms A, B, C and D, A hasMembers B, C, D indicates that A is defined by its member abundance classes B, C and D.";
        case HAS_MODIFICATION:
            return "This relationship links abundance terms modified by the pmod() function to the unmodified abundance term. This is a direct relationship because it is a self relationship. This relationship is introduced by the BEL Compiler and does not need to be specified by statements in BEL Documents.";
        case HAS_PRODUCT:
            return "This relationship links abundance terms from the products(<list>) in a reaction to the reaction. This is a direct relationship because it is a self relationship. Products are produced directly by a reaction. This relationship is introduced by the BEL compiler and does not need to be specified by statements in BEL documents.";
        case HAS_VARIANT:
            return "This relationship links abundance terms modified by the substitution(), fusion(), or truncation() functions to the unmodified abundance term. This relationship is introduced by the BEL Compiler and does not need to be specified by statements in BEL Documents.";
        case INCLUDES:
            return "This relationship links each individual abundance term in a compositeAbundance(<list>) to the compositeAbundance. For example, compositeAbundance(A, B) includes A and compositeAbundance(A, B) includes B. This relationship is direct because it is a self relationship. This relationship is introduced by the BEL compiler and does not need to be specified by statements in BEL documents.";
        case INCREASES:
            return "For terms A and B, A increases B or A -> B indicates that increases in A have been observed to cause increases in B. A increases B also represents cases where decreases in A have been observed to cause decreases in B, for example, in recording the results of gene deletion or other inhibition experiments. A is a BEL Term and B is either a BEL Term or a BEL Statement. The relationship does not indicate that the changes in A are either necessary for changes in B, nor does it indicate that changes in A are sufficient to cause changes in B.";
        case IS_A:
            return "For terms A and B, A isA B indicates that A is a subset of B. All terms in BEL represent classes, but given that classes implicitly have instances, one can also interpret A isA B to mean that any instance of A must also be an instance of B. This relationship can be used to represent GO and MeSH hierarchies: pathology(MESH:Psoriasis) isA pathology(MESH:\"Skin Diseases\").";
        case NEGATIVE_CORRELATION:
            return "For terms A and B, A negativeCorrelation B indicates that changes in A and B have been observed to be negatively correlated. The order of the subject and object does not affect the interpretation of the statement, thus B negativeCorrelation A is equivalent to A negativeCorrelation B.";
        case ORTHOLOGOUS:
            return "For geneAbundance terms A and B, A orthologousTo B indicates that A and B represent abundances of genes in different species which are sequence similar and which are therefore presumed to share a common ancestral gene. For example, g(HGNC:AKT1) orthologousTo g(MGI:AKT1) indicates that the mouse and human AKT1 genes are orthologous.";
        case POSITIVE_CORRELATION:
            return "For terms A and B, A positiveCorrelation B indicates that changes in A and B have been observed to be positively correlated, thus B positiveCorrelation A is equivalent to A positiveCorrelation B.";
        case PROGNOSTIC_BIOMARKER_FOR:
            return "For term A and process term P, A prognosticBiomarkerFor P indicates that changes in or detection of A is used in some way to be a prognostic biomarker for the subsequent development of pathology or biological process P.";
        case RATE_LIMITING_STEP_OF:
            return "For process, activity, or transformation term A and process term B, A rateLimitingStepOf B indicates A subProcessOf B and A -> B. For example, the catalytic activity of HMG CoA reductase is a rate-limiting step for cholesterol biosynthesis.";
        case REACTANT_IN:
            return "This relationship links abundance terms from the reactants(<list>) in a reaction to the reaction. This is a direct relationship because it is a self relationship. Reactants are consumed directly by a reaction. This relationship is introduced by the BEL compiler and does not need to be specified by statements in BEL documents.";
        case SUB_PROCESS_OF:
            return "For process, activity, or transformation term A and process term B, A subProcessOf B indicates that instances of process B, by default, include one or more instances of A in their composition. For example, the reduction of HMG-CoA to mevalonate is a subprocess of cholesterol biosynthesis.";
        case TRANSCRIBED_TO:
            return "For rnaAbundance term R and geneAbundance term G, G transcribedTo R or G :> R indicates that members of R are produced by the transcription of members of G. For example: g(HGNC:AKT1) :> r(HGNC:AKT1) indicates that the human AKT1 RNA is transcribed from the human AKT1 gene.";
        case TRANSLATED_TO:
            return "For rnaAbundance term R and proteinAbundance term P, R translatedTo P or R >> P indicates that members of P are produced by the translation of members of R. For example: r(HGNC:AKT1) >> p(HGNC:AKT1) indicates that AKT1 protein is produced by translation of AKT1 RNA.";
        case TRANSLOCATES:
            return "This relationship links the abundance term in a translocation() to the translocation. This relationship is direct because it is a self relationship. The translocated abundance is directly acted on by the translocation process. This relationship is introduced by the BEL compiler and does not need to be specified by statements in BEL documents.";
        }
        return null;
    }

    private static String description(FunctionEnum fx) {
        switch (fx) {
        case ABUNDANCE:
            return "Denotes the abundance of an entity.";
        case BIOLOGICAL_PROCESS:
            return "Denotes a process or population of events.";
        case CATALYTIC_ACTIVITY:
            return "Denotes the frequency or abundance of events where a member acts as an enzymatic catalyst of biochecmial reactions.";
        case CELL_SECRETION:
            return "Denotes the frequency or abundance of events in which members of an abundance move from cells to regions outside of the cells.";
        case CHAPERONE_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member binds to some substrate and acts as a chaperone for the substrate.";
        case CELL_SURFACE_EXPRESSION:
            return "Denotes the frequency or abundance of events in which members of an abundance move to the surface of cells.";
        case COMPLEX_ABUNDANCE:
            return "Denotes the abundance of a molecular complex.";
        case COMPOSITE_ABUNDANCE:
            return "Denotes the frequency or abundance of events in which members are present.";
        case DEGRADATION:
            return "Denotes the frequency or abundance of events in which a member is degraded in some way such that it is no longer a member.";
        case FUSION:
            return "Specifies the abundance of a protein translated from the fusion of a gene.";
        case GENE_ABUNDANCE:
            return "Denotes the abundance of a gene.";
        case GTP_BOUND_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member of a G-protein abundance is GTP-bound.";
        case KINASE_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member acts as a kinase, performing enzymatic phosphorylation of a substrate.";
        case LIST:
            return "Denotes a list of terms.";
        case MICRORNA_ABUNDANCE:
            return "Denotes the abundance of a processed, functional microRNA.";
        case MOLECULAR_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member acts as a causal agent at the molecular scale.";
        case PATHOLOGY:
            return "Denotes a disease or pathology process.";
        case PEPTIDASE_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member acts to cleave a protein.";
        case PHOSPHATASE_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member acts as a phosphatase.";
        case PRODUCTS:
            return "Denotes the products of a reaction.";
        case PROTEIN_ABUNDANCE:
            return "Denotes the abundance of a protein.";
        case PROTEIN_MODIFICATION:
            return "Denotes a covalently modified protein abundance.";
        case REACTANTS:
            return "Denotes the reactants of a reaction.";
        case REACTION:
            return "Denotes the frequency or abundance of events in a reaction.";
        case RIBOSYLATION_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member acts to perform post-translational modification of proteins.";
        case RNA_ABUNDANCE:
            return "Denotes the abundance of a gene.";
        case SUBSTITUTION:
            return "Indicates the abundance of proteins with amino acid substitution sequence.";
        case TRANSCRIPTIONAL_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member directly acts to control transcription of genes.";
        case TRANSLOCATION:
            return "Denotes the frequency or abundance of events in which members move between locations.";
        case TRANSPORT_ACTIVITY:
            return "Denotes the frequency or abundance of events in which a member directs acts to enable the directed movement of substances into, out of, within, or between cells.";
        case TRUNCATION:
            return "Indicates an abundance of proteins with truncation sequence variants.";
        }
        return null;
    }

}
