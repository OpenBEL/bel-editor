/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.ui.editor;

import org.eclipse.jface.text.rules.RuleBasedScanner;

/**
 * 
 * @author julianray
 *
 */
public class BELGrammar extends RuleBasedScanner {

	public static final String[] controlWords = {
			"SET", 
			"UNSET", 
			"DEFINE", 
			"AS"
			};

	public static final String[] objectWords = {
			"DOCUMENT", 
			"NAMESPACE", 
			"ANNOTATION", 
			"URL", 
			"STATEMENT_GROUP"
			};
	
	public static final String[] belFunctions = {
			"abundance", 
			"a",
			"complexAbundance", 
			"complex",
			"compositeAbundance", 
			"composite",
			"geneAbundance",
			"g",
			"microRNAAbundance",
			"m",
			"proteinAbundance",
			"p",
			"rnaAbundance",
			"r",
			"fusion", 
			"fus",
			"proteinModification",
			"pmod",
			"substitution",
			"sub",
			"truncation",
			"trunc",
			"biologicalProcess",
			"bp",
			"pathology",
			"path",
			"cellSecretion", 
			"sec",
			"cellSurfaceExpression",
			"surf",
			"degradation",
			"deg",
			"reaction",
			"rxn",
			"translocation", 
			"tloc",
			"catalyticActivity",
			"cat",
			"chaperoneActivity", 
			"chap",
			"gtpBoundActivity",
			"gtp",
			"kinaseActivity",
			"kin",
			"molecularActivity", 
			"act",
			"peptidaseActivity",
			"pep",
			"phosphataseActivity",
			"phos",
			"ribosylationActivity", 
			"ribo",
			"transcriptionalActivity",
			"tscript",
			"transportActivity",
			"tport",
			"list",
			"products",
			"reactants"
	};
	
	public static final String[] relationshipWords = {
			"-|", 
			"=|", 
			"->", 
			"=>", 
			"--", 
			":>", 
			">>", 
			"causesNoChange", 
			"decreases",
			"directlyDecreases", 
			"increases",
			"directlyIncreases", 
			"negativeCorrelation",
			"positiveCorrelation",
			"analogous",
			"orthologous",
			"transcribedTo",
			"translatedTo",
			"association", 
			"biomarkerFor",
			"hasComponent", 
			"hasComponents",
			"hasMember", 
			"hasMembers",
			"isA",
			"prognosticBiomakerFor",
			"rateLimitingStepOf",
			"subProcessOf"
	};
				
}
