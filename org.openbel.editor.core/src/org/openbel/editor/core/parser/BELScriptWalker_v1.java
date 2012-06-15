/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.editor.core.parser;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

@SuppressWarnings({ "all", "warnings", "unchecked" })
public class BELScriptWalker_v1 extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANNO_DEF_LIST",
        "ANNO_DEF_PTRN", "ANNO_DEF_URL", "ANNO_SET_ID", "ANNO_SET_LIST",
        "ANNO_SET_QV", "COLON", "COMMA", "DFLT_NSDEF", "DIGIT", "DOCDEF",
        "DOCSET_ID", "DOCSET_LIST", "DOCSET_QV", "DOCUMENT_COMMENT", "EQ",
        "ESCAPE_SEQUENCE", "HEX_DIGIT", "IDENT_LIST", "KWRD_ANNO", "KWRD_AS",
        "KWRD_AUTHORS", "KWRD_CONTACTINFO", "KWRD_COPYRIGHT", "KWRD_DEFINE",
        "KWRD_DESC", "KWRD_DFLT", "KWRD_DISCLAIMER", "KWRD_DOCUMENT",
        "KWRD_LICENSES", "KWRD_LIST", "KWRD_NAME", "KWRD_NS", "KWRD_PATTERN",
        "KWRD_SET", "KWRD_STMT_GROUP", "KWRD_UNSET", "KWRD_URL",
        "KWRD_VERSION", "LETTER", "LP", "NEWLINE", "NSDEF", "OBJECT_IDENT",
        "OCTAL_ESCAPE", "PARAM_DEF_ID", "PARAM_DEF_QV", "QUOTED_VALUE", "RP",
        "SG_SET_ID", "SG_SET_QV", "STATEMENT_COMMENT", "STMTDEF", "TERMDEF",
        "UNICODE_ESCAPE", "UNSET_ID", "UNSET_ID_LIST", "UNSET_SG",
        "VALUE_LIST", "WS", "'--'", "'->'", "'-|'", "':>'", "'=>'", "'=|'",
        "'>>'", "'a'", "'abundance'", "'act'", "'analogous'", "'association'",
        "'biologicalProcess'", "'biomarkerFor'", "'bp'", "'cat'",
        "'catalyticActivity'", "'causesNoChange'", "'cellSecretion'",
        "'cellSurfaceExpression'", "'chap'", "'chaperoneActivity'",
        "'complex'", "'complexAbundance'", "'composite'",
        "'compositeAbundance'", "'decreases'", "'deg'", "'degradation'",
        "'directlyDecreases'", "'directlyIncreases'", "'fus'", "'fusion'",
        "'g'", "'geneAbundance'", "'gtp'", "'gtpBoundActivity'",
        "'hasComponent'", "'hasComponents'", "'hasMember'", "'hasMembers'",
        "'increases'", "'isA'", "'kin'", "'kinaseActivity'", "'list'", "'m'",
        "'microRNAAbundance'", "'molecularActivity'", "'negativeCorrelation'",
        "'orthologous'", "'p'", "'path'", "'pathology'", "'pep'",
        "'peptidaseActivity'", "'phos'", "'phosphataseActivity'", "'pmod'",
        "'positiveCorrelation'", "'products'", "'prognosticBiomarkerFor'",
        "'proteinAbundance'", "'proteinModification'", "'r'",
        "'rateLimitingStepOf'", "'reactants'", "'reaction'", "'ribo'",
        "'ribosylationActivity'", "'rnaAbundance'", "'rxn'", "'sec'", "'sub'",
        "'subProcessOf'", "'substitution'", "'surf'", "'tloc'", "'tport'",
        "'transcribedTo'", "'transcriptionalActivity'", "'translatedTo'",
        "'translocation'", "'transportActivity'", "'trunc'", "'truncation'",
        "'tscript'", "AUTHDEF", "CONTACTDEF", "COPYDEF", "DESCDEF", "DISCDEF",
        "LICDEF", "NAMEDEF", "VERSIONDEF"
    };

    public static final int EOF = -1;
    public static final int T__64 = 64;
    public static final int T__65 = 65;
    public static final int T__66 = 66;
    public static final int T__67 = 67;
    public static final int T__68 = 68;
    public static final int T__69 = 69;
    public static final int T__70 = 70;
    public static final int T__71 = 71;
    public static final int T__72 = 72;
    public static final int T__73 = 73;
    public static final int T__74 = 74;
    public static final int T__75 = 75;
    public static final int T__76 = 76;
    public static final int T__77 = 77;
    public static final int T__78 = 78;
    public static final int T__79 = 79;
    public static final int T__80 = 80;
    public static final int T__81 = 81;
    public static final int T__82 = 82;
    public static final int T__83 = 83;
    public static final int T__84 = 84;
    public static final int T__85 = 85;
    public static final int T__86 = 86;
    public static final int T__87 = 87;
    public static final int T__88 = 88;
    public static final int T__89 = 89;
    public static final int T__90 = 90;
    public static final int T__91 = 91;
    public static final int T__92 = 92;
    public static final int T__93 = 93;
    public static final int T__94 = 94;
    public static final int T__95 = 95;
    public static final int T__96 = 96;
    public static final int T__97 = 97;
    public static final int T__98 = 98;
    public static final int T__99 = 99;
    public static final int T__100 = 100;
    public static final int T__101 = 101;
    public static final int T__102 = 102;
    public static final int T__103 = 103;
    public static final int T__104 = 104;
    public static final int T__105 = 105;
    public static final int T__106 = 106;
    public static final int T__107 = 107;
    public static final int T__108 = 108;
    public static final int T__109 = 109;
    public static final int T__110 = 110;
    public static final int T__111 = 111;
    public static final int T__112 = 112;
    public static final int T__113 = 113;
    public static final int T__114 = 114;
    public static final int T__115 = 115;
    public static final int T__116 = 116;
    public static final int T__117 = 117;
    public static final int T__118 = 118;
    public static final int T__119 = 119;
    public static final int T__120 = 120;
    public static final int T__121 = 121;
    public static final int T__122 = 122;
    public static final int T__123 = 123;
    public static final int T__124 = 124;
    public static final int T__125 = 125;
    public static final int T__126 = 126;
    public static final int T__127 = 127;
    public static final int T__128 = 128;
    public static final int T__129 = 129;
    public static final int T__130 = 130;
    public static final int T__131 = 131;
    public static final int T__132 = 132;
    public static final int T__133 = 133;
    public static final int T__134 = 134;
    public static final int T__135 = 135;
    public static final int T__136 = 136;
    public static final int T__137 = 137;
    public static final int T__138 = 138;
    public static final int T__139 = 139;
    public static final int T__140 = 140;
    public static final int T__141 = 141;
    public static final int T__142 = 142;
    public static final int T__143 = 143;
    public static final int T__144 = 144;
    public static final int T__145 = 145;
    public static final int T__146 = 146;
    public static final int T__147 = 147;
    public static final int T__148 = 148;
    public static final int T__149 = 149;
    public static final int T__150 = 150;
    public static final int ANNO_DEF_LIST = 4;
    public static final int ANNO_DEF_PTRN = 5;
    public static final int ANNO_DEF_URL = 6;
    public static final int ANNO_SET_ID = 7;
    public static final int ANNO_SET_LIST = 8;
    public static final int ANNO_SET_QV = 9;
    public static final int COLON = 10;
    public static final int COMMA = 11;
    public static final int DFLT_NSDEF = 12;
    public static final int DIGIT = 13;
    public static final int DOCDEF = 14;
    public static final int DOCSET_ID = 15;
    public static final int DOCSET_LIST = 16;
    public static final int DOCSET_QV = 17;
    public static final int DOCUMENT_COMMENT = 18;
    public static final int EQ = 19;
    public static final int ESCAPE_SEQUENCE = 20;
    public static final int HEX_DIGIT = 21;
    public static final int IDENT_LIST = 22;
    public static final int KWRD_ANNO = 23;
    public static final int KWRD_AS = 24;
    public static final int KWRD_AUTHORS = 25;
    public static final int KWRD_CONTACTINFO = 26;
    public static final int KWRD_COPYRIGHT = 27;
    public static final int KWRD_DEFINE = 28;
    public static final int KWRD_DESC = 29;
    public static final int KWRD_DFLT = 30;
    public static final int KWRD_DISCLAIMER = 31;
    public static final int KWRD_DOCUMENT = 32;
    public static final int KWRD_LICENSES = 33;
    public static final int KWRD_LIST = 34;
    public static final int KWRD_NAME = 35;
    public static final int KWRD_NS = 36;
    public static final int KWRD_PATTERN = 37;
    public static final int KWRD_SET = 38;
    public static final int KWRD_STMT_GROUP = 39;
    public static final int KWRD_UNSET = 40;
    public static final int KWRD_URL = 41;
    public static final int KWRD_VERSION = 42;
    public static final int LETTER = 43;
    public static final int LP = 44;
    public static final int NEWLINE = 45;
    public static final int NSDEF = 46;
    public static final int OBJECT_IDENT = 47;
    public static final int OCTAL_ESCAPE = 48;
    public static final int PARAM_DEF_ID = 49;
    public static final int PARAM_DEF_QV = 50;
    public static final int QUOTED_VALUE = 51;
    public static final int RP = 52;
    public static final int SG_SET_ID = 53;
    public static final int SG_SET_QV = 54;
    public static final int STATEMENT_COMMENT = 55;
    public static final int STMTDEF = 56;
    public static final int TERMDEF = 57;
    public static final int UNICODE_ESCAPE = 58;
    public static final int UNSET_ID = 59;
    public static final int UNSET_ID_LIST = 60;
    public static final int UNSET_SG = 61;
    public static final int VALUE_LIST = 62;
    public static final int WS = 63;
    public static final int AUTHDEF = 151;
    public static final int CONTACTDEF = 152;
    public static final int COPYDEF = 153;
    public static final int DESCDEF = 154;
    public static final int DISCDEF = 155;
    public static final int LICDEF = 156;
    public static final int NAMEDEF = 157;
    public static final int VERSIONDEF = 158;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators

    public BELScriptWalker_v1(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }

    public BELScriptWalker_v1(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() {
        return BELScriptWalker_v1.tokenNames;
    }

    public String getGrammarFileName() {
        return "BELScriptWalker_v1.g";
    }

    public static class document_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "document"
    // BELScriptWalker_v1.g:39:1: document : ^( DOCDEF ( record )+ ) ;
    public final BELScriptWalker_v1.document_return document()
            throws RecognitionException {
        BELScriptWalker_v1.document_return retval = new BELScriptWalker_v1.document_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DOCDEF1 = null;
        BELScriptWalker_v1.record_return record2 = null;

        CommonTree DOCDEF1_tree = null;

        try {
            // BELScriptWalker_v1.g:40:5: ( ^( DOCDEF ( record )+ ) )
            // BELScriptWalker_v1.g:40:9: ^( DOCDEF ( record )+ )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    DOCDEF1 = (CommonTree) match(input, DOCDEF,
                            FOLLOW_DOCDEF_in_document198);
                    DOCDEF1_tree = (CommonTree) adaptor.dupNode(DOCDEF1);

                    root_1 = (CommonTree) adaptor.becomeRoot(DOCDEF1_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    // BELScriptWalker_v1.g:40:18: ( record )+
                    int cnt1 = 0;
                    loop1: do {
                        int alt1 = 2;
                        int LA1_0 = input.LA(1);

                        if ((LA1_0 == ANNO_DEF_LIST
                                || (LA1_0 >= ANNO_DEF_URL && LA1_0 <= ANNO_SET_QV)
                                || LA1_0 == DFLT_NSDEF
                                || (LA1_0 >= DOCSET_ID && LA1_0 <= DOCSET_QV)
                                || LA1_0 == NSDEF
                                || (LA1_0 >= SG_SET_ID && LA1_0 <= SG_SET_QV)
                                || LA1_0 == STMTDEF || (LA1_0 >= UNSET_ID && LA1_0 <= UNSET_SG))) {
                            alt1 = 1;
                        }

                        switch (alt1) {
                        case 1:
                        // BELScriptWalker_v1.g:40:18: record
                        {
                            _last = (CommonTree) input.LT(1);
                            pushFollow(FOLLOW_record_in_document200);
                            record2 = record();

                            state._fsp--;

                            adaptor.addChild(root_1, record2.getTree());

                        }
                            break;

                        default:
                            if (cnt1 >= 1) break loop1;
                            EarlyExitException eee =
                                    new EarlyExitException(1, input);
                            throw eee;
                        }
                        cnt1++;
                    } while (true);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "document"

    public static class record_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "record"
    // BELScriptWalker_v1.g:43:1: record : ( define_namespace | define_annotation | set_annotation | set_document | set_statement_group | unset_statement_group | unset | statement );
    public final BELScriptWalker_v1.record_return record()
            throws RecognitionException {
        BELScriptWalker_v1.record_return retval = new BELScriptWalker_v1.record_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        BELScriptWalker_v1.define_namespace_return define_namespace3 = null;

        BELScriptWalker_v1.define_annotation_return define_annotation4 = null;

        BELScriptWalker_v1.set_annotation_return set_annotation5 = null;

        BELScriptWalker_v1.set_document_return set_document6 = null;

        BELScriptWalker_v1.set_statement_group_return set_statement_group7 = null;

        BELScriptWalker_v1.unset_statement_group_return unset_statement_group8 = null;

        BELScriptWalker_v1.unset_return unset9 = null;

        BELScriptWalker_v1.statement_return statement10 = null;

        try {
            // BELScriptWalker_v1.g:44:5: ( define_namespace | define_annotation | set_annotation | set_document | set_statement_group | unset_statement_group | unset | statement )
            int alt2 = 8;
            switch (input.LA(1)) {
            case DFLT_NSDEF:
            case NSDEF: {
                alt2 = 1;
            }
                break;
            case ANNO_DEF_LIST:
            case ANNO_DEF_URL: {
                alt2 = 2;
            }
                break;
            case ANNO_SET_ID:
            case ANNO_SET_LIST:
            case ANNO_SET_QV: {
                alt2 = 3;
            }
                break;
            case DOCSET_ID:
            case DOCSET_LIST:
            case DOCSET_QV: {
                alt2 = 4;
            }
                break;
            case SG_SET_ID:
            case SG_SET_QV: {
                alt2 = 5;
            }
                break;
            case UNSET_SG: {
                alt2 = 6;
            }
                break;
            case UNSET_ID:
            case UNSET_ID_LIST: {
                alt2 = 7;
            }
                break;
            case STMTDEF: {
                alt2 = 8;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
            case 1:
            // BELScriptWalker_v1.g:44:9: define_namespace
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_define_namespace_in_record221);
                define_namespace3 = define_namespace();

                state._fsp--;

                adaptor.addChild(root_0, define_namespace3.getTree());

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:45:9: define_annotation
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_define_annotation_in_record231);
                define_annotation4 = define_annotation();

                state._fsp--;

                adaptor.addChild(root_0, define_annotation4.getTree());

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:46:9: set_annotation
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_set_annotation_in_record241);
                set_annotation5 = set_annotation();

                state._fsp--;

                adaptor.addChild(root_0, set_annotation5.getTree());

            }
                break;
            case 4:
            // BELScriptWalker_v1.g:47:9: set_document
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_set_document_in_record251);
                set_document6 = set_document();

                state._fsp--;

                adaptor.addChild(root_0, set_document6.getTree());

            }
                break;
            case 5:
            // BELScriptWalker_v1.g:48:9: set_statement_group
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_set_statement_group_in_record261);
                set_statement_group7 = set_statement_group();

                state._fsp--;

                adaptor.addChild(root_0, set_statement_group7.getTree());

            }
                break;
            case 6:
            // BELScriptWalker_v1.g:49:9: unset_statement_group
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_unset_statement_group_in_record271);
                unset_statement_group8 = unset_statement_group();

                state._fsp--;

                adaptor.addChild(root_0, unset_statement_group8.getTree());

            }
                break;
            case 7:
            // BELScriptWalker_v1.g:50:9: unset
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_unset_in_record281);
                unset9 = unset();

                state._fsp--;

                adaptor.addChild(root_0, unset9.getTree());

            }
                break;
            case 8:
            // BELScriptWalker_v1.g:51:9: statement
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_statement_in_record291);
                statement10 = statement();

                state._fsp--;

                adaptor.addChild(root_0, statement10.getTree());

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "record"

    public static class set_doc_expr_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_doc_expr"
    // BELScriptWalker_v1.g:54:1: set_doc_expr : KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )* ;
    public final BELScriptWalker_v1.set_doc_expr_return set_doc_expr()
            throws RecognitionException {
        BELScriptWalker_v1.set_doc_expr_return retval = new BELScriptWalker_v1.set_doc_expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree KWRD_SET11 = null;
        CommonTree WS12 = null;
        CommonTree KWRD_DOCUMENT13 = null;
        CommonTree WS14 = null;

        CommonTree KWRD_SET11_tree = null;
        CommonTree WS12_tree = null;
        CommonTree KWRD_DOCUMENT13_tree = null;
        CommonTree WS14_tree = null;

        try {
            // BELScriptWalker_v1.g:55:5: ( KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )* )
            // BELScriptWalker_v1.g:55:9: KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )*
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                KWRD_SET11 = (CommonTree) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_doc_expr310);
                KWRD_SET11_tree = (CommonTree) adaptor.dupNode(KWRD_SET11);

                adaptor.addChild(root_0, KWRD_SET11_tree);

                // BELScriptWalker_v1.g:55:18: ( WS )*
                loop3: do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == WS)) {
                        alt3 = 1;
                    }

                    switch (alt3) {
                    case 1:
                    // BELScriptWalker_v1.g:55:18: WS
                    {
                        _last = (CommonTree) input.LT(1);
                        WS12 = (CommonTree) match(input, WS,
                                FOLLOW_WS_in_set_doc_expr312);
                        WS12_tree = (CommonTree) adaptor.dupNode(WS12);

                        adaptor.addChild(root_0, WS12_tree);

                    }
                        break;

                    default:
                        break loop3;
                    }
                } while (true);

                _last = (CommonTree) input.LT(1);
                KWRD_DOCUMENT13 = (CommonTree) match(input, KWRD_DOCUMENT,
                        FOLLOW_KWRD_DOCUMENT_in_set_doc_expr315);
                KWRD_DOCUMENT13_tree = (CommonTree) adaptor
                        .dupNode(KWRD_DOCUMENT13);

                adaptor.addChild(root_0, KWRD_DOCUMENT13_tree);

                // BELScriptWalker_v1.g:55:36: ( WS )*
                loop4: do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if ((LA4_0 == WS)) {
                        alt4 = 1;
                    }

                    switch (alt4) {
                    case 1:
                    // BELScriptWalker_v1.g:55:36: WS
                    {
                        _last = (CommonTree) input.LT(1);
                        WS14 = (CommonTree) match(input, WS,
                                FOLLOW_WS_in_set_doc_expr317);
                        WS14_tree = (CommonTree) adaptor.dupNode(WS14);

                        adaptor.addChild(root_0, WS14_tree);

                    }
                        break;

                    default:
                        break loop4;
                    }
                } while (true);

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_doc_expr"

    public static class set_document_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_document"
    // BELScriptWalker_v1.g:58:1: set_document : ( ^( DOCSET_QV document_property QUOTED_VALUE ) | ^( DOCSET_LIST document_property VALUE_LIST ) | ^( DOCSET_ID document_property OBJECT_IDENT ) );
    public final BELScriptWalker_v1.set_document_return set_document()
            throws RecognitionException {
        BELScriptWalker_v1.set_document_return retval = new BELScriptWalker_v1.set_document_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DOCSET_QV15 = null;
        CommonTree QUOTED_VALUE17 = null;
        CommonTree DOCSET_LIST18 = null;
        CommonTree VALUE_LIST20 = null;
        CommonTree DOCSET_ID21 = null;
        CommonTree OBJECT_IDENT23 = null;
        BELScriptWalker_v1.document_property_return document_property16 = null;

        BELScriptWalker_v1.document_property_return document_property19 = null;

        BELScriptWalker_v1.document_property_return document_property22 = null;

        CommonTree DOCSET_QV15_tree = null;
        CommonTree QUOTED_VALUE17_tree = null;
        CommonTree DOCSET_LIST18_tree = null;
        CommonTree VALUE_LIST20_tree = null;
        CommonTree DOCSET_ID21_tree = null;
        CommonTree OBJECT_IDENT23_tree = null;

        try {
            // BELScriptWalker_v1.g:59:2: ( ^( DOCSET_QV document_property QUOTED_VALUE ) | ^( DOCSET_LIST document_property VALUE_LIST ) | ^( DOCSET_ID document_property OBJECT_IDENT ) )
            int alt5 = 3;
            switch (input.LA(1)) {
            case DOCSET_QV: {
                alt5 = 1;
            }
                break;
            case DOCSET_LIST: {
                alt5 = 2;
            }
                break;
            case DOCSET_ID: {
                alt5 = 3;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
            case 1:
            // BELScriptWalker_v1.g:59:4: ^( DOCSET_QV document_property QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    DOCSET_QV15 = (CommonTree) match(input, DOCSET_QV,
                            FOLLOW_DOCSET_QV_in_set_document333);
                    DOCSET_QV15_tree = (CommonTree) adaptor
                            .dupNode(DOCSET_QV15);

                    root_1 = (CommonTree) adaptor.becomeRoot(DOCSET_QV15_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    pushFollow(FOLLOW_document_property_in_set_document335);
                    document_property16 = document_property();

                    state._fsp--;

                    adaptor.addChild(root_1, document_property16.getTree());

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE17 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_set_document337);
                    QUOTED_VALUE17_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE17);

                    adaptor.addChild(root_1, QUOTED_VALUE17_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:60:4: ^( DOCSET_LIST document_property VALUE_LIST )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    DOCSET_LIST18 = (CommonTree) match(input, DOCSET_LIST,
                            FOLLOW_DOCSET_LIST_in_set_document344);
                    DOCSET_LIST18_tree = (CommonTree) adaptor
                            .dupNode(DOCSET_LIST18);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            DOCSET_LIST18_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    pushFollow(FOLLOW_document_property_in_set_document346);
                    document_property19 = document_property();

                    state._fsp--;

                    adaptor.addChild(root_1, document_property19.getTree());

                    _last = (CommonTree) input.LT(1);
                    VALUE_LIST20 = (CommonTree) match(input, VALUE_LIST,
                            FOLLOW_VALUE_LIST_in_set_document348);
                    VALUE_LIST20_tree = (CommonTree) adaptor
                            .dupNode(VALUE_LIST20);

                    adaptor.addChild(root_1, VALUE_LIST20_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:61:4: ^( DOCSET_ID document_property OBJECT_IDENT )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    DOCSET_ID21 = (CommonTree) match(input, DOCSET_ID,
                            FOLLOW_DOCSET_ID_in_set_document355);
                    DOCSET_ID21_tree = (CommonTree) adaptor
                            .dupNode(DOCSET_ID21);

                    root_1 = (CommonTree) adaptor.becomeRoot(DOCSET_ID21_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    pushFollow(FOLLOW_document_property_in_set_document357);
                    document_property22 = document_property();

                    state._fsp--;

                    adaptor.addChild(root_1, document_property22.getTree());

                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT23 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_document359);
                    OBJECT_IDENT23_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT23);

                    adaptor.addChild(root_1, OBJECT_IDENT23_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_document"

    public static class set_statement_group_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_statement_group"
    // BELScriptWalker_v1.g:64:1: set_statement_group : ( ^( SG_SET_QV QUOTED_VALUE ) | ^( SG_SET_ID OBJECT_IDENT ) );
    public final BELScriptWalker_v1.set_statement_group_return set_statement_group()
            throws RecognitionException {
        BELScriptWalker_v1.set_statement_group_return retval = new BELScriptWalker_v1.set_statement_group_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SG_SET_QV24 = null;
        CommonTree QUOTED_VALUE25 = null;
        CommonTree SG_SET_ID26 = null;
        CommonTree OBJECT_IDENT27 = null;

        CommonTree SG_SET_QV24_tree = null;
        CommonTree QUOTED_VALUE25_tree = null;
        CommonTree SG_SET_ID26_tree = null;
        CommonTree OBJECT_IDENT27_tree = null;

        try {
            // BELScriptWalker_v1.g:65:5: ( ^( SG_SET_QV QUOTED_VALUE ) | ^( SG_SET_ID OBJECT_IDENT ) )
            int alt6 = 2;
            int LA6_0 = input.LA(1);

            if ((LA6_0 == SG_SET_QV)) {
                alt6 = 1;
            }
            else if ((LA6_0 == SG_SET_ID)) {
                alt6 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
            case 1:
            // BELScriptWalker_v1.g:65:9: ^( SG_SET_QV QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    SG_SET_QV24 = (CommonTree) match(input, SG_SET_QV,
                            FOLLOW_SG_SET_QV_in_set_statement_group380);
                    SG_SET_QV24_tree = (CommonTree) adaptor
                            .dupNode(SG_SET_QV24);

                    root_1 = (CommonTree) adaptor.becomeRoot(SG_SET_QV24_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE25 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_set_statement_group382);
                    QUOTED_VALUE25_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE25);

                    adaptor.addChild(root_1, QUOTED_VALUE25_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:66:9: ^( SG_SET_ID OBJECT_IDENT )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    SG_SET_ID26 = (CommonTree) match(input, SG_SET_ID,
                            FOLLOW_SG_SET_ID_in_set_statement_group394);
                    SG_SET_ID26_tree = (CommonTree) adaptor
                            .dupNode(SG_SET_ID26);

                    root_1 = (CommonTree) adaptor.becomeRoot(SG_SET_ID26_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT27 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_statement_group396);
                    OBJECT_IDENT27_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT27);

                    adaptor.addChild(root_1, OBJECT_IDENT27_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_statement_group"

    public static class set_annotation_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_annotation"
    // BELScriptWalker_v1.g:69:1: set_annotation : ( ^( ANNO_SET_QV OBJECT_IDENT QUOTED_VALUE ) | ^( ANNO_SET_LIST OBJECT_IDENT VALUE_LIST ) | ^( ANNO_SET_ID OBJECT_IDENT OBJECT_IDENT ) );
    public final BELScriptWalker_v1.set_annotation_return set_annotation()
            throws RecognitionException {
        BELScriptWalker_v1.set_annotation_return retval = new BELScriptWalker_v1.set_annotation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ANNO_SET_QV28 = null;
        CommonTree OBJECT_IDENT29 = null;
        CommonTree QUOTED_VALUE30 = null;
        CommonTree ANNO_SET_LIST31 = null;
        CommonTree OBJECT_IDENT32 = null;
        CommonTree VALUE_LIST33 = null;
        CommonTree ANNO_SET_ID34 = null;
        CommonTree OBJECT_IDENT35 = null;
        CommonTree OBJECT_IDENT36 = null;

        CommonTree ANNO_SET_QV28_tree = null;
        CommonTree OBJECT_IDENT29_tree = null;
        CommonTree QUOTED_VALUE30_tree = null;
        CommonTree ANNO_SET_LIST31_tree = null;
        CommonTree OBJECT_IDENT32_tree = null;
        CommonTree VALUE_LIST33_tree = null;
        CommonTree ANNO_SET_ID34_tree = null;
        CommonTree OBJECT_IDENT35_tree = null;
        CommonTree OBJECT_IDENT36_tree = null;

        try {
            // BELScriptWalker_v1.g:70:5: ( ^( ANNO_SET_QV OBJECT_IDENT QUOTED_VALUE ) | ^( ANNO_SET_LIST OBJECT_IDENT VALUE_LIST ) | ^( ANNO_SET_ID OBJECT_IDENT OBJECT_IDENT ) )
            int alt7 = 3;
            switch (input.LA(1)) {
            case ANNO_SET_QV: {
                alt7 = 1;
            }
                break;
            case ANNO_SET_LIST: {
                alt7 = 2;
            }
                break;
            case ANNO_SET_ID: {
                alt7 = 3;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
            case 1:
            // BELScriptWalker_v1.g:70:9: ^( ANNO_SET_QV OBJECT_IDENT QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    ANNO_SET_QV28 = (CommonTree) match(input, ANNO_SET_QV,
                            FOLLOW_ANNO_SET_QV_in_set_annotation417);
                    ANNO_SET_QV28_tree = (CommonTree) adaptor
                            .dupNode(ANNO_SET_QV28);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            ANNO_SET_QV28_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT29 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_annotation419);
                    OBJECT_IDENT29_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT29);

                    adaptor.addChild(root_1, OBJECT_IDENT29_tree);

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE30 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_set_annotation421);
                    QUOTED_VALUE30_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE30);

                    adaptor.addChild(root_1, QUOTED_VALUE30_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:71:9: ^( ANNO_SET_LIST OBJECT_IDENT VALUE_LIST )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    ANNO_SET_LIST31 = (CommonTree) match(input, ANNO_SET_LIST,
                            FOLLOW_ANNO_SET_LIST_in_set_annotation433);
                    ANNO_SET_LIST31_tree = (CommonTree) adaptor
                            .dupNode(ANNO_SET_LIST31);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            ANNO_SET_LIST31_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT32 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_annotation435);
                    OBJECT_IDENT32_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT32);

                    adaptor.addChild(root_1, OBJECT_IDENT32_tree);

                    _last = (CommonTree) input.LT(1);
                    VALUE_LIST33 = (CommonTree) match(input, VALUE_LIST,
                            FOLLOW_VALUE_LIST_in_set_annotation437);
                    VALUE_LIST33_tree = (CommonTree) adaptor
                            .dupNode(VALUE_LIST33);

                    adaptor.addChild(root_1, VALUE_LIST33_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:72:9: ^( ANNO_SET_ID OBJECT_IDENT OBJECT_IDENT )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    ANNO_SET_ID34 = (CommonTree) match(input, ANNO_SET_ID,
                            FOLLOW_ANNO_SET_ID_in_set_annotation449);
                    ANNO_SET_ID34_tree = (CommonTree) adaptor
                            .dupNode(ANNO_SET_ID34);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            ANNO_SET_ID34_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT35 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_annotation451);
                    OBJECT_IDENT35_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT35);

                    adaptor.addChild(root_1, OBJECT_IDENT35_tree);

                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT36 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_set_annotation453);
                    OBJECT_IDENT36_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT36);

                    adaptor.addChild(root_1, OBJECT_IDENT36_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_annotation"

    public static class unset_statement_group_return extends
            TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "unset_statement_group"
    // BELScriptWalker_v1.g:75:1: unset_statement_group : UNSET_SG ;
    public final BELScriptWalker_v1.unset_statement_group_return unset_statement_group()
            throws RecognitionException {
        BELScriptWalker_v1.unset_statement_group_return retval = new BELScriptWalker_v1.unset_statement_group_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree UNSET_SG37 = null;

        CommonTree UNSET_SG37_tree = null;

        try {
            // BELScriptWalker_v1.g:76:5: ( UNSET_SG )
            // BELScriptWalker_v1.g:76:9: UNSET_SG
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                UNSET_SG37 = (CommonTree) match(input, UNSET_SG,
                        FOLLOW_UNSET_SG_in_unset_statement_group473);
                UNSET_SG37_tree = (CommonTree) adaptor.dupNode(UNSET_SG37);

                adaptor.addChild(root_0, UNSET_SG37_tree);

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "unset_statement_group"

    public static class unset_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "unset"
    // BELScriptWalker_v1.g:79:1: unset : ( ^( UNSET_ID OBJECT_IDENT ) | ^( UNSET_ID_LIST IDENT_LIST ) );
    public final BELScriptWalker_v1.unset_return unset()
            throws RecognitionException {
        BELScriptWalker_v1.unset_return retval = new BELScriptWalker_v1.unset_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree UNSET_ID38 = null;
        CommonTree OBJECT_IDENT39 = null;
        CommonTree UNSET_ID_LIST40 = null;
        CommonTree IDENT_LIST41 = null;

        CommonTree UNSET_ID38_tree = null;
        CommonTree OBJECT_IDENT39_tree = null;
        CommonTree UNSET_ID_LIST40_tree = null;
        CommonTree IDENT_LIST41_tree = null;

        try {
            // BELScriptWalker_v1.g:80:5: ( ^( UNSET_ID OBJECT_IDENT ) | ^( UNSET_ID_LIST IDENT_LIST ) )
            int alt8 = 2;
            int LA8_0 = input.LA(1);

            if ((LA8_0 == UNSET_ID)) {
                alt8 = 1;
            }
            else if ((LA8_0 == UNSET_ID_LIST)) {
                alt8 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
            case 1:
            // BELScriptWalker_v1.g:80:9: ^( UNSET_ID OBJECT_IDENT )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    UNSET_ID38 = (CommonTree) match(input, UNSET_ID,
                            FOLLOW_UNSET_ID_in_unset493);
                    UNSET_ID38_tree = (CommonTree) adaptor.dupNode(UNSET_ID38);

                    root_1 = (CommonTree) adaptor.becomeRoot(UNSET_ID38_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT39 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_unset495);
                    OBJECT_IDENT39_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT39);

                    adaptor.addChild(root_1, OBJECT_IDENT39_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:81:9: ^( UNSET_ID_LIST IDENT_LIST )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    UNSET_ID_LIST40 = (CommonTree) match(input, UNSET_ID_LIST,
                            FOLLOW_UNSET_ID_LIST_in_unset507);
                    UNSET_ID_LIST40_tree = (CommonTree) adaptor
                            .dupNode(UNSET_ID_LIST40);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            UNSET_ID_LIST40_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    IDENT_LIST41 = (CommonTree) match(input, IDENT_LIST,
                            FOLLOW_IDENT_LIST_in_unset509);
                    IDENT_LIST41_tree = (CommonTree) adaptor
                            .dupNode(IDENT_LIST41);

                    adaptor.addChild(root_1, IDENT_LIST41_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "unset"

    public static class define_namespace_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_namespace"
    // BELScriptWalker_v1.g:84:1: define_namespace : ( ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE ) | ^( NSDEF OBJECT_IDENT QUOTED_VALUE ) );
    public final BELScriptWalker_v1.define_namespace_return define_namespace()
            throws RecognitionException {
        BELScriptWalker_v1.define_namespace_return retval = new BELScriptWalker_v1.define_namespace_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree DFLT_NSDEF42 = null;
        CommonTree OBJECT_IDENT43 = null;
        CommonTree QUOTED_VALUE44 = null;
        CommonTree NSDEF45 = null;
        CommonTree OBJECT_IDENT46 = null;
        CommonTree QUOTED_VALUE47 = null;

        CommonTree DFLT_NSDEF42_tree = null;
        CommonTree OBJECT_IDENT43_tree = null;
        CommonTree QUOTED_VALUE44_tree = null;
        CommonTree NSDEF45_tree = null;
        CommonTree OBJECT_IDENT46_tree = null;
        CommonTree QUOTED_VALUE47_tree = null;

        try {
            // BELScriptWalker_v1.g:85:5: ( ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE ) | ^( NSDEF OBJECT_IDENT QUOTED_VALUE ) )
            int alt9 = 2;
            int LA9_0 = input.LA(1);

            if ((LA9_0 == DFLT_NSDEF)) {
                alt9 = 1;
            }
            else if ((LA9_0 == NSDEF)) {
                alt9 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
            case 1:
            // BELScriptWalker_v1.g:85:9: ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    DFLT_NSDEF42 = (CommonTree) match(input, DFLT_NSDEF,
                            FOLLOW_DFLT_NSDEF_in_define_namespace530);
                    DFLT_NSDEF42_tree = (CommonTree) adaptor
                            .dupNode(DFLT_NSDEF42);

                    root_1 = (CommonTree) adaptor.becomeRoot(DFLT_NSDEF42_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT43 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_define_namespace532);
                    OBJECT_IDENT43_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT43);

                    adaptor.addChild(root_1, OBJECT_IDENT43_tree);

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE44 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_define_namespace534);
                    QUOTED_VALUE44_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE44);

                    adaptor.addChild(root_1, QUOTED_VALUE44_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:86:9: ^( NSDEF OBJECT_IDENT QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    NSDEF45 = (CommonTree) match(input, NSDEF,
                            FOLLOW_NSDEF_in_define_namespace546);
                    NSDEF45_tree = (CommonTree) adaptor.dupNode(NSDEF45);

                    root_1 = (CommonTree) adaptor.becomeRoot(NSDEF45_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT46 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_define_namespace548);
                    OBJECT_IDENT46_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT46);

                    adaptor.addChild(root_1, OBJECT_IDENT46_tree);

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE47 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_define_namespace550);
                    QUOTED_VALUE47_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE47);

                    adaptor.addChild(root_1, QUOTED_VALUE47_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_namespace"

    public static class define_anno_expr_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_anno_expr"
    // BELScriptWalker_v1.g:89:1: define_anno_expr : KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )* ;
    public final BELScriptWalker_v1.define_anno_expr_return define_anno_expr()
            throws RecognitionException {
        BELScriptWalker_v1.define_anno_expr_return retval = new BELScriptWalker_v1.define_anno_expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree KWRD_DEFINE48 = null;
        CommonTree WS49 = null;
        CommonTree KWRD_ANNO50 = null;
        CommonTree WS51 = null;

        CommonTree KWRD_DEFINE48_tree = null;
        CommonTree WS49_tree = null;
        CommonTree KWRD_ANNO50_tree = null;
        CommonTree WS51_tree = null;

        try {
            // BELScriptWalker_v1.g:90:5: ( KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )* )
            // BELScriptWalker_v1.g:90:9: KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )*
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                KWRD_DEFINE48 = (CommonTree) match(input, KWRD_DEFINE,
                        FOLLOW_KWRD_DEFINE_in_define_anno_expr570);
                KWRD_DEFINE48_tree = (CommonTree) adaptor
                        .dupNode(KWRD_DEFINE48);

                adaptor.addChild(root_0, KWRD_DEFINE48_tree);

                // BELScriptWalker_v1.g:90:21: ( WS )*
                loop10: do {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);

                    if ((LA10_0 == WS)) {
                        alt10 = 1;
                    }

                    switch (alt10) {
                    case 1:
                    // BELScriptWalker_v1.g:90:21: WS
                    {
                        _last = (CommonTree) input.LT(1);
                        WS49 = (CommonTree) match(input, WS,
                                FOLLOW_WS_in_define_anno_expr572);
                        WS49_tree = (CommonTree) adaptor.dupNode(WS49);

                        adaptor.addChild(root_0, WS49_tree);

                    }
                        break;

                    default:
                        break loop10;
                    }
                } while (true);

                _last = (CommonTree) input.LT(1);
                KWRD_ANNO50 = (CommonTree) match(input, KWRD_ANNO,
                        FOLLOW_KWRD_ANNO_in_define_anno_expr575);
                KWRD_ANNO50_tree = (CommonTree) adaptor.dupNode(KWRD_ANNO50);

                adaptor.addChild(root_0, KWRD_ANNO50_tree);

                // BELScriptWalker_v1.g:90:35: ( WS )*
                loop11: do {
                    int alt11 = 2;
                    int LA11_0 = input.LA(1);

                    if ((LA11_0 == WS)) {
                        alt11 = 1;
                    }

                    switch (alt11) {
                    case 1:
                    // BELScriptWalker_v1.g:90:35: WS
                    {
                        _last = (CommonTree) input.LT(1);
                        WS51 = (CommonTree) match(input, WS,
                                FOLLOW_WS_in_define_anno_expr577);
                        WS51_tree = (CommonTree) adaptor.dupNode(WS51);

                        adaptor.addChild(root_0, WS51_tree);

                    }
                        break;

                    default:
                        break loop11;
                    }
                } while (true);

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_anno_expr"

    public static class define_annotation_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_annotation"
    // BELScriptWalker_v1.g:93:1: define_annotation : ( ^( ANNO_DEF_LIST OBJECT_IDENT VALUE_LIST ) | ^( ANNO_DEF_URL OBJECT_IDENT QUOTED_VALUE ) );
    public final BELScriptWalker_v1.define_annotation_return define_annotation()
            throws RecognitionException {
        BELScriptWalker_v1.define_annotation_return retval = new BELScriptWalker_v1.define_annotation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ANNO_DEF_LIST52 = null;
        CommonTree OBJECT_IDENT53 = null;
        CommonTree VALUE_LIST54 = null;
        CommonTree ANNO_DEF_URL55 = null;
        CommonTree OBJECT_IDENT56 = null;
        CommonTree QUOTED_VALUE57 = null;

        CommonTree ANNO_DEF_LIST52_tree = null;
        CommonTree OBJECT_IDENT53_tree = null;
        CommonTree VALUE_LIST54_tree = null;
        CommonTree ANNO_DEF_URL55_tree = null;
        CommonTree OBJECT_IDENT56_tree = null;
        CommonTree QUOTED_VALUE57_tree = null;

        try {
            // BELScriptWalker_v1.g:94:5: ( ^( ANNO_DEF_LIST OBJECT_IDENT VALUE_LIST ) | ^( ANNO_DEF_URL OBJECT_IDENT QUOTED_VALUE ) )
            int alt12 = 2;
            int LA12_0 = input.LA(1);

            if ((LA12_0 == ANNO_DEF_LIST)) {
                alt12 = 1;
            }
            else if ((LA12_0 == ANNO_DEF_URL)) {
                alt12 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
            case 1:
            // BELScriptWalker_v1.g:94:9: ^( ANNO_DEF_LIST OBJECT_IDENT VALUE_LIST )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    ANNO_DEF_LIST52 = (CommonTree) match(input, ANNO_DEF_LIST,
                            FOLLOW_ANNO_DEF_LIST_in_define_annotation598);
                    ANNO_DEF_LIST52_tree = (CommonTree) adaptor
                            .dupNode(ANNO_DEF_LIST52);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            ANNO_DEF_LIST52_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT53 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_define_annotation600);
                    OBJECT_IDENT53_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT53);

                    adaptor.addChild(root_1, OBJECT_IDENT53_tree);

                    _last = (CommonTree) input.LT(1);
                    VALUE_LIST54 = (CommonTree) match(input, VALUE_LIST,
                            FOLLOW_VALUE_LIST_in_define_annotation602);
                    VALUE_LIST54_tree = (CommonTree) adaptor
                            .dupNode(VALUE_LIST54);

                    adaptor.addChild(root_1, VALUE_LIST54_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:95:9: ^( ANNO_DEF_URL OBJECT_IDENT QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    ANNO_DEF_URL55 = (CommonTree) match(input, ANNO_DEF_URL,
                            FOLLOW_ANNO_DEF_URL_in_define_annotation614);
                    ANNO_DEF_URL55_tree = (CommonTree) adaptor
                            .dupNode(ANNO_DEF_URL55);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            ANNO_DEF_URL55_tree, root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT56 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_define_annotation616);
                    OBJECT_IDENT56_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT56);

                    adaptor.addChild(root_1, OBJECT_IDENT56_tree);

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE57 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_define_annotation618);
                    QUOTED_VALUE57_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE57);

                    adaptor.addChild(root_1, QUOTED_VALUE57_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_annotation"

    public static class document_property_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "document_property"
    // BELScriptWalker_v1.g:98:1: document_property : ( KWRD_AUTHORS -> AUTHDEF | KWRD_CONTACTINFO -> CONTACTDEF | KWRD_COPYRIGHT -> COPYDEF | KWRD_DESC -> DESCDEF | KWRD_DISCLAIMER -> DISCDEF | KWRD_LICENSES -> LICDEF | KWRD_NAME -> NAMEDEF | KWRD_VERSION -> VERSIONDEF );
    public final BELScriptWalker_v1.document_property_return document_property()
            throws RecognitionException {
        BELScriptWalker_v1.document_property_return retval = new BELScriptWalker_v1.document_property_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree KWRD_AUTHORS58 = null;
        CommonTree KWRD_CONTACTINFO59 = null;
        CommonTree KWRD_COPYRIGHT60 = null;
        CommonTree KWRD_DESC61 = null;
        CommonTree KWRD_DISCLAIMER62 = null;
        CommonTree KWRD_LICENSES63 = null;
        CommonTree KWRD_NAME64 = null;
        CommonTree KWRD_VERSION65 = null;

        CommonTree KWRD_AUTHORS58_tree = null;
        CommonTree KWRD_CONTACTINFO59_tree = null;
        CommonTree KWRD_COPYRIGHT60_tree = null;
        CommonTree KWRD_DESC61_tree = null;
        CommonTree KWRD_DISCLAIMER62_tree = null;
        CommonTree KWRD_LICENSES63_tree = null;
        CommonTree KWRD_NAME64_tree = null;
        CommonTree KWRD_VERSION65_tree = null;
        RewriteRuleNodeStream stream_KWRD_DESC = new RewriteRuleNodeStream(
                adaptor, "token KWRD_DESC");
        RewriteRuleNodeStream stream_KWRD_NAME = new RewriteRuleNodeStream(
                adaptor, "token KWRD_NAME");
        RewriteRuleNodeStream stream_KWRD_LICENSES = new RewriteRuleNodeStream(
                adaptor, "token KWRD_LICENSES");
        RewriteRuleNodeStream stream_KWRD_COPYRIGHT = new RewriteRuleNodeStream(
                adaptor, "token KWRD_COPYRIGHT");
        RewriteRuleNodeStream stream_KWRD_DISCLAIMER = new RewriteRuleNodeStream(
                adaptor, "token KWRD_DISCLAIMER");
        RewriteRuleNodeStream stream_KWRD_CONTACTINFO = new RewriteRuleNodeStream(
                adaptor, "token KWRD_CONTACTINFO");
        RewriteRuleNodeStream stream_KWRD_VERSION = new RewriteRuleNodeStream(
                adaptor, "token KWRD_VERSION");
        RewriteRuleNodeStream stream_KWRD_AUTHORS = new RewriteRuleNodeStream(
                adaptor, "token KWRD_AUTHORS");

        try {
            // BELScriptWalker_v1.g:99:5: ( KWRD_AUTHORS -> AUTHDEF | KWRD_CONTACTINFO -> CONTACTDEF | KWRD_COPYRIGHT -> COPYDEF | KWRD_DESC -> DESCDEF | KWRD_DISCLAIMER -> DISCDEF | KWRD_LICENSES -> LICDEF | KWRD_NAME -> NAMEDEF | KWRD_VERSION -> VERSIONDEF )
            int alt13 = 8;
            switch (input.LA(1)) {
            case KWRD_AUTHORS: {
                alt13 = 1;
            }
                break;
            case KWRD_CONTACTINFO: {
                alt13 = 2;
            }
                break;
            case KWRD_COPYRIGHT: {
                alt13 = 3;
            }
                break;
            case KWRD_DESC: {
                alt13 = 4;
            }
                break;
            case KWRD_DISCLAIMER: {
                alt13 = 5;
            }
                break;
            case KWRD_LICENSES: {
                alt13 = 6;
            }
                break;
            case KWRD_NAME: {
                alt13 = 7;
            }
                break;
            case KWRD_VERSION: {
                alt13 = 8;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
            case 1:
            // BELScriptWalker_v1.g:99:9: KWRD_AUTHORS
            {
                _last = (CommonTree) input.LT(1);
                KWRD_AUTHORS58 = (CommonTree) match(input, KWRD_AUTHORS,
                        FOLLOW_KWRD_AUTHORS_in_document_property638);
                stream_KWRD_AUTHORS.add(KWRD_AUTHORS58);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 99:22: -> AUTHDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(AUTHDEF, "AUTHDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:100:9: KWRD_CONTACTINFO
            {
                _last = (CommonTree) input.LT(1);
                KWRD_CONTACTINFO59 = (CommonTree) match(input,
                        KWRD_CONTACTINFO,
                        FOLLOW_KWRD_CONTACTINFO_in_document_property652);
                stream_KWRD_CONTACTINFO.add(KWRD_CONTACTINFO59);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 100:26: -> CONTACTDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(CONTACTDEF,
                                    "CONTACTDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:101:9: KWRD_COPYRIGHT
            {
                _last = (CommonTree) input.LT(1);
                KWRD_COPYRIGHT60 = (CommonTree) match(input, KWRD_COPYRIGHT,
                        FOLLOW_KWRD_COPYRIGHT_in_document_property666);
                stream_KWRD_COPYRIGHT.add(KWRD_COPYRIGHT60);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 101:24: -> COPYDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(COPYDEF, "COPYDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 4:
            // BELScriptWalker_v1.g:102:9: KWRD_DESC
            {
                _last = (CommonTree) input.LT(1);
                KWRD_DESC61 = (CommonTree) match(input, KWRD_DESC,
                        FOLLOW_KWRD_DESC_in_document_property680);
                stream_KWRD_DESC.add(KWRD_DESC61);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 102:19: -> DESCDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(DESCDEF, "DESCDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 5:
            // BELScriptWalker_v1.g:103:9: KWRD_DISCLAIMER
            {
                _last = (CommonTree) input.LT(1);
                KWRD_DISCLAIMER62 = (CommonTree) match(input, KWRD_DISCLAIMER,
                        FOLLOW_KWRD_DISCLAIMER_in_document_property694);
                stream_KWRD_DISCLAIMER.add(KWRD_DISCLAIMER62);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 103:25: -> DISCDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(DISCDEF, "DISCDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 6:
            // BELScriptWalker_v1.g:104:9: KWRD_LICENSES
            {
                _last = (CommonTree) input.LT(1);
                KWRD_LICENSES63 = (CommonTree) match(input, KWRD_LICENSES,
                        FOLLOW_KWRD_LICENSES_in_document_property708);
                stream_KWRD_LICENSES.add(KWRD_LICENSES63);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 104:23: -> LICDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(LICDEF, "LICDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 7:
            // BELScriptWalker_v1.g:105:9: KWRD_NAME
            {
                _last = (CommonTree) input.LT(1);
                KWRD_NAME64 = (CommonTree) match(input, KWRD_NAME,
                        FOLLOW_KWRD_NAME_in_document_property722);
                stream_KWRD_NAME.add(KWRD_NAME64);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 105:19: -> NAMEDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(NAMEDEF, "NAMEDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;
            case 8:
            // BELScriptWalker_v1.g:106:9: KWRD_VERSION
            {
                _last = (CommonTree) input.LT(1);
                KWRD_VERSION65 = (CommonTree) match(input, KWRD_VERSION,
                        FOLLOW_KWRD_VERSION_in_document_property736);
                stream_KWRD_VERSION.add(KWRD_VERSION65);

                // AST REWRITE
                // elements: 
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (CommonTree) adaptor.nil();
                // 106:22: -> VERSIONDEF
                {
                    adaptor.addChild(root_0,
                            (CommonTree) adaptor.create(VERSIONDEF,
                                    "VERSIONDEF")
                            );

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "document_property"

    public static class argument_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "argument"
    // BELScriptWalker_v1.g:109:1: argument : ( term | param );
    public final BELScriptWalker_v1.argument_return argument()
            throws RecognitionException {
        BELScriptWalker_v1.argument_return retval = new BELScriptWalker_v1.argument_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        BELScriptWalker_v1.term_return term66 = null;

        BELScriptWalker_v1.param_return param67 = null;

        try {
            // BELScriptWalker_v1.g:110:5: ( term | param )
            int alt14 = 2;
            int LA14_0 = input.LA(1);

            if ((LA14_0 == TERMDEF)) {
                alt14 = 1;
            }
            else if (((LA14_0 >= PARAM_DEF_ID && LA14_0 <= PARAM_DEF_QV))) {
                alt14 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
            case 1:
            // BELScriptWalker_v1.g:110:9: term
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_term_in_argument759);
                term66 = term();

                state._fsp--;

                adaptor.addChild(root_0, term66.getTree());

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:111:9: param
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                pushFollow(FOLLOW_param_in_argument769);
                param67 = param();

                state._fsp--;

                adaptor.addChild(root_0, param67.getTree());

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "argument"

    public static class term_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "term"
    // BELScriptWalker_v1.g:114:1: term : ^( TERMDEF function ( argument )* ) ;
    public final BELScriptWalker_v1.term_return term()
            throws RecognitionException {
        BELScriptWalker_v1.term_return retval = new BELScriptWalker_v1.term_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree TERMDEF68 = null;
        BELScriptWalker_v1.function_return function69 = null;

        BELScriptWalker_v1.argument_return argument70 = null;

        CommonTree TERMDEF68_tree = null;

        try {
            // BELScriptWalker_v1.g:115:5: ( ^( TERMDEF function ( argument )* ) )
            // BELScriptWalker_v1.g:115:9: ^( TERMDEF function ( argument )* )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    TERMDEF68 = (CommonTree) match(input, TERMDEF,
                            FOLLOW_TERMDEF_in_term789);
                    TERMDEF68_tree = (CommonTree) adaptor.dupNode(TERMDEF68);

                    root_1 = (CommonTree) adaptor.becomeRoot(TERMDEF68_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    _last = (CommonTree) input.LT(1);
                    pushFollow(FOLLOW_function_in_term791);
                    function69 = function();

                    state._fsp--;

                    adaptor.addChild(root_1, function69.getTree());

                    // BELScriptWalker_v1.g:115:28: ( argument )*
                    loop15: do {
                        int alt15 = 2;
                        int LA15_0 = input.LA(1);

                        if (((LA15_0 >= PARAM_DEF_ID && LA15_0 <= PARAM_DEF_QV) || LA15_0 == TERMDEF)) {
                            alt15 = 1;
                        }

                        switch (alt15) {
                        case 1:
                        // BELScriptWalker_v1.g:115:28: argument
                        {
                            _last = (CommonTree) input.LT(1);
                            pushFollow(FOLLOW_argument_in_term793);
                            argument70 = argument();

                            state._fsp--;

                            adaptor.addChild(root_1, argument70.getTree());

                        }
                            break;

                        default:
                            break loop15;
                        }
                    } while (true);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "term"

    public static class statement_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "statement"
    // BELScriptWalker_v1.g:118:1: statement : ^( STMTDEF ( STATEMENT_COMMENT )? term ( relationship term ( relationship term )? )? ) ;
    public final BELScriptWalker_v1.statement_return statement()
            throws RecognitionException {
        BELScriptWalker_v1.statement_return retval = new BELScriptWalker_v1.statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STMTDEF71 = null;
        CommonTree STATEMENT_COMMENT72 = null;
        BELScriptWalker_v1.term_return term73 = null;

        BELScriptWalker_v1.relationship_return relationship74 = null;

        BELScriptWalker_v1.term_return term75 = null;

        BELScriptWalker_v1.relationship_return relationship76 = null;

        BELScriptWalker_v1.term_return term77 = null;

        CommonTree STMTDEF71_tree = null;
        CommonTree STATEMENT_COMMENT72_tree = null;

        try {
            // BELScriptWalker_v1.g:119:5: ( ^( STMTDEF ( STATEMENT_COMMENT )? term ( relationship term ( relationship term )? )? ) )
            // BELScriptWalker_v1.g:119:9: ^( STMTDEF ( STATEMENT_COMMENT )? term ( relationship term ( relationship term )? )? )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    STMTDEF71 = (CommonTree) match(input, STMTDEF,
                            FOLLOW_STMTDEF_in_statement815);
                    STMTDEF71_tree = (CommonTree) adaptor.dupNode(STMTDEF71);

                    root_1 = (CommonTree) adaptor.becomeRoot(STMTDEF71_tree,
                            root_1);

                    match(input, Token.DOWN, null);
                    // BELScriptWalker_v1.g:119:19: ( STATEMENT_COMMENT )?
                    int alt16 = 2;
                    int LA16_0 = input.LA(1);

                    if ((LA16_0 == STATEMENT_COMMENT)) {
                        alt16 = 1;
                    }
                    switch (alt16) {
                    case 1:
                    // BELScriptWalker_v1.g:119:19: STATEMENT_COMMENT
                    {
                        _last = (CommonTree) input.LT(1);
                        STATEMENT_COMMENT72 = (CommonTree) match(input,
                                STATEMENT_COMMENT,
                                FOLLOW_STATEMENT_COMMENT_in_statement817);
                        STATEMENT_COMMENT72_tree = (CommonTree) adaptor
                                .dupNode(STATEMENT_COMMENT72);

                        adaptor.addChild(root_1, STATEMENT_COMMENT72_tree);

                    }
                        break;

                    }

                    _last = (CommonTree) input.LT(1);
                    pushFollow(FOLLOW_term_in_statement820);
                    term73 = term();

                    state._fsp--;

                    adaptor.addChild(root_1, term73.getTree());

                    // BELScriptWalker_v1.g:119:43: ( relationship term ( relationship term )? )?
                    int alt18 = 2;
                    int LA18_0 = input.LA(1);

                    if (((LA18_0 >= 64 && LA18_0 <= 70)
                            || (LA18_0 >= 74 && LA18_0 <= 75) || LA18_0 == 77
                            || LA18_0 == 81 || LA18_0 == 90
                            || (LA18_0 >= 93 && LA18_0 <= 94)
                            || (LA18_0 >= 101 && LA18_0 <= 106)
                            || (LA18_0 >= 113 && LA18_0 <= 114)
                            || LA18_0 == 123 || LA18_0 == 125 || LA18_0 == 129
                            || LA18_0 == 138 || LA18_0 == 143 || LA18_0 == 145)) {
                        alt18 = 1;
                    }
                    switch (alt18) {
                    case 1:
                    // BELScriptWalker_v1.g:119:44: relationship term ( relationship term )?
                    {
                        _last = (CommonTree) input.LT(1);
                        pushFollow(FOLLOW_relationship_in_statement823);
                        relationship74 = relationship();

                        state._fsp--;

                        adaptor.addChild(root_1, relationship74.getTree());

                        _last = (CommonTree) input.LT(1);
                        pushFollow(FOLLOW_term_in_statement825);
                        term75 = term();

                        state._fsp--;

                        adaptor.addChild(root_1, term75.getTree());

                        // BELScriptWalker_v1.g:119:62: ( relationship term )?
                        int alt17 = 2;
                        int LA17_0 = input.LA(1);

                        if (((LA17_0 >= 64 && LA17_0 <= 70)
                                || (LA17_0 >= 74 && LA17_0 <= 75)
                                || LA17_0 == 77 || LA17_0 == 81 || LA17_0 == 90
                                || (LA17_0 >= 93 && LA17_0 <= 94)
                                || (LA17_0 >= 101 && LA17_0 <= 106)
                                || (LA17_0 >= 113 && LA17_0 <= 114)
                                || LA17_0 == 123 || LA17_0 == 125
                                || LA17_0 == 129 || LA17_0 == 138
                                || LA17_0 == 143 || LA17_0 == 145)) {
                            alt17 = 1;
                        }
                        switch (alt17) {
                        case 1:
                        // BELScriptWalker_v1.g:119:63: relationship term
                        {
                            _last = (CommonTree) input.LT(1);
                            pushFollow(FOLLOW_relationship_in_statement828);
                            relationship76 = relationship();

                            state._fsp--;

                            adaptor.addChild(root_1, relationship76.getTree());

                            _last = (CommonTree) input.LT(1);
                            pushFollow(FOLLOW_term_in_statement830);
                            term77 = term();

                            state._fsp--;

                            adaptor.addChild(root_1, term77.getTree());

                        }
                            break;

                        }

                    }
                        break;

                    }

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "statement"

    public static class ns_prefix_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "ns_prefix"
    // BELScriptWalker_v1.g:122:1: ns_prefix : OBJECT_IDENT ;
    public final BELScriptWalker_v1.ns_prefix_return ns_prefix()
            throws RecognitionException {
        BELScriptWalker_v1.ns_prefix_return retval = new BELScriptWalker_v1.ns_prefix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree OBJECT_IDENT78 = null;

        CommonTree OBJECT_IDENT78_tree = null;

        try {
            // BELScriptWalker_v1.g:123:5: ( OBJECT_IDENT )
            // BELScriptWalker_v1.g:123:9: OBJECT_IDENT
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                OBJECT_IDENT78 = (CommonTree) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_ns_prefix854);
                OBJECT_IDENT78_tree = (CommonTree) adaptor
                        .dupNode(OBJECT_IDENT78);

                adaptor.addChild(root_0, OBJECT_IDENT78_tree);

            }

            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "ns_prefix"

    public static class param_return extends TreeRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "param"
    // BELScriptWalker_v1.g:126:1: param : ( ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT ) | ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE ) );
    public final BELScriptWalker_v1.param_return param()
            throws RecognitionException {
        BELScriptWalker_v1.param_return retval = new BELScriptWalker_v1.param_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree PARAM_DEF_ID79 = null;
        CommonTree OBJECT_IDENT81 = null;
        CommonTree PARAM_DEF_QV82 = null;
        CommonTree QUOTED_VALUE84 = null;
        BELScriptWalker_v1.ns_prefix_return ns_prefix80 = null;

        BELScriptWalker_v1.ns_prefix_return ns_prefix83 = null;

        CommonTree PARAM_DEF_ID79_tree = null;
        CommonTree OBJECT_IDENT81_tree = null;
        CommonTree PARAM_DEF_QV82_tree = null;
        CommonTree QUOTED_VALUE84_tree = null;

        try {
            // BELScriptWalker_v1.g:127:5: ( ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT ) | ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE ) )
            int alt21 = 2;
            int LA21_0 = input.LA(1);

            if ((LA21_0 == PARAM_DEF_ID)) {
                alt21 = 1;
            }
            else if ((LA21_0 == PARAM_DEF_QV)) {
                alt21 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
            case 1:
            // BELScriptWalker_v1.g:127:9: ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    PARAM_DEF_ID79 = (CommonTree) match(input, PARAM_DEF_ID,
                            FOLLOW_PARAM_DEF_ID_in_param874);
                    PARAM_DEF_ID79_tree = (CommonTree) adaptor
                            .dupNode(PARAM_DEF_ID79);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            PARAM_DEF_ID79_tree, root_1);

                    match(input, Token.DOWN, null);
                    // BELScriptWalker_v1.g:127:24: ( ns_prefix )?
                    int alt19 = 2;
                    int LA19_0 = input.LA(1);

                    if ((LA19_0 == OBJECT_IDENT)) {
                        int LA19_1 = input.LA(2);

                        if ((LA19_1 == OBJECT_IDENT)) {
                            alt19 = 1;
                        }
                    }
                    switch (alt19) {
                    case 1:
                    // BELScriptWalker_v1.g:127:24: ns_prefix
                    {
                        _last = (CommonTree) input.LT(1);
                        pushFollow(FOLLOW_ns_prefix_in_param876);
                        ns_prefix80 = ns_prefix();

                        state._fsp--;

                        adaptor.addChild(root_1, ns_prefix80.getTree());

                    }
                        break;

                    }

                    _last = (CommonTree) input.LT(1);
                    OBJECT_IDENT81 = (CommonTree) match(input, OBJECT_IDENT,
                            FOLLOW_OBJECT_IDENT_in_param879);
                    OBJECT_IDENT81_tree = (CommonTree) adaptor
                            .dupNode(OBJECT_IDENT81);

                    adaptor.addChild(root_1, OBJECT_IDENT81_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:128:9: ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE )
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree) adaptor.nil();
                    _last = (CommonTree) input.LT(1);
                    PARAM_DEF_QV82 = (CommonTree) match(input, PARAM_DEF_QV,
                            FOLLOW_PARAM_DEF_QV_in_param891);
                    PARAM_DEF_QV82_tree = (CommonTree) adaptor
                            .dupNode(PARAM_DEF_QV82);

                    root_1 = (CommonTree) adaptor.becomeRoot(
                            PARAM_DEF_QV82_tree, root_1);

                    match(input, Token.DOWN, null);
                    // BELScriptWalker_v1.g:128:24: ( ns_prefix )?
                    int alt20 = 2;
                    int LA20_0 = input.LA(1);

                    if ((LA20_0 == OBJECT_IDENT)) {
                        alt20 = 1;
                    }
                    switch (alt20) {
                    case 1:
                    // BELScriptWalker_v1.g:128:24: ns_prefix
                    {
                        _last = (CommonTree) input.LT(1);
                        pushFollow(FOLLOW_ns_prefix_in_param893);
                        ns_prefix83 = ns_prefix();

                        state._fsp--;

                        adaptor.addChild(root_1, ns_prefix83.getTree());

                    }
                        break;

                    }

                    _last = (CommonTree) input.LT(1);
                    QUOTED_VALUE84 = (CommonTree) match(input, QUOTED_VALUE,
                            FOLLOW_QUOTED_VALUE_in_param896);
                    QUOTED_VALUE84_tree = (CommonTree) adaptor
                            .dupNode(QUOTED_VALUE84);

                    adaptor.addChild(root_1, QUOTED_VALUE84_tree);

                    match(input, Token.UP, null);
                    adaptor.addChild(root_0, root_1);
                    _last = _save_last_1;
                }

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "param"

    public static class function_return extends TreeRuleReturnScope {
        public String r;
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "function"
    // BELScriptWalker_v1.g:131:1: function returns [String r] : (fv= 'proteinAbundance' |fv= 'p' |fv= 'rnaAbundance' |fv= 'r' |fv= 'abundance' |fv= 'a' |fv= 'microRNAAbundance' |fv= 'm' |fv= 'geneAbundance' |fv= 'g' |fv= 'biologicalProcess' |fv= 'bp' |fv= 'pathology' |fv= 'path' |fv= 'complexAbundance' |fv= 'complex' |fv= 'translocation' |fv= 'tloc' |fv= 'cellSecretion' |fv= 'sec' |fv= 'cellSurfaceExpression' |fv= 'surf' |fv= 'reaction' |fv= 'rxn' |fv= 'compositeAbundance' |fv= 'composite' |fv= 'fusion' |fv= 'fus' |fv= 'degradation' |fv= 'deg' |fv= 'molecularActivity' |fv= 'act' |fv= 'catalyticActivity' |fv= 'cat' |fv= 'kinaseActivity' |fv= 'kin' |fv= 'phosphataseActivity' |fv= 'phos' |fv= 'peptidaseActivity' |fv= 'pep' |fv= 'ribosylationActivity' |fv= 'ribo' |fv= 'transcriptionalActivity' |fv= 'tscript' |fv= 'transportActivity' |fv= 'tport' |fv= 'gtpBoundActivity' |fv= 'gtp' |fv= 'chaperoneActivity' |fv= 'chap' |fv= 'proteinModification' |fv= 'pmod' |fv= 'substitution' |fv= 'sub' |fv= 'truncation' |fv= 'trunc' |fv= 'reactants' |fv= 'products' |fv= 'list' );
    public final BELScriptWalker_v1.function_return function()
            throws RecognitionException {
        BELScriptWalker_v1.function_return retval = new BELScriptWalker_v1.function_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree fv = null;

        CommonTree fv_tree = null;

        try {
            // BELScriptWalker_v1.g:132:5: (fv= 'proteinAbundance' |fv= 'p' |fv= 'rnaAbundance' |fv= 'r' |fv= 'abundance' |fv= 'a' |fv= 'microRNAAbundance' |fv= 'm' |fv= 'geneAbundance' |fv= 'g' |fv= 'biologicalProcess' |fv= 'bp' |fv= 'pathology' |fv= 'path' |fv= 'complexAbundance' |fv= 'complex' |fv= 'translocation' |fv= 'tloc' |fv= 'cellSecretion' |fv= 'sec' |fv= 'cellSurfaceExpression' |fv= 'surf' |fv= 'reaction' |fv= 'rxn' |fv= 'compositeAbundance' |fv= 'composite' |fv= 'fusion' |fv= 'fus' |fv= 'degradation' |fv= 'deg' |fv= 'molecularActivity' |fv= 'act' |fv= 'catalyticActivity' |fv= 'cat' |fv= 'kinaseActivity' |fv= 'kin' |fv= 'phosphataseActivity' |fv= 'phos' |fv= 'peptidaseActivity' |fv= 'pep' |fv= 'ribosylationActivity' |fv= 'ribo' |fv= 'transcriptionalActivity' |fv= 'tscript' |fv= 'transportActivity' |fv= 'tport' |fv= 'gtpBoundActivity' |fv= 'gtp' |fv= 'chaperoneActivity' |fv= 'chap' |fv= 'proteinModification' |fv= 'pmod' |fv= 'substitution' |fv= 'sub' |fv= 'truncation' |fv= 'trunc' |fv= 'reactants' |fv= 'products' |fv= 'list' )
            int alt22 = 59;
            switch (input.LA(1)) {
            case 126: {
                alt22 = 1;
            }
                break;
            case 115: {
                alt22 = 2;
            }
                break;
            case 134: {
                alt22 = 3;
            }
                break;
            case 128: {
                alt22 = 4;
            }
                break;
            case 72: {
                alt22 = 5;
            }
                break;
            case 71: {
                alt22 = 6;
            }
                break;
            case 111: {
                alt22 = 7;
            }
                break;
            case 110: {
                alt22 = 8;
            }
                break;
            case 98: {
                alt22 = 9;
            }
                break;
            case 97: {
                alt22 = 10;
            }
                break;
            case 76: {
                alt22 = 11;
            }
                break;
            case 78: {
                alt22 = 12;
            }
                break;
            case 117: {
                alt22 = 13;
            }
                break;
            case 116: {
                alt22 = 14;
            }
                break;
            case 87: {
                alt22 = 15;
            }
                break;
            case 86: {
                alt22 = 16;
            }
                break;
            case 146: {
                alt22 = 17;
            }
                break;
            case 141: {
                alt22 = 18;
            }
                break;
            case 82: {
                alt22 = 19;
            }
                break;
            case 136: {
                alt22 = 20;
            }
                break;
            case 83: {
                alt22 = 21;
            }
                break;
            case 140: {
                alt22 = 22;
            }
                break;
            case 131: {
                alt22 = 23;
            }
                break;
            case 135: {
                alt22 = 24;
            }
                break;
            case 89: {
                alt22 = 25;
            }
                break;
            case 88: {
                alt22 = 26;
            }
                break;
            case 96: {
                alt22 = 27;
            }
                break;
            case 95: {
                alt22 = 28;
            }
                break;
            case 92: {
                alt22 = 29;
            }
                break;
            case 91: {
                alt22 = 30;
            }
                break;
            case 112: {
                alt22 = 31;
            }
                break;
            case 73: {
                alt22 = 32;
            }
                break;
            case 80: {
                alt22 = 33;
            }
                break;
            case 79: {
                alt22 = 34;
            }
                break;
            case 108: {
                alt22 = 35;
            }
                break;
            case 107: {
                alt22 = 36;
            }
                break;
            case 121: {
                alt22 = 37;
            }
                break;
            case 120: {
                alt22 = 38;
            }
                break;
            case 119: {
                alt22 = 39;
            }
                break;
            case 118: {
                alt22 = 40;
            }
                break;
            case 133: {
                alt22 = 41;
            }
                break;
            case 132: {
                alt22 = 42;
            }
                break;
            case 144: {
                alt22 = 43;
            }
                break;
            case 150: {
                alt22 = 44;
            }
                break;
            case 147: {
                alt22 = 45;
            }
                break;
            case 142: {
                alt22 = 46;
            }
                break;
            case 100: {
                alt22 = 47;
            }
                break;
            case 99: {
                alt22 = 48;
            }
                break;
            case 85: {
                alt22 = 49;
            }
                break;
            case 84: {
                alt22 = 50;
            }
                break;
            case 127: {
                alt22 = 51;
            }
                break;
            case 122: {
                alt22 = 52;
            }
                break;
            case 139: {
                alt22 = 53;
            }
                break;
            case 137: {
                alt22 = 54;
            }
                break;
            case 149: {
                alt22 = 55;
            }
                break;
            case 148: {
                alt22 = 56;
            }
                break;
            case 130: {
                alt22 = 57;
            }
                break;
            case 124: {
                alt22 = 58;
            }
                break;
            case 109: {
                alt22 = 59;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
            case 1:
            // BELScriptWalker_v1.g:132:9: fv= 'proteinAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 126, FOLLOW_126_in_function922);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "p";

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:133:9: fv= 'p'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 115, FOLLOW_115_in_function946);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "p";

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:134:9: fv= 'rnaAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 134, FOLLOW_134_in_function985);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "r";

            }
                break;
            case 4:
            // BELScriptWalker_v1.g:135:9: fv= 'r'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 128, FOLLOW_128_in_function1013);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "r";

            }
                break;
            case 5:
            // BELScriptWalker_v1.g:136:9: fv= 'abundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 72, FOLLOW_72_in_function1052);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "a";

            }
                break;
            case 6:
            // BELScriptWalker_v1.g:137:9: fv= 'a'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 71, FOLLOW_71_in_function1083);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "a";

            }
                break;
            case 7:
            // BELScriptWalker_v1.g:138:9: fv= 'microRNAAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 111, FOLLOW_111_in_function1122);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "m";

            }
                break;
            case 8:
            // BELScriptWalker_v1.g:139:9: fv= 'm'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 110, FOLLOW_110_in_function1145);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "m";

            }
                break;
            case 9:
            // BELScriptWalker_v1.g:140:9: fv= 'geneAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 98, FOLLOW_98_in_function1184);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "g";

            }
                break;
            case 10:
            // BELScriptWalker_v1.g:141:9: fv= 'g'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 97, FOLLOW_97_in_function1211);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "g";

            }
                break;
            case 11:
            // BELScriptWalker_v1.g:142:9: fv= 'biologicalProcess'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 76, FOLLOW_76_in_function1250);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "bp";

            }
                break;
            case 12:
            // BELScriptWalker_v1.g:143:9: fv= 'bp'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 78, FOLLOW_78_in_function1273);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "bp";

            }
                break;
            case 13:
            // BELScriptWalker_v1.g:144:9: fv= 'pathology'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 117, FOLLOW_117_in_function1311);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "path";

            }
                break;
            case 14:
            // BELScriptWalker_v1.g:145:9: fv= 'path'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 116, FOLLOW_116_in_function1342);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "path";

            }
                break;
            case 15:
            // BELScriptWalker_v1.g:146:9: fv= 'complexAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 87, FOLLOW_87_in_function1378);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "complex";

            }
                break;
            case 16:
            // BELScriptWalker_v1.g:147:9: fv= 'complex'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 86, FOLLOW_86_in_function1402);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "complex";

            }
                break;
            case 17:
            // BELScriptWalker_v1.g:148:9: fv= 'translocation'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 146, FOLLOW_146_in_function1435);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tloc";

            }
                break;
            case 18:
            // BELScriptWalker_v1.g:149:9: fv= 'tloc'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 141, FOLLOW_141_in_function1462);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tloc";

            }
                break;
            case 19:
            // BELScriptWalker_v1.g:150:9: fv= 'cellSecretion'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 82, FOLLOW_82_in_function1498);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "sec";

            }
                break;
            case 20:
            // BELScriptWalker_v1.g:151:9: fv= 'sec'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 136, FOLLOW_136_in_function1525);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "sec";

            }
                break;
            case 21:
            // BELScriptWalker_v1.g:152:9: fv= 'cellSurfaceExpression'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 83, FOLLOW_83_in_function1562);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "surf";

            }
                break;
            case 22:
            // BELScriptWalker_v1.g:153:9: fv= 'surf'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 140, FOLLOW_140_in_function1581);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "surf";

            }
                break;
            case 23:
            // BELScriptWalker_v1.g:154:9: fv= 'reaction'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 131, FOLLOW_131_in_function1617);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "rxn";

            }
                break;
            case 24:
            // BELScriptWalker_v1.g:155:9: fv= 'rxn'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 135, FOLLOW_135_in_function1649);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "rxn";

            }
                break;
            case 25:
            // BELScriptWalker_v1.g:156:9: fv= 'compositeAbundance'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 89, FOLLOW_89_in_function1686);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "composite";

            }
                break;
            case 26:
            // BELScriptWalker_v1.g:157:9: fv= 'composite'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 88, FOLLOW_88_in_function1708);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "composite";

            }
                break;
            case 27:
            // BELScriptWalker_v1.g:158:9: fv= 'fusion'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 96, FOLLOW_96_in_function1739);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "fus";

            }
                break;
            case 28:
            // BELScriptWalker_v1.g:159:9: fv= 'fus'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 95, FOLLOW_95_in_function1773);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "fus";

            }
                break;
            case 29:
            // BELScriptWalker_v1.g:160:9: fv= 'degradation'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 92, FOLLOW_92_in_function1810);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "deg";

            }
                break;
            case 30:
            // BELScriptWalker_v1.g:161:9: fv= 'deg'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 91, FOLLOW_91_in_function1839);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "deg";

            }
                break;
            case 31:
            // BELScriptWalker_v1.g:162:9: fv= 'molecularActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 112, FOLLOW_112_in_function1876);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "act";

            }
                break;
            case 32:
            // BELScriptWalker_v1.g:163:9: fv= 'act'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 73, FOLLOW_73_in_function1899);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "act";

            }
                break;
            case 33:
            // BELScriptWalker_v1.g:164:9: fv= 'catalyticActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 80, FOLLOW_80_in_function1936);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "cat";

            }
                break;
            case 34:
            // BELScriptWalker_v1.g:165:9: fv= 'cat'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 79, FOLLOW_79_in_function1959);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "cat";

            }
                break;
            case 35:
            // BELScriptWalker_v1.g:166:9: fv= 'kinaseActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 108, FOLLOW_108_in_function1996);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "kin";

            }
                break;
            case 36:
            // BELScriptWalker_v1.g:167:9: fv= 'kin'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 107, FOLLOW_107_in_function2022);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "kin";

            }
                break;
            case 37:
            // BELScriptWalker_v1.g:168:9: fv= 'phosphataseActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 121, FOLLOW_121_in_function2059);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "phos";

            }
                break;
            case 38:
            // BELScriptWalker_v1.g:169:9: fv= 'phos'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 120, FOLLOW_120_in_function2080);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "phos";

            }
                break;
            case 39:
            // BELScriptWalker_v1.g:170:9: fv= 'peptidaseActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 119, FOLLOW_119_in_function2116);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "pep";

            }
                break;
            case 40:
            // BELScriptWalker_v1.g:171:9: fv= 'pep'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 118, FOLLOW_118_in_function2139);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "pep";

            }
                break;
            case 41:
            // BELScriptWalker_v1.g:172:9: fv= 'ribosylationActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 133, FOLLOW_133_in_function2176);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "ribo";

            }
                break;
            case 42:
            // BELScriptWalker_v1.g:173:9: fv= 'ribo'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 132, FOLLOW_132_in_function2196);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "ribo";

            }
                break;
            case 43:
            // BELScriptWalker_v1.g:174:9: fv= 'transcriptionalActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 144, FOLLOW_144_in_function2232);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tscript";

            }
                break;
            case 44:
            // BELScriptWalker_v1.g:175:9: fv= 'tscript'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 150, FOLLOW_150_in_function2249);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tscript";

            }
                break;
            case 45:
            // BELScriptWalker_v1.g:176:9: fv= 'transportActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 147, FOLLOW_147_in_function2282);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tport";

            }
                break;
            case 46:
            // BELScriptWalker_v1.g:177:9: fv= 'tport'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 142, FOLLOW_142_in_function2305);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "tport";

            }
                break;
            case 47:
            // BELScriptWalker_v1.g:178:9: fv= 'gtpBoundActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 100, FOLLOW_100_in_function2340);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "gtp";

            }
                break;
            case 48:
            // BELScriptWalker_v1.g:179:9: fv= 'gtp'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 99, FOLLOW_99_in_function2364);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "gtp";

            }
                break;
            case 49:
            // BELScriptWalker_v1.g:180:9: fv= 'chaperoneActivity'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 85, FOLLOW_85_in_function2401);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "chap";

            }
                break;
            case 50:
            // BELScriptWalker_v1.g:181:9: fv= 'chap'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 84, FOLLOW_84_in_function2424);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "chap";

            }
                break;
            case 51:
            // BELScriptWalker_v1.g:182:9: fv= 'proteinModification'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 127, FOLLOW_127_in_function2460);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "pmod";

            }
                break;
            case 52:
            // BELScriptWalker_v1.g:183:9: fv= 'pmod'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 122, FOLLOW_122_in_function2481);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "pmod";

            }
                break;
            case 53:
            // BELScriptWalker_v1.g:184:9: fv= 'substitution'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 139, FOLLOW_139_in_function2517);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "sub";

            }
                break;
            case 54:
            // BELScriptWalker_v1.g:185:9: fv= 'sub'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 137, FOLLOW_137_in_function2545);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "sub";

            }
                break;
            case 55:
            // BELScriptWalker_v1.g:186:9: fv= 'truncation'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 149, FOLLOW_149_in_function2582);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "trunc";

            }
                break;
            case 56:
            // BELScriptWalker_v1.g:187:9: fv= 'trunc'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 148, FOLLOW_148_in_function2612);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "trunc";

            }
                break;
            case 57:
            // BELScriptWalker_v1.g:188:9: fv= 'reactants'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 130, FOLLOW_130_in_function2647);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "reactants";

            }
                break;
            case 58:
            // BELScriptWalker_v1.g:189:9: fv= 'products'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 124, FOLLOW_124_in_function2678);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "products";

            }
                break;
            case 59:
            // BELScriptWalker_v1.g:190:9: fv= 'list'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                fv = (CommonTree) match(input, 109, FOLLOW_109_in_function2710);
                fv_tree = (CommonTree) adaptor.dupNode(fv);

                adaptor.addChild(root_0, fv_tree);

                retval.r = "list";

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "function"

    public static class relationship_return extends TreeRuleReturnScope {
        public String r;
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "relationship"
    // BELScriptWalker_v1.g:193:1: relationship returns [String r] : (rv= 'increases' |rv= '->' |rv= 'decreases' |rv= '-|' |rv= 'directlyIncreases' |rv= '=>' |rv= 'directlyDecreases' |rv= '=|' |rv= 'causesNoChange' |rv= 'positiveCorrelation' |rv= 'negativeCorrelation' |rv= 'translatedTo' |rv= '>>' |rv= 'transcribedTo' |rv= ':>' |rv= 'isA' |rv= 'subProcessOf' |rv= 'rateLimitingStepOf' |rv= 'biomarkerFor' |rv= 'prognosticBiomarkerFor' |rv= 'orthologous' |rv= 'analogous' |rv= 'association' |rv= '--' |rv= 'hasMembers' |rv= 'hasComponents' |rv= 'hasMember' |rv= 'hasComponent' );
    public final BELScriptWalker_v1.relationship_return relationship()
            throws RecognitionException {
        BELScriptWalker_v1.relationship_return retval = new BELScriptWalker_v1.relationship_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree rv = null;

        CommonTree rv_tree = null;

        try {
            // BELScriptWalker_v1.g:194:5: (rv= 'increases' |rv= '->' |rv= 'decreases' |rv= '-|' |rv= 'directlyIncreases' |rv= '=>' |rv= 'directlyDecreases' |rv= '=|' |rv= 'causesNoChange' |rv= 'positiveCorrelation' |rv= 'negativeCorrelation' |rv= 'translatedTo' |rv= '>>' |rv= 'transcribedTo' |rv= ':>' |rv= 'isA' |rv= 'subProcessOf' |rv= 'rateLimitingStepOf' |rv= 'biomarkerFor' |rv= 'prognosticBiomarkerFor' |rv= 'orthologous' |rv= 'analogous' |rv= 'association' |rv= '--' |rv= 'hasMembers' |rv= 'hasComponents' |rv= 'hasMember' |rv= 'hasComponent' )
            int alt23 = 28;
            switch (input.LA(1)) {
            case 105: {
                alt23 = 1;
            }
                break;
            case 65: {
                alt23 = 2;
            }
                break;
            case 90: {
                alt23 = 3;
            }
                break;
            case 66: {
                alt23 = 4;
            }
                break;
            case 94: {
                alt23 = 5;
            }
                break;
            case 68: {
                alt23 = 6;
            }
                break;
            case 93: {
                alt23 = 7;
            }
                break;
            case 69: {
                alt23 = 8;
            }
                break;
            case 81: {
                alt23 = 9;
            }
                break;
            case 123: {
                alt23 = 10;
            }
                break;
            case 113: {
                alt23 = 11;
            }
                break;
            case 145: {
                alt23 = 12;
            }
                break;
            case 70: {
                alt23 = 13;
            }
                break;
            case 143: {
                alt23 = 14;
            }
                break;
            case 67: {
                alt23 = 15;
            }
                break;
            case 106: {
                alt23 = 16;
            }
                break;
            case 138: {
                alt23 = 17;
            }
                break;
            case 129: {
                alt23 = 18;
            }
                break;
            case 77: {
                alt23 = 19;
            }
                break;
            case 125: {
                alt23 = 20;
            }
                break;
            case 114: {
                alt23 = 21;
            }
                break;
            case 74: {
                alt23 = 22;
            }
                break;
            case 75: {
                alt23 = 23;
            }
                break;
            case 64: {
                alt23 = 24;
            }
                break;
            case 104: {
                alt23 = 25;
            }
                break;
            case 102: {
                alt23 = 26;
            }
                break;
            case 103: {
                alt23 = 27;
            }
                break;
            case 101: {
                alt23 = 28;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
            case 1:
            // BELScriptWalker_v1.g:194:9: rv= 'increases'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 105,
                        FOLLOW_105_in_relationship2759);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "increases";

            }
                break;
            case 2:
            // BELScriptWalker_v1.g:195:9: rv= '->'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 65,
                        FOLLOW_65_in_relationship2790);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "increases";

            }
                break;
            case 3:
            // BELScriptWalker_v1.g:196:9: rv= 'decreases'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 90,
                        FOLLOW_90_in_relationship2828);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "decreases";

            }
                break;
            case 4:
            // BELScriptWalker_v1.g:197:9: rv= '-|'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 66,
                        FOLLOW_66_in_relationship2859);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "decreases";

            }
                break;
            case 5:
            // BELScriptWalker_v1.g:198:9: rv= 'directlyIncreases'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 94,
                        FOLLOW_94_in_relationship2897);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyIncreases";

            }
                break;
            case 6:
            // BELScriptWalker_v1.g:199:9: rv= '=>'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 68,
                        FOLLOW_68_in_relationship2920);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyIncreases";

            }
                break;
            case 7:
            // BELScriptWalker_v1.g:200:9: rv= 'directlyDecreases'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 93,
                        FOLLOW_93_in_relationship2958);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyDecreases";

            }
                break;
            case 8:
            // BELScriptWalker_v1.g:201:9: rv= '=|'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 69,
                        FOLLOW_69_in_relationship2981);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyDecreases";

            }
                break;
            case 9:
            // BELScriptWalker_v1.g:202:9: rv= 'causesNoChange'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 81,
                        FOLLOW_81_in_relationship3019);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "causesNoChange";

            }
                break;
            case 10:
            // BELScriptWalker_v1.g:203:9: rv= 'positiveCorrelation'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 123,
                        FOLLOW_123_in_relationship3045);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "positiveCorrelation";

            }
                break;
            case 11:
            // BELScriptWalker_v1.g:204:9: rv= 'negativeCorrelation'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 113,
                        FOLLOW_113_in_relationship3066);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "negativeCorrelation";

            }
                break;
            case 12:
            // BELScriptWalker_v1.g:205:9: rv= 'translatedTo'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 145,
                        FOLLOW_145_in_relationship3087);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "translatedTo";

            }
                break;
            case 13:
            // BELScriptWalker_v1.g:206:9: rv= '>>'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 70,
                        FOLLOW_70_in_relationship3115);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "translatedTo";

            }
                break;
            case 14:
            // BELScriptWalker_v1.g:207:9: rv= 'transcribedTo'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 143,
                        FOLLOW_143_in_relationship3153);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "transcribedTo";

            }
                break;
            case 15:
            // BELScriptWalker_v1.g:208:9: rv= ':>'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 67,
                        FOLLOW_67_in_relationship3180);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "transcribedTo";

            }
                break;
            case 16:
            // BELScriptWalker_v1.g:209:9: rv= 'isA'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 106,
                        FOLLOW_106_in_relationship3218);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "isA";

            }
                break;
            case 17:
            // BELScriptWalker_v1.g:210:9: rv= 'subProcessOf'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 138,
                        FOLLOW_138_in_relationship3255);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "subProcessOf";

            }
                break;
            case 18:
            // BELScriptWalker_v1.g:211:9: rv= 'rateLimitingStepOf'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 129,
                        FOLLOW_129_in_relationship3283);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "rateLimitingStepOf";

            }
                break;
            case 19:
            // BELScriptWalker_v1.g:212:9: rv= 'biomarkerFor'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 77,
                        FOLLOW_77_in_relationship3305);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "biomarkerFor";

            }
                break;
            case 20:
            // BELScriptWalker_v1.g:213:9: rv= 'prognosticBiomarkerFor'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 125,
                        FOLLOW_125_in_relationship3333);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "prognosticBiomarkerFor";

            }
                break;
            case 21:
            // BELScriptWalker_v1.g:214:9: rv= 'orthologous'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 114,
                        FOLLOW_114_in_relationship3351);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "orthologous";

            }
                break;
            case 22:
            // BELScriptWalker_v1.g:215:9: rv= 'analogous'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 74,
                        FOLLOW_74_in_relationship3380);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "analogous";

            }
                break;
            case 23:
            // BELScriptWalker_v1.g:216:9: rv= 'association'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 75,
                        FOLLOW_75_in_relationship3411);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "association";

            }
                break;
            case 24:
            // BELScriptWalker_v1.g:217:9: rv= '--'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 64,
                        FOLLOW_64_in_relationship3440);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "association";

            }
                break;
            case 25:
            // BELScriptWalker_v1.g:218:9: rv= 'hasMembers'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 104,
                        FOLLOW_104_in_relationship3478);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasMembers";

            }
                break;
            case 26:
            // BELScriptWalker_v1.g:219:9: rv= 'hasComponents'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 102,
                        FOLLOW_102_in_relationship3508);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasComponents";

            }
                break;
            case 27:
            // BELScriptWalker_v1.g:220:9: rv= 'hasMember'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 103,
                        FOLLOW_103_in_relationship3535);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasMember";

            }
                break;
            case 28:
            // BELScriptWalker_v1.g:221:9: rv= 'hasComponent'
            {
                root_0 = (CommonTree) adaptor.nil();

                _last = (CommonTree) input.LT(1);
                rv = (CommonTree) match(input, 101,
                        FOLLOW_101_in_relationship3566);
                rv_tree = (CommonTree) adaptor.dupNode(rv);

                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasComponent";

            }
                break;

            }
            retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "relationship"

    // Delegated rules

    public static final BitSet FOLLOW_DOCDEF_in_document198 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_record_in_document200 = new BitSet(
            new long[] { 0x39604000000393D8L });
    public static final BitSet FOLLOW_define_namespace_in_record221 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_define_annotation_in_record231 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_annotation_in_record241 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_document_in_record251 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_statement_group_in_record261 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_unset_statement_group_in_record271 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_unset_in_record281 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_statement_in_record291 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_doc_expr310 = new BitSet(
            new long[] { 0x8000000100000000L });
    public static final BitSet FOLLOW_WS_in_set_doc_expr312 = new BitSet(
            new long[] { 0x8000000100000000L });
    public static final BitSet FOLLOW_KWRD_DOCUMENT_in_set_doc_expr315 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_WS_in_set_doc_expr317 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_DOCSET_QV_in_set_document333 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_document_property_in_set_document335 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_document337 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_DOCSET_LIST_in_set_document344 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_document_property_in_set_document346 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_set_document348 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_DOCSET_ID_in_set_document355 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_document_property_in_set_document357 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_document359 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_SG_SET_QV_in_set_statement_group380 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_statement_group382 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_SG_SET_ID_in_set_statement_group394 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_statement_group396 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_ANNO_SET_QV_in_set_annotation417 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation419 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_annotation421 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_ANNO_SET_LIST_in_set_annotation433 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation435 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_set_annotation437 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_ANNO_SET_ID_in_set_annotation449 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation451 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation453 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_UNSET_SG_in_unset_statement_group473 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_UNSET_ID_in_unset493 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_unset495 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_UNSET_ID_LIST_in_unset507 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_IDENT_LIST_in_unset509 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_DFLT_NSDEF_in_define_namespace530 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_namespace532 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_namespace534 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_NSDEF_in_define_namespace546 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_namespace548 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_namespace550 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_KWRD_DEFINE_in_define_anno_expr570 = new BitSet(
            new long[] { 0x8000000000800000L });
    public static final BitSet FOLLOW_WS_in_define_anno_expr572 = new BitSet(
            new long[] { 0x8000000000800000L });
    public static final BitSet FOLLOW_KWRD_ANNO_in_define_anno_expr575 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_WS_in_define_anno_expr577 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_ANNO_DEF_LIST_in_define_annotation598 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_annotation600 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_define_annotation602 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_ANNO_DEF_URL_in_define_annotation614 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_annotation616 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_annotation618 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_KWRD_AUTHORS_in_document_property638 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_CONTACTINFO_in_document_property652 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_COPYRIGHT_in_document_property666 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_DESC_in_document_property680 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_DISCLAIMER_in_document_property694 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_LICENSES_in_document_property708 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_NAME_in_document_property722 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_VERSION_in_document_property736 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_term_in_argument759 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_param_in_argument769 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_TERMDEF_in_term789 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_function_in_term791 = new BitSet(
            new long[] { 0x0206000000000008L });
    public static final BitSet FOLLOW_argument_in_term793 = new BitSet(
            new long[] { 0x0206000000000008L });
    public static final BitSet FOLLOW_STMTDEF_in_statement815 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_STATEMENT_COMMENT_in_statement817 = new BitSet(
            new long[] { 0x0200000000000000L });
    public static final BitSet FOLLOW_term_in_statement820 = new BitSet(
            new long[] { 0x0000000000000008L, 0x280607E064022C7FL,
                0x0000000000028402L });
    public static final BitSet FOLLOW_relationship_in_statement823 = new BitSet(
            new long[] { 0x0200000000000000L });
    public static final BitSet FOLLOW_term_in_statement825 = new BitSet(
            new long[] { 0x0000000000000008L, 0x280607E064022C7FL,
                0x0000000000028402L });
    public static final BitSet FOLLOW_relationship_in_statement828 = new BitSet(
            new long[] { 0x0200000000000000L });
    public static final BitSet FOLLOW_term_in_statement830 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_ns_prefix854 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_PARAM_DEF_ID_in_param874 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_ns_prefix_in_param876 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_param879 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_PARAM_DEF_QV_in_param891 = new BitSet(
            new long[] { 0x0000000000000004L });
    public static final BitSet FOLLOW_ns_prefix_in_param893 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_param896 = new BitSet(
            new long[] { 0x0000000000000008L });
    public static final BitSet FOLLOW_126_in_function922 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_115_in_function946 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_134_in_function985 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_128_in_function1013 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_72_in_function1052 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_71_in_function1083 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_111_in_function1122 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_110_in_function1145 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_98_in_function1184 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_97_in_function1211 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_76_in_function1250 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_78_in_function1273 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_117_in_function1311 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_116_in_function1342 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_87_in_function1378 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_86_in_function1402 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_146_in_function1435 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_141_in_function1462 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_82_in_function1498 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_136_in_function1525 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_83_in_function1562 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_140_in_function1581 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_131_in_function1617 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_135_in_function1649 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_89_in_function1686 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_88_in_function1708 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_96_in_function1739 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_95_in_function1773 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_92_in_function1810 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_91_in_function1839 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_112_in_function1876 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_73_in_function1899 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_80_in_function1936 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_79_in_function1959 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_108_in_function1996 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_107_in_function2022 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_121_in_function2059 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_120_in_function2080 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_119_in_function2116 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_118_in_function2139 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_133_in_function2176 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_132_in_function2196 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_144_in_function2232 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_150_in_function2249 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_147_in_function2282 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_142_in_function2305 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_100_in_function2340 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_99_in_function2364 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_85_in_function2401 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_84_in_function2424 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_127_in_function2460 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_122_in_function2481 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_139_in_function2517 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_137_in_function2545 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_149_in_function2582 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_148_in_function2612 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_130_in_function2647 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_124_in_function2678 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_109_in_function2710 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_105_in_relationship2759 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_65_in_relationship2790 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_90_in_relationship2828 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_66_in_relationship2859 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_94_in_relationship2897 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_68_in_relationship2920 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_93_in_relationship2958 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_69_in_relationship2981 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_81_in_relationship3019 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_123_in_relationship3045 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_113_in_relationship3066 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_145_in_relationship3087 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_70_in_relationship3115 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_143_in_relationship3153 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_67_in_relationship3180 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_106_in_relationship3218 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_138_in_relationship3255 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_129_in_relationship3283 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_77_in_relationship3305 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_125_in_relationship3333 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_114_in_relationship3351 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_74_in_relationship3380 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_75_in_relationship3411 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_64_in_relationship3440 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_104_in_relationship3478 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_102_in_relationship3508 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_103_in_relationship3535 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_101_in_relationship3566 = new BitSet(
            new long[] { 0x0000000000000002L });

}