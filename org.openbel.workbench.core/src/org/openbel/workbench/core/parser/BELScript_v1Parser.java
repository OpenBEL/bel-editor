/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.core.parser;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

@SuppressWarnings({ "all", "warnings", "unchecked" })
public class BELScript_v1Parser extends Parser {
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
        "'tscript'"
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

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators

    public BELScript_v1Parser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public BELScript_v1Parser(TokenStream input, RecognizerSharedState state) {
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
        return BELScript_v1Parser.tokenNames;
    }

    public String getGrammarFileName() {
        return "BELScript_v1.g";
    }

    public static class document_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "document"
    // BELScript_v1.g:67:1: document : ( NEWLINE | DOCUMENT_COMMENT | record )+ EOF -> ^( DOCDEF ( record )+ ) ;
    public final BELScript_v1Parser.document_return document()
            throws RecognitionException {
        BELScript_v1Parser.document_return retval = new BELScript_v1Parser.document_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NEWLINE1 = null;
        Token DOCUMENT_COMMENT2 = null;
        Token EOF4 = null;
        BELScript_v1Parser.record_return record3 = null;

        Object NEWLINE1_tree = null;
        Object DOCUMENT_COMMENT2_tree = null;
        Object EOF4_tree = null;
        RewriteRuleTokenStream stream_DOCUMENT_COMMENT = new RewriteRuleTokenStream(
                adaptor, "token DOCUMENT_COMMENT");
        RewriteRuleTokenStream stream_NEWLINE = new RewriteRuleTokenStream(
                adaptor, "token NEWLINE");
        RewriteRuleTokenStream stream_EOF = new RewriteRuleTokenStream(adaptor,
                "token EOF");
        RewriteRuleSubtreeStream stream_record = new RewriteRuleSubtreeStream(
                adaptor, "rule record");
        try {
            // BELScript_v1.g:68:5: ( ( NEWLINE | DOCUMENT_COMMENT | record )+ EOF -> ^( DOCDEF ( record )+ ) )
            // BELScript_v1.g:68:9: ( NEWLINE | DOCUMENT_COMMENT | record )+ EOF
            {
                // BELScript_v1.g:68:9: ( NEWLINE | DOCUMENT_COMMENT | record )+
                int cnt1 = 0;
                loop1: do {
                    int alt1 = 4;
                    switch (input.LA(1)) {
                    case NEWLINE: {
                        alt1 = 1;
                    }
                        break;
                    case DOCUMENT_COMMENT: {
                        alt1 = 2;
                    }
                        break;
                    case KWRD_DEFINE:
                    case KWRD_SET:
                    case KWRD_UNSET:
                    case 71:
                    case 72:
                    case 73:
                    case 76:
                    case 78:
                    case 79:
                    case 80:
                    case 82:
                    case 83:
                    case 84:
                    case 85:
                    case 86:
                    case 87:
                    case 88:
                    case 89:
                    case 91:
                    case 92:
                    case 95:
                    case 96:
                    case 97:
                    case 98:
                    case 99:
                    case 100:
                    case 107:
                    case 108:
                    case 109:
                    case 110:
                    case 111:
                    case 112:
                    case 115:
                    case 116:
                    case 117:
                    case 118:
                    case 119:
                    case 120:
                    case 121:
                    case 122:
                    case 124:
                    case 126:
                    case 127:
                    case 128:
                    case 130:
                    case 131:
                    case 132:
                    case 133:
                    case 134:
                    case 135:
                    case 136:
                    case 137:
                    case 139:
                    case 140:
                    case 141:
                    case 142:
                    case 144:
                    case 146:
                    case 147:
                    case 148:
                    case 149:
                    case 150: {
                        alt1 = 3;
                    }
                        break;

                    }

                    switch (alt1) {
                    case 1:
                    // BELScript_v1.g:68:10: NEWLINE
                    {
                        NEWLINE1 = (Token) match(input, NEWLINE,
                                FOLLOW_NEWLINE_in_document327);
                        stream_NEWLINE.add(NEWLINE1);

                    }
                        break;
                    case 2:
                    // BELScript_v1.g:68:20: DOCUMENT_COMMENT
                    {
                        DOCUMENT_COMMENT2 = (Token) match(input,
                                DOCUMENT_COMMENT,
                                FOLLOW_DOCUMENT_COMMENT_in_document331);
                        stream_DOCUMENT_COMMENT.add(DOCUMENT_COMMENT2);

                    }
                        break;
                    case 3:
                    // BELScript_v1.g:68:39: record
                    {
                        pushFollow(FOLLOW_record_in_document335);
                        record3 = record();

                        state._fsp--;

                        stream_record.add(record3.getTree());

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

                EOF4 = (Token) match(input, EOF, FOLLOW_EOF_in_document339);
                stream_EOF.add(EOF4);

                // AST REWRITE
                // elements: record
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 68:52: -> ^( DOCDEF ( record )+ )
                {
                    // BELScript_v1.g:69:9: ^( DOCDEF ( record )+ )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(DOCDEF, "DOCDEF")
                                , root_1);

                        if (!(stream_record.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_record.hasNext()) {
                            adaptor.addChild(root_1, stream_record.nextTree());

                        }
                        stream_record.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "document"

    public static class record_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "record"
    // BELScript_v1.g:72:1: record : ( define_namespace | define_annotation | set_annotation | set_document | set_statement_group | unset_statement_group | unset | statement );
    public final BELScript_v1Parser.record_return record()
            throws RecognitionException {
        BELScript_v1Parser.record_return retval = new BELScript_v1Parser.record_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        BELScript_v1Parser.define_namespace_return define_namespace5 = null;

        BELScript_v1Parser.define_annotation_return define_annotation6 = null;

        BELScript_v1Parser.set_annotation_return set_annotation7 = null;

        BELScript_v1Parser.set_document_return set_document8 = null;

        BELScript_v1Parser.set_statement_group_return set_statement_group9 = null;

        BELScript_v1Parser.unset_statement_group_return unset_statement_group10 = null;

        BELScript_v1Parser.unset_return unset11 = null;

        BELScript_v1Parser.statement_return statement12 = null;

        try {
            // BELScript_v1.g:73:5: ( define_namespace | define_annotation | set_annotation | set_document | set_statement_group | unset_statement_group | unset | statement )
            int alt2 = 8;
            alt2 = dfa2.predict(input);
            switch (alt2) {
            case 1:
            // BELScript_v1.g:73:9: define_namespace
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_define_namespace_in_record375);
                define_namespace5 = define_namespace();

                state._fsp--;

                adaptor.addChild(root_0, define_namespace5.getTree());

            }
                break;
            case 2:
            // BELScript_v1.g:74:9: define_annotation
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_define_annotation_in_record385);
                define_annotation6 = define_annotation();

                state._fsp--;

                adaptor.addChild(root_0, define_annotation6.getTree());

            }
                break;
            case 3:
            // BELScript_v1.g:75:9: set_annotation
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_set_annotation_in_record395);
                set_annotation7 = set_annotation();

                state._fsp--;

                adaptor.addChild(root_0, set_annotation7.getTree());

            }
                break;
            case 4:
            // BELScript_v1.g:76:9: set_document
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_set_document_in_record405);
                set_document8 = set_document();

                state._fsp--;

                adaptor.addChild(root_0, set_document8.getTree());

            }
                break;
            case 5:
            // BELScript_v1.g:77:9: set_statement_group
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_set_statement_group_in_record415);
                set_statement_group9 = set_statement_group();

                state._fsp--;

                adaptor.addChild(root_0, set_statement_group9.getTree());

            }
                break;
            case 6:
            // BELScript_v1.g:78:9: unset_statement_group
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_unset_statement_group_in_record425);
                unset_statement_group10 = unset_statement_group();

                state._fsp--;

                adaptor.addChild(root_0, unset_statement_group10.getTree());

            }
                break;
            case 7:
            // BELScript_v1.g:79:9: unset
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_unset_in_record435);
                unset11 = unset();

                state._fsp--;

                adaptor.addChild(root_0, unset11.getTree());

            }
                break;
            case 8:
            // BELScript_v1.g:80:9: statement
            {
                root_0 = (Object) adaptor.nil();

                pushFollow(FOLLOW_statement_in_record445);
                statement12 = statement();

                state._fsp--;

                adaptor.addChild(root_0, statement12.getTree());

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "record"

    public static class set_doc_expr_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_doc_expr"
    // BELScript_v1.g:83:1: set_doc_expr : KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )* ;
    public final BELScript_v1Parser.set_doc_expr_return set_doc_expr()
            throws RecognitionException {
        BELScript_v1Parser.set_doc_expr_return retval = new BELScript_v1Parser.set_doc_expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KWRD_SET13 = null;
        Token WS14 = null;
        Token KWRD_DOCUMENT15 = null;
        Token WS16 = null;

        Object KWRD_SET13_tree = null;
        Object WS14_tree = null;
        Object KWRD_DOCUMENT15_tree = null;
        Object WS16_tree = null;

        try {
            // BELScript_v1.g:84:5: ( KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )* )
            // BELScript_v1.g:84:9: KWRD_SET ( WS )* KWRD_DOCUMENT ( WS )*
            {
                root_0 = (Object) adaptor.nil();

                KWRD_SET13 = (Token) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_doc_expr464);
                KWRD_SET13_tree =
                        (Object) adaptor.create(KWRD_SET13);
                adaptor.addChild(root_0, KWRD_SET13_tree);

                // BELScript_v1.g:84:18: ( WS )*
                loop3: do {
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == WS)) {
                        alt3 = 1;
                    }

                    switch (alt3) {
                    case 1:
                    // BELScript_v1.g:84:18: WS
                    {
                        WS14 = (Token) match(input, WS,
                                FOLLOW_WS_in_set_doc_expr466);
                        WS14_tree =
                                (Object) adaptor.create(WS14);
                        adaptor.addChild(root_0, WS14_tree);

                    }
                        break;

                    default:
                        break loop3;
                    }
                } while (true);

                KWRD_DOCUMENT15 = (Token) match(input, KWRD_DOCUMENT,
                        FOLLOW_KWRD_DOCUMENT_in_set_doc_expr469);
                KWRD_DOCUMENT15_tree =
                        (Object) adaptor.create(KWRD_DOCUMENT15);
                adaptor.addChild(root_0, KWRD_DOCUMENT15_tree);

                // BELScript_v1.g:84:36: ( WS )*
                loop4: do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if ((LA4_0 == WS)) {
                        alt4 = 1;
                    }

                    switch (alt4) {
                    case 1:
                    // BELScript_v1.g:84:36: WS
                    {
                        WS16 = (Token) match(input, WS,
                                FOLLOW_WS_in_set_doc_expr471);
                        WS16_tree =
                                (Object) adaptor.create(WS16);
                        adaptor.addChild(root_0, WS16_tree);

                    }
                        break;

                    default:
                        break loop4;
                    }
                } while (true);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_doc_expr"

    public static class set_document_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_document"
    // BELScript_v1.g:87:1: set_document : ( set_doc_expr document_property eq_clause val= QUOTED_VALUE -> ^( DOCSET_QV document_property $val) | set_doc_expr document_property eq_clause val= VALUE_LIST -> ^( DOCSET_LIST document_property $val) | set_doc_expr document_property eq_clause val= OBJECT_IDENT -> ^( DOCSET_ID document_property $val) );
    public final BELScript_v1Parser.set_document_return set_document()
            throws RecognitionException {
        BELScript_v1Parser.set_document_return retval = new BELScript_v1Parser.set_document_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val = null;
        BELScript_v1Parser.set_doc_expr_return set_doc_expr17 = null;

        BELScript_v1Parser.document_property_return document_property18 = null;

        BELScript_v1Parser.eq_clause_return eq_clause19 = null;

        BELScript_v1Parser.set_doc_expr_return set_doc_expr20 = null;

        BELScript_v1Parser.document_property_return document_property21 = null;

        BELScript_v1Parser.eq_clause_return eq_clause22 = null;

        BELScript_v1Parser.set_doc_expr_return set_doc_expr23 = null;

        BELScript_v1Parser.document_property_return document_property24 = null;

        BELScript_v1Parser.eq_clause_return eq_clause25 = null;

        Object val_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleTokenStream stream_VALUE_LIST = new RewriteRuleTokenStream(
                adaptor, "token VALUE_LIST");
        RewriteRuleSubtreeStream stream_set_doc_expr = new RewriteRuleSubtreeStream(
                adaptor, "rule set_doc_expr");
        RewriteRuleSubtreeStream stream_eq_clause = new RewriteRuleSubtreeStream(
                adaptor, "rule eq_clause");
        RewriteRuleSubtreeStream stream_document_property = new RewriteRuleSubtreeStream(
                adaptor, "rule document_property");
        try {
            // BELScript_v1.g:88:5: ( set_doc_expr document_property eq_clause val= QUOTED_VALUE -> ^( DOCSET_QV document_property $val) | set_doc_expr document_property eq_clause val= VALUE_LIST -> ^( DOCSET_LIST document_property $val) | set_doc_expr document_property eq_clause val= OBJECT_IDENT -> ^( DOCSET_ID document_property $val) )
            int alt5 = 3;
            alt5 = dfa5.predict(input);
            switch (alt5) {
            case 1:
            // BELScript_v1.g:88:9: set_doc_expr document_property eq_clause val= QUOTED_VALUE
            {
                pushFollow(FOLLOW_set_doc_expr_in_set_document491);
                set_doc_expr17 = set_doc_expr();

                state._fsp--;

                stream_set_doc_expr.add(set_doc_expr17.getTree());

                pushFollow(FOLLOW_document_property_in_set_document493);
                document_property18 = document_property();

                state._fsp--;

                stream_document_property.add(document_property18.getTree());

                pushFollow(FOLLOW_eq_clause_in_set_document495);
                eq_clause19 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause19.getTree());

                val = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_set_document499);
                stream_QUOTED_VALUE.add(val);

                // AST REWRITE
                // elements: val, document_property
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 88:67: -> ^( DOCSET_QV document_property $val)
                {
                    // BELScript_v1.g:89:9: ^( DOCSET_QV document_property $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(DOCSET_QV, "DOCSET_QV")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_document_property.nextTree());

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:90:9: set_doc_expr document_property eq_clause val= VALUE_LIST
            {
                pushFollow(FOLLOW_set_doc_expr_in_set_document528);
                set_doc_expr20 = set_doc_expr();

                state._fsp--;

                stream_set_doc_expr.add(set_doc_expr20.getTree());

                pushFollow(FOLLOW_document_property_in_set_document530);
                document_property21 = document_property();

                state._fsp--;

                stream_document_property.add(document_property21.getTree());

                pushFollow(FOLLOW_eq_clause_in_set_document532);
                eq_clause22 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause22.getTree());

                val = (Token) match(input, VALUE_LIST,
                        FOLLOW_VALUE_LIST_in_set_document536);
                stream_VALUE_LIST.add(val);

                // AST REWRITE
                // elements: document_property, val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 90:65: -> ^( DOCSET_LIST document_property $val)
                {
                    // BELScript_v1.g:91:9: ^( DOCSET_LIST document_property $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(DOCSET_LIST,
                                        "DOCSET_LIST")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_document_property.nextTree());

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 3:
            // BELScript_v1.g:92:9: set_doc_expr document_property eq_clause val= OBJECT_IDENT
            {
                pushFollow(FOLLOW_set_doc_expr_in_set_document565);
                set_doc_expr23 = set_doc_expr();

                state._fsp--;

                stream_set_doc_expr.add(set_doc_expr23.getTree());

                pushFollow(FOLLOW_document_property_in_set_document567);
                document_property24 = document_property();

                state._fsp--;

                stream_document_property.add(document_property24.getTree());

                pushFollow(FOLLOW_eq_clause_in_set_document569);
                eq_clause25 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause25.getTree());

                val = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_document573);
                stream_OBJECT_IDENT.add(val);

                // AST REWRITE
                // elements: val, document_property
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 92:67: -> ^( DOCSET_ID document_property $val)
                {
                    // BELScript_v1.g:93:9: ^( DOCSET_ID document_property $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(DOCSET_ID, "DOCSET_ID")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_document_property.nextTree());

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_document"

    public static class set_sg_expr_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_sg_expr"
    // BELScript_v1.g:96:1: set_sg_expr : KWRD_SET ( WS )* KWRD_STMT_GROUP ;
    public final BELScript_v1Parser.set_sg_expr_return set_sg_expr()
            throws RecognitionException {
        BELScript_v1Parser.set_sg_expr_return retval = new BELScript_v1Parser.set_sg_expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KWRD_SET26 = null;
        Token WS27 = null;
        Token KWRD_STMT_GROUP28 = null;

        Object KWRD_SET26_tree = null;
        Object WS27_tree = null;
        Object KWRD_STMT_GROUP28_tree = null;

        try {
            // BELScript_v1.g:97:5: ( KWRD_SET ( WS )* KWRD_STMT_GROUP )
            // BELScript_v1.g:97:9: KWRD_SET ( WS )* KWRD_STMT_GROUP
            {
                root_0 = (Object) adaptor.nil();

                KWRD_SET26 = (Token) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_sg_expr611);
                KWRD_SET26_tree =
                        (Object) adaptor.create(KWRD_SET26);
                adaptor.addChild(root_0, KWRD_SET26_tree);

                // BELScript_v1.g:97:18: ( WS )*
                loop6: do {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == WS)) {
                        alt6 = 1;
                    }

                    switch (alt6) {
                    case 1:
                    // BELScript_v1.g:97:18: WS
                    {
                        WS27 = (Token) match(input, WS,
                                FOLLOW_WS_in_set_sg_expr613);
                        WS27_tree =
                                (Object) adaptor.create(WS27);
                        adaptor.addChild(root_0, WS27_tree);

                    }
                        break;

                    default:
                        break loop6;
                    }
                } while (true);

                KWRD_STMT_GROUP28 = (Token) match(input, KWRD_STMT_GROUP,
                        FOLLOW_KWRD_STMT_GROUP_in_set_sg_expr616);
                KWRD_STMT_GROUP28_tree =
                        (Object) adaptor.create(KWRD_STMT_GROUP28);
                adaptor.addChild(root_0, KWRD_STMT_GROUP28_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_sg_expr"

    public static class set_statement_group_return extends
            ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_statement_group"
    // BELScript_v1.g:100:1: set_statement_group : ( set_sg_expr eq_clause val= QUOTED_VALUE -> ^( SG_SET_QV $val) | set_sg_expr eq_clause val= OBJECT_IDENT -> ^( SG_SET_ID $val) );
    public final BELScript_v1Parser.set_statement_group_return set_statement_group()
            throws RecognitionException {
        BELScript_v1Parser.set_statement_group_return retval = new BELScript_v1Parser.set_statement_group_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val = null;
        BELScript_v1Parser.set_sg_expr_return set_sg_expr29 = null;

        BELScript_v1Parser.eq_clause_return eq_clause30 = null;

        BELScript_v1Parser.set_sg_expr_return set_sg_expr31 = null;

        BELScript_v1Parser.eq_clause_return eq_clause32 = null;

        Object val_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleSubtreeStream stream_eq_clause = new RewriteRuleSubtreeStream(
                adaptor, "rule eq_clause");
        RewriteRuleSubtreeStream stream_set_sg_expr = new RewriteRuleSubtreeStream(
                adaptor, "rule set_sg_expr");
        try {
            // BELScript_v1.g:101:5: ( set_sg_expr eq_clause val= QUOTED_VALUE -> ^( SG_SET_QV $val) | set_sg_expr eq_clause val= OBJECT_IDENT -> ^( SG_SET_ID $val) )
            int alt7 = 2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
            case 1:
            // BELScript_v1.g:101:9: set_sg_expr eq_clause val= QUOTED_VALUE
            {
                pushFollow(FOLLOW_set_sg_expr_in_set_statement_group635);
                set_sg_expr29 = set_sg_expr();

                state._fsp--;

                stream_set_sg_expr.add(set_sg_expr29.getTree());

                pushFollow(FOLLOW_eq_clause_in_set_statement_group637);
                eq_clause30 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause30.getTree());

                val = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_set_statement_group641);
                stream_QUOTED_VALUE.add(val);

                // AST REWRITE
                // elements: val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 101:48: -> ^( SG_SET_QV $val)
                {
                    // BELScript_v1.g:101:51: ^( SG_SET_QV $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(SG_SET_QV, "SG_SET_QV")
                                , root_1);

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:102:9: set_sg_expr eq_clause val= OBJECT_IDENT
            {
                pushFollow(FOLLOW_set_sg_expr_in_set_statement_group660);
                set_sg_expr31 = set_sg_expr();

                state._fsp--;

                stream_set_sg_expr.add(set_sg_expr31.getTree());

                pushFollow(FOLLOW_eq_clause_in_set_statement_group662);
                eq_clause32 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause32.getTree());

                val = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_statement_group666);
                stream_OBJECT_IDENT.add(val);

                // AST REWRITE
                // elements: val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 102:48: -> ^( SG_SET_ID $val)
                {
                    // BELScript_v1.g:102:51: ^( SG_SET_ID $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(SG_SET_ID, "SG_SET_ID")
                                , root_1);

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_statement_group"

    public static class set_annotation_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "set_annotation"
    // BELScript_v1.g:105:1: set_annotation : ( KWRD_SET OBJECT_IDENT eq_clause val= QUOTED_VALUE -> ^( ANNO_SET_QV OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= VALUE_LIST -> ^( ANNO_SET_LIST OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= OBJECT_IDENT -> ^( ANNO_SET_ID OBJECT_IDENT $val) );
    public final BELScript_v1Parser.set_annotation_return set_annotation()
            throws RecognitionException {
        BELScript_v1Parser.set_annotation_return retval = new BELScript_v1Parser.set_annotation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val = null;
        Token KWRD_SET33 = null;
        Token OBJECT_IDENT34 = null;
        Token KWRD_SET36 = null;
        Token OBJECT_IDENT37 = null;
        Token KWRD_SET39 = null;
        Token OBJECT_IDENT40 = null;
        BELScript_v1Parser.eq_clause_return eq_clause35 = null;

        BELScript_v1Parser.eq_clause_return eq_clause38 = null;

        BELScript_v1Parser.eq_clause_return eq_clause41 = null;

        Object val_tree = null;
        Object KWRD_SET33_tree = null;
        Object OBJECT_IDENT34_tree = null;
        Object KWRD_SET36_tree = null;
        Object OBJECT_IDENT37_tree = null;
        Object KWRD_SET39_tree = null;
        Object OBJECT_IDENT40_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleTokenStream stream_VALUE_LIST = new RewriteRuleTokenStream(
                adaptor, "token VALUE_LIST");
        RewriteRuleTokenStream stream_KWRD_SET = new RewriteRuleTokenStream(
                adaptor, "token KWRD_SET");
        RewriteRuleSubtreeStream stream_eq_clause = new RewriteRuleSubtreeStream(
                adaptor, "rule eq_clause");
        try {
            // BELScript_v1.g:106:5: ( KWRD_SET OBJECT_IDENT eq_clause val= QUOTED_VALUE -> ^( ANNO_SET_QV OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= VALUE_LIST -> ^( ANNO_SET_LIST OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= OBJECT_IDENT -> ^( ANNO_SET_ID OBJECT_IDENT $val) )
            int alt8 = 3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
            case 1:
            // BELScript_v1.g:106:9: KWRD_SET OBJECT_IDENT eq_clause val= QUOTED_VALUE
            {
                KWRD_SET33 = (Token) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_annotation694);
                stream_KWRD_SET.add(KWRD_SET33);

                OBJECT_IDENT34 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_annotation696);
                stream_OBJECT_IDENT.add(OBJECT_IDENT34);

                pushFollow(FOLLOW_eq_clause_in_set_annotation698);
                eq_clause35 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause35.getTree());

                val = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_set_annotation702);
                stream_QUOTED_VALUE.add(val);

                // AST REWRITE
                // elements: val, OBJECT_IDENT
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 106:58: -> ^( ANNO_SET_QV OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:107:9: ^( ANNO_SET_QV OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_SET_QV,
                                        "ANNO_SET_QV")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:108:9: KWRD_SET OBJECT_IDENT eq_clause val= VALUE_LIST
            {
                KWRD_SET36 = (Token) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_annotation731);
                stream_KWRD_SET.add(KWRD_SET36);

                OBJECT_IDENT37 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_annotation733);
                stream_OBJECT_IDENT.add(OBJECT_IDENT37);

                pushFollow(FOLLOW_eq_clause_in_set_annotation735);
                eq_clause38 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause38.getTree());

                val = (Token) match(input, VALUE_LIST,
                        FOLLOW_VALUE_LIST_in_set_annotation739);
                stream_VALUE_LIST.add(val);

                // AST REWRITE
                // elements: OBJECT_IDENT, val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 108:56: -> ^( ANNO_SET_LIST OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:109:9: ^( ANNO_SET_LIST OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_SET_LIST,
                                        "ANNO_SET_LIST")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 3:
            // BELScript_v1.g:110:9: KWRD_SET OBJECT_IDENT eq_clause val= OBJECT_IDENT
            {
                KWRD_SET39 = (Token) match(input, KWRD_SET,
                        FOLLOW_KWRD_SET_in_set_annotation768);
                stream_KWRD_SET.add(KWRD_SET39);

                OBJECT_IDENT40 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_annotation770);
                stream_OBJECT_IDENT.add(OBJECT_IDENT40);

                pushFollow(FOLLOW_eq_clause_in_set_annotation772);
                eq_clause41 = eq_clause();

                state._fsp--;

                stream_eq_clause.add(eq_clause41.getTree());

                val = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_set_annotation776);
                stream_OBJECT_IDENT.add(val);

                // AST REWRITE
                // elements: val, OBJECT_IDENT
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 110:58: -> ^( ANNO_SET_ID OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:111:9: ^( ANNO_SET_ID OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_SET_ID,
                                        "ANNO_SET_ID")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "set_annotation"

    public static class unset_statement_group_return extends
            ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "unset_statement_group"
    // BELScript_v1.g:114:1: unset_statement_group : KWRD_UNSET KWRD_STMT_GROUP -> ^( UNSET_SG ) ;
    public final BELScript_v1Parser.unset_statement_group_return unset_statement_group()
            throws RecognitionException {
        BELScript_v1Parser.unset_statement_group_return retval = new BELScript_v1Parser.unset_statement_group_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KWRD_UNSET42 = null;
        Token KWRD_STMT_GROUP43 = null;

        Object KWRD_UNSET42_tree = null;
        Object KWRD_STMT_GROUP43_tree = null;
        RewriteRuleTokenStream stream_KWRD_UNSET = new RewriteRuleTokenStream(
                adaptor, "token KWRD_UNSET");
        RewriteRuleTokenStream stream_KWRD_STMT_GROUP = new RewriteRuleTokenStream(
                adaptor, "token KWRD_STMT_GROUP");

        try {
            // BELScript_v1.g:115:5: ( KWRD_UNSET KWRD_STMT_GROUP -> ^( UNSET_SG ) )
            // BELScript_v1.g:115:9: KWRD_UNSET KWRD_STMT_GROUP
            {
                KWRD_UNSET42 = (Token) match(input, KWRD_UNSET,
                        FOLLOW_KWRD_UNSET_in_unset_statement_group814);
                stream_KWRD_UNSET.add(KWRD_UNSET42);

                KWRD_STMT_GROUP43 = (Token) match(input, KWRD_STMT_GROUP,
                        FOLLOW_KWRD_STMT_GROUP_in_unset_statement_group816);
                stream_KWRD_STMT_GROUP.add(KWRD_STMT_GROUP43);

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

                root_0 = (Object) adaptor.nil();
                // 115:36: -> ^( UNSET_SG )
                {
                    // BELScript_v1.g:115:39: ^( UNSET_SG )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(UNSET_SG, "UNSET_SG")
                                , root_1);

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "unset_statement_group"

    public static class unset_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "unset"
    // BELScript_v1.g:118:1: unset : ( KWRD_UNSET val= OBJECT_IDENT -> ^( UNSET_ID $val) | KWRD_UNSET val= IDENT_LIST -> ^( UNSET_ID_LIST $val) );
    public final BELScript_v1Parser.unset_return unset()
            throws RecognitionException {
        BELScript_v1Parser.unset_return retval = new BELScript_v1Parser.unset_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val = null;
        Token KWRD_UNSET44 = null;
        Token KWRD_UNSET45 = null;

        Object val_tree = null;
        Object KWRD_UNSET44_tree = null;
        Object KWRD_UNSET45_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_KWRD_UNSET = new RewriteRuleTokenStream(
                adaptor, "token KWRD_UNSET");
        RewriteRuleTokenStream stream_IDENT_LIST = new RewriteRuleTokenStream(
                adaptor, "token IDENT_LIST");

        try {
            // BELScript_v1.g:119:5: ( KWRD_UNSET val= OBJECT_IDENT -> ^( UNSET_ID $val) | KWRD_UNSET val= IDENT_LIST -> ^( UNSET_ID_LIST $val) )
            int alt9 = 2;
            int LA9_0 = input.LA(1);

            if ((LA9_0 == KWRD_UNSET)) {
                int LA9_1 = input.LA(2);

                if ((LA9_1 == OBJECT_IDENT)) {
                    alt9 = 1;
                }
                else if ((LA9_1 == IDENT_LIST)) {
                    alt9 = 2;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 9, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
            case 1:
            // BELScript_v1.g:119:9: KWRD_UNSET val= OBJECT_IDENT
            {
                KWRD_UNSET44 = (Token) match(input, KWRD_UNSET,
                        FOLLOW_KWRD_UNSET_in_unset841);
                stream_KWRD_UNSET.add(KWRD_UNSET44);

                val = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_unset845);
                stream_OBJECT_IDENT.add(val);

                // AST REWRITE
                // elements: val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 119:37: -> ^( UNSET_ID $val)
                {
                    // BELScript_v1.g:119:40: ^( UNSET_ID $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(UNSET_ID, "UNSET_ID")
                                , root_1);

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:120:9: KWRD_UNSET val= IDENT_LIST
            {
                KWRD_UNSET45 = (Token) match(input, KWRD_UNSET,
                        FOLLOW_KWRD_UNSET_in_unset864);
                stream_KWRD_UNSET.add(KWRD_UNSET45);

                val = (Token) match(input, IDENT_LIST,
                        FOLLOW_IDENT_LIST_in_unset868);
                stream_IDENT_LIST.add(val);

                // AST REWRITE
                // elements: val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 120:35: -> ^( UNSET_ID_LIST $val)
                {
                    // BELScript_v1.g:120:38: ^( UNSET_ID_LIST $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(UNSET_ID_LIST,
                                        "UNSET_ID_LIST")
                                , root_1);

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "unset"

    public static class define_namespace_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_namespace"
    // BELScript_v1.g:123:1: define_namespace : ( KWRD_DEFINE KWRD_DFLT KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE -> ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE ) | KWRD_DEFINE KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE -> ^( NSDEF OBJECT_IDENT QUOTED_VALUE ) );
    public final BELScript_v1Parser.define_namespace_return define_namespace()
            throws RecognitionException {
        BELScript_v1Parser.define_namespace_return retval = new BELScript_v1Parser.define_namespace_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KWRD_DEFINE46 = null;
        Token KWRD_DFLT47 = null;
        Token KWRD_NS48 = null;
        Token OBJECT_IDENT49 = null;
        Token KWRD_AS50 = null;
        Token KWRD_URL51 = null;
        Token QUOTED_VALUE52 = null;
        Token KWRD_DEFINE53 = null;
        Token KWRD_NS54 = null;
        Token OBJECT_IDENT55 = null;
        Token KWRD_AS56 = null;
        Token KWRD_URL57 = null;
        Token QUOTED_VALUE58 = null;

        Object KWRD_DEFINE46_tree = null;
        Object KWRD_DFLT47_tree = null;
        Object KWRD_NS48_tree = null;
        Object OBJECT_IDENT49_tree = null;
        Object KWRD_AS50_tree = null;
        Object KWRD_URL51_tree = null;
        Object QUOTED_VALUE52_tree = null;
        Object KWRD_DEFINE53_tree = null;
        Object KWRD_NS54_tree = null;
        Object OBJECT_IDENT55_tree = null;
        Object KWRD_AS56_tree = null;
        Object KWRD_URL57_tree = null;
        Object QUOTED_VALUE58_tree = null;
        RewriteRuleTokenStream stream_KWRD_DFLT = new RewriteRuleTokenStream(
                adaptor, "token KWRD_DFLT");
        RewriteRuleTokenStream stream_KWRD_DEFINE = new RewriteRuleTokenStream(
                adaptor, "token KWRD_DEFINE");
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleTokenStream stream_KWRD_NS = new RewriteRuleTokenStream(
                adaptor, "token KWRD_NS");
        RewriteRuleTokenStream stream_KWRD_URL = new RewriteRuleTokenStream(
                adaptor, "token KWRD_URL");
        RewriteRuleTokenStream stream_KWRD_AS = new RewriteRuleTokenStream(
                adaptor, "token KWRD_AS");

        try {
            // BELScript_v1.g:124:5: ( KWRD_DEFINE KWRD_DFLT KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE -> ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE ) | KWRD_DEFINE KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE -> ^( NSDEF OBJECT_IDENT QUOTED_VALUE ) )
            int alt10 = 2;
            int LA10_0 = input.LA(1);

            if ((LA10_0 == KWRD_DEFINE)) {
                int LA10_1 = input.LA(2);

                if ((LA10_1 == KWRD_DFLT)) {
                    alt10 = 1;
                }
                else if ((LA10_1 == KWRD_NS)) {
                    alt10 = 2;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 10, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
            case 1:
            // BELScript_v1.g:124:9: KWRD_DEFINE KWRD_DFLT KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE
            {
                KWRD_DEFINE46 = (Token) match(input, KWRD_DEFINE,
                        FOLLOW_KWRD_DEFINE_in_define_namespace896);
                stream_KWRD_DEFINE.add(KWRD_DEFINE46);

                KWRD_DFLT47 = (Token) match(input, KWRD_DFLT,
                        FOLLOW_KWRD_DFLT_in_define_namespace898);
                stream_KWRD_DFLT.add(KWRD_DFLT47);

                KWRD_NS48 = (Token) match(input, KWRD_NS,
                        FOLLOW_KWRD_NS_in_define_namespace900);
                stream_KWRD_NS.add(KWRD_NS48);

                OBJECT_IDENT49 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_define_namespace902);
                stream_OBJECT_IDENT.add(OBJECT_IDENT49);

                KWRD_AS50 = (Token) match(input, KWRD_AS,
                        FOLLOW_KWRD_AS_in_define_namespace904);
                stream_KWRD_AS.add(KWRD_AS50);

                KWRD_URL51 = (Token) match(input, KWRD_URL,
                        FOLLOW_KWRD_URL_in_define_namespace906);
                stream_KWRD_URL.add(KWRD_URL51);

                QUOTED_VALUE52 = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_define_namespace908);
                stream_QUOTED_VALUE.add(QUOTED_VALUE52);

                // AST REWRITE
                // elements: QUOTED_VALUE, OBJECT_IDENT
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 124:82: -> ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE )
                {
                    // BELScript_v1.g:125:9: ^( DFLT_NSDEF OBJECT_IDENT QUOTED_VALUE )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(DFLT_NSDEF,
                                        "DFLT_NSDEF")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1,
                                stream_QUOTED_VALUE.nextNode()
                                );

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:126:9: KWRD_DEFINE KWRD_NS OBJECT_IDENT KWRD_AS KWRD_URL QUOTED_VALUE
            {
                KWRD_DEFINE53 = (Token) match(input, KWRD_DEFINE,
                        FOLLOW_KWRD_DEFINE_in_define_namespace936);
                stream_KWRD_DEFINE.add(KWRD_DEFINE53);

                KWRD_NS54 = (Token) match(input, KWRD_NS,
                        FOLLOW_KWRD_NS_in_define_namespace938);
                stream_KWRD_NS.add(KWRD_NS54);

                OBJECT_IDENT55 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_define_namespace940);
                stream_OBJECT_IDENT.add(OBJECT_IDENT55);

                KWRD_AS56 = (Token) match(input, KWRD_AS,
                        FOLLOW_KWRD_AS_in_define_namespace942);
                stream_KWRD_AS.add(KWRD_AS56);

                KWRD_URL57 = (Token) match(input, KWRD_URL,
                        FOLLOW_KWRD_URL_in_define_namespace944);
                stream_KWRD_URL.add(KWRD_URL57);

                QUOTED_VALUE58 = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_define_namespace946);
                stream_QUOTED_VALUE.add(QUOTED_VALUE58);

                // AST REWRITE
                // elements: QUOTED_VALUE, OBJECT_IDENT
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 126:72: -> ^( NSDEF OBJECT_IDENT QUOTED_VALUE )
                {
                    // BELScript_v1.g:127:9: ^( NSDEF OBJECT_IDENT QUOTED_VALUE )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(NSDEF, "NSDEF")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1,
                                stream_QUOTED_VALUE.nextNode()
                                );

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_namespace"

    public static class define_anno_expr_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_anno_expr"
    // BELScript_v1.g:130:1: define_anno_expr : KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )* ;
    public final BELScript_v1Parser.define_anno_expr_return define_anno_expr()
            throws RecognitionException {
        BELScript_v1Parser.define_anno_expr_return retval = new BELScript_v1Parser.define_anno_expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KWRD_DEFINE59 = null;
        Token WS60 = null;
        Token KWRD_ANNO61 = null;
        Token WS62 = null;

        Object KWRD_DEFINE59_tree = null;
        Object WS60_tree = null;
        Object KWRD_ANNO61_tree = null;
        Object WS62_tree = null;

        try {
            // BELScript_v1.g:131:5: ( KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )* )
            // BELScript_v1.g:131:9: KWRD_DEFINE ( WS )* KWRD_ANNO ( WS )*
            {
                root_0 = (Object) adaptor.nil();

                KWRD_DEFINE59 = (Token) match(input, KWRD_DEFINE,
                        FOLLOW_KWRD_DEFINE_in_define_anno_expr983);
                KWRD_DEFINE59_tree =
                        (Object) adaptor.create(KWRD_DEFINE59);
                adaptor.addChild(root_0, KWRD_DEFINE59_tree);

                // BELScript_v1.g:131:21: ( WS )*
                loop11: do {
                    int alt11 = 2;
                    int LA11_0 = input.LA(1);

                    if ((LA11_0 == WS)) {
                        alt11 = 1;
                    }

                    switch (alt11) {
                    case 1:
                    // BELScript_v1.g:131:21: WS
                    {
                        WS60 = (Token) match(input, WS,
                                FOLLOW_WS_in_define_anno_expr985);
                        WS60_tree =
                                (Object) adaptor.create(WS60);
                        adaptor.addChild(root_0, WS60_tree);

                    }
                        break;

                    default:
                        break loop11;
                    }
                } while (true);

                KWRD_ANNO61 = (Token) match(input, KWRD_ANNO,
                        FOLLOW_KWRD_ANNO_in_define_anno_expr988);
                KWRD_ANNO61_tree =
                        (Object) adaptor.create(KWRD_ANNO61);
                adaptor.addChild(root_0, KWRD_ANNO61_tree);

                // BELScript_v1.g:131:35: ( WS )*
                loop12: do {
                    int alt12 = 2;
                    int LA12_0 = input.LA(1);

                    if ((LA12_0 == WS)) {
                        alt12 = 1;
                    }

                    switch (alt12) {
                    case 1:
                    // BELScript_v1.g:131:35: WS
                    {
                        WS62 = (Token) match(input, WS,
                                FOLLOW_WS_in_define_anno_expr990);
                        WS62_tree =
                                (Object) adaptor.create(WS62);
                        adaptor.addChild(root_0, WS62_tree);

                    }
                        break;

                    default:
                        break loop12;
                    }
                } while (true);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_anno_expr"

    public static class define_annotation_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "define_annotation"
    // BELScript_v1.g:134:1: define_annotation : ( define_anno_expr OBJECT_IDENT KWRD_AS KWRD_LIST val= VALUE_LIST -> ^( ANNO_DEF_LIST OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_URL val= QUOTED_VALUE -> ^( ANNO_DEF_URL OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_PATTERN val= QUOTED_VALUE -> ^( ANNO_DEF_PTRN OBJECT_IDENT $val) );
    public final BELScript_v1Parser.define_annotation_return define_annotation()
            throws RecognitionException {
        BELScript_v1Parser.define_annotation_return retval = new BELScript_v1Parser.define_annotation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val = null;
        Token OBJECT_IDENT64 = null;
        Token KWRD_AS65 = null;
        Token KWRD_LIST66 = null;
        Token OBJECT_IDENT68 = null;
        Token KWRD_AS69 = null;
        Token KWRD_URL70 = null;
        Token OBJECT_IDENT72 = null;
        Token KWRD_AS73 = null;
        Token KWRD_PATTERN74 = null;
        BELScript_v1Parser.define_anno_expr_return define_anno_expr63 = null;

        BELScript_v1Parser.define_anno_expr_return define_anno_expr67 = null;

        BELScript_v1Parser.define_anno_expr_return define_anno_expr71 = null;

        Object val_tree = null;
        Object OBJECT_IDENT64_tree = null;
        Object KWRD_AS65_tree = null;
        Object KWRD_LIST66_tree = null;
        Object OBJECT_IDENT68_tree = null;
        Object KWRD_AS69_tree = null;
        Object KWRD_URL70_tree = null;
        Object OBJECT_IDENT72_tree = null;
        Object KWRD_AS73_tree = null;
        Object KWRD_PATTERN74_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_KWRD_LIST = new RewriteRuleTokenStream(
                adaptor, "token KWRD_LIST");
        RewriteRuleTokenStream stream_KWRD_PATTERN = new RewriteRuleTokenStream(
                adaptor, "token KWRD_PATTERN");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleTokenStream stream_KWRD_URL = new RewriteRuleTokenStream(
                adaptor, "token KWRD_URL");
        RewriteRuleTokenStream stream_KWRD_AS = new RewriteRuleTokenStream(
                adaptor, "token KWRD_AS");
        RewriteRuleTokenStream stream_VALUE_LIST = new RewriteRuleTokenStream(
                adaptor, "token VALUE_LIST");
        RewriteRuleSubtreeStream stream_define_anno_expr = new RewriteRuleSubtreeStream(
                adaptor, "rule define_anno_expr");
        try {
            // BELScript_v1.g:135:5: ( define_anno_expr OBJECT_IDENT KWRD_AS KWRD_LIST val= VALUE_LIST -> ^( ANNO_DEF_LIST OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_URL val= QUOTED_VALUE -> ^( ANNO_DEF_URL OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_PATTERN val= QUOTED_VALUE -> ^( ANNO_DEF_PTRN OBJECT_IDENT $val) )
            int alt13 = 3;
            alt13 = dfa13.predict(input);
            switch (alt13) {
            case 1:
            // BELScript_v1.g:135:9: define_anno_expr OBJECT_IDENT KWRD_AS KWRD_LIST val= VALUE_LIST
            {
                pushFollow(FOLLOW_define_anno_expr_in_define_annotation1010);
                define_anno_expr63 = define_anno_expr();

                state._fsp--;

                stream_define_anno_expr.add(define_anno_expr63.getTree());

                OBJECT_IDENT64 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_define_annotation1012);
                stream_OBJECT_IDENT.add(OBJECT_IDENT64);

                KWRD_AS65 = (Token) match(input, KWRD_AS,
                        FOLLOW_KWRD_AS_in_define_annotation1014);
                stream_KWRD_AS.add(KWRD_AS65);

                KWRD_LIST66 = (Token) match(input, KWRD_LIST,
                        FOLLOW_KWRD_LIST_in_define_annotation1016);
                stream_KWRD_LIST.add(KWRD_LIST66);

                val = (Token) match(input, VALUE_LIST,
                        FOLLOW_VALUE_LIST_in_define_annotation1020);
                stream_VALUE_LIST.add(val);

                // AST REWRITE
                // elements: OBJECT_IDENT, val
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 135:72: -> ^( ANNO_DEF_LIST OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:136:9: ^( ANNO_DEF_LIST OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_DEF_LIST,
                                        "ANNO_DEF_LIST")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:137:9: define_anno_expr OBJECT_IDENT KWRD_AS KWRD_URL val= QUOTED_VALUE
            {
                pushFollow(FOLLOW_define_anno_expr_in_define_annotation1049);
                define_anno_expr67 = define_anno_expr();

                state._fsp--;

                stream_define_anno_expr.add(define_anno_expr67.getTree());

                OBJECT_IDENT68 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_define_annotation1051);
                stream_OBJECT_IDENT.add(OBJECT_IDENT68);

                KWRD_AS69 = (Token) match(input, KWRD_AS,
                        FOLLOW_KWRD_AS_in_define_annotation1053);
                stream_KWRD_AS.add(KWRD_AS69);

                KWRD_URL70 = (Token) match(input, KWRD_URL,
                        FOLLOW_KWRD_URL_in_define_annotation1055);
                stream_KWRD_URL.add(KWRD_URL70);

                val = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_define_annotation1059);
                stream_QUOTED_VALUE.add(val);

                // AST REWRITE
                // elements: val, OBJECT_IDENT
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 137:73: -> ^( ANNO_DEF_URL OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:138:9: ^( ANNO_DEF_URL OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_DEF_URL,
                                        "ANNO_DEF_URL")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 3:
            // BELScript_v1.g:139:9: define_anno_expr OBJECT_IDENT KWRD_AS KWRD_PATTERN val= QUOTED_VALUE
            {
                pushFollow(FOLLOW_define_anno_expr_in_define_annotation1088);
                define_anno_expr71 = define_anno_expr();

                state._fsp--;

                stream_define_anno_expr.add(define_anno_expr71.getTree());

                OBJECT_IDENT72 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_define_annotation1090);
                stream_OBJECT_IDENT.add(OBJECT_IDENT72);

                KWRD_AS73 = (Token) match(input, KWRD_AS,
                        FOLLOW_KWRD_AS_in_define_annotation1092);
                stream_KWRD_AS.add(KWRD_AS73);

                KWRD_PATTERN74 = (Token) match(input, KWRD_PATTERN,
                        FOLLOW_KWRD_PATTERN_in_define_annotation1094);
                stream_KWRD_PATTERN.add(KWRD_PATTERN74);

                val = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_define_annotation1098);
                stream_QUOTED_VALUE.add(val);

                // AST REWRITE
                // elements: val, OBJECT_IDENT
                // token labels: val
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_val = new RewriteRuleTokenStream(
                        adaptor, "token val", val);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 139:77: -> ^( ANNO_DEF_PTRN OBJECT_IDENT $val)
                {
                    // BELScript_v1.g:140:9: ^( ANNO_DEF_PTRN OBJECT_IDENT $val)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(ANNO_DEF_PTRN,
                                        "ANNO_DEF_PTRN")
                                , root_1);

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_1, stream_val.nextNode());

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "define_annotation"

    public static class document_property_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "document_property"
    // BELScript_v1.g:143:1: document_property : ( KWRD_AUTHORS | KWRD_CONTACTINFO | KWRD_COPYRIGHT | KWRD_DESC | KWRD_DISCLAIMER | KWRD_LICENSES | KWRD_NAME | KWRD_VERSION );
    public final BELScript_v1Parser.document_property_return document_property()
            throws RecognitionException {
        BELScript_v1Parser.document_property_return retval = new BELScript_v1Parser.document_property_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set75 = null;

        Object set75_tree = null;

        try {
            // BELScript_v1.g:144:5: ( KWRD_AUTHORS | KWRD_CONTACTINFO | KWRD_COPYRIGHT | KWRD_DESC | KWRD_DISCLAIMER | KWRD_LICENSES | KWRD_NAME | KWRD_VERSION )
            // BELScript_v1.g:
            {
                root_0 = (Object) adaptor.nil();

                set75 = (Token) input.LT(1);

                if ((input.LA(1) >= KWRD_AUTHORS && input.LA(1) <= KWRD_COPYRIGHT)
                        || input.LA(1) == KWRD_DESC
                        || input.LA(1) == KWRD_DISCLAIMER
                        || input.LA(1) == KWRD_LICENSES
                        || input.LA(1) == KWRD_NAME
                        || input.LA(1) == KWRD_VERSION) {
                    input.consume();
                    adaptor.addChild(root_0,
                            (Object) adaptor.create(set75)
                            );
                    state.errorRecovery = false;
                }
                else {
                    MismatchedSetException mse = new MismatchedSetException(
                            null, input);
                    throw mse;
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "document_property"

    public static class argument_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "argument"
    // BELScript_v1.g:154:1: argument : ( ( COMMA )? term -> term | ( COMMA )? param -> param );
    public final BELScript_v1Parser.argument_return argument()
            throws RecognitionException {
        BELScript_v1Parser.argument_return retval = new BELScript_v1Parser.argument_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA76 = null;
        Token COMMA78 = null;
        BELScript_v1Parser.term_return term77 = null;

        BELScript_v1Parser.param_return param79 = null;

        Object COMMA76_tree = null;
        Object COMMA78_tree = null;
        RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(
                adaptor, "token COMMA");
        RewriteRuleSubtreeStream stream_param = new RewriteRuleSubtreeStream(
                adaptor, "rule param");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(
                adaptor, "rule term");
        try {
            // BELScript_v1.g:155:5: ( ( COMMA )? term -> term | ( COMMA )? param -> param )
            int alt16 = 2;
            switch (input.LA(1)) {
            case COMMA: {
                int LA16_1 = input.LA(2);

                if (((LA16_1 >= 71 && LA16_1 <= 73) || LA16_1 == 76
                        || (LA16_1 >= 78 && LA16_1 <= 80)
                        || (LA16_1 >= 82 && LA16_1 <= 89)
                        || (LA16_1 >= 91 && LA16_1 <= 92)
                        || (LA16_1 >= 95 && LA16_1 <= 100)
                        || (LA16_1 >= 107 && LA16_1 <= 112)
                        || (LA16_1 >= 115 && LA16_1 <= 122) || LA16_1 == 124
                        || (LA16_1 >= 126 && LA16_1 <= 128)
                        || (LA16_1 >= 130 && LA16_1 <= 137)
                        || (LA16_1 >= 139 && LA16_1 <= 142) || LA16_1 == 144 || (LA16_1 >= 146 && LA16_1 <= 150))) {
                    alt16 = 1;
                }
                else if ((LA16_1 == OBJECT_IDENT || LA16_1 == QUOTED_VALUE)) {
                    alt16 = 2;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 16, 1, input);

                    throw nvae;

                }
            }
                break;
            case 71:
            case 72:
            case 73:
            case 76:
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 91:
            case 92:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 124:
            case 126:
            case 127:
            case 128:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 139:
            case 140:
            case 141:
            case 142:
            case 144:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150: {
                alt16 = 1;
            }
                break;
            case OBJECT_IDENT:
            case QUOTED_VALUE: {
                alt16 = 2;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
            case 1:
            // BELScript_v1.g:155:9: ( COMMA )? term
            {
                // BELScript_v1.g:155:9: ( COMMA )?
                int alt14 = 2;
                int LA14_0 = input.LA(1);

                if ((LA14_0 == COMMA)) {
                    alt14 = 1;
                }
                switch (alt14) {
                case 1:
                // BELScript_v1.g:155:9: COMMA
                {
                    COMMA76 = (Token) match(input, COMMA,
                            FOLLOW_COMMA_in_argument1225);
                    stream_COMMA.add(COMMA76);

                }
                    break;

                }

                pushFollow(FOLLOW_term_in_argument1228);
                term77 = term();

                state._fsp--;

                stream_term.add(term77.getTree());

                // AST REWRITE
                // elements: term
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 155:21: -> term
                {
                    adaptor.addChild(root_0, stream_term.nextTree());

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:156:9: ( COMMA )? param
            {
                // BELScript_v1.g:156:9: ( COMMA )?
                int alt15 = 2;
                int LA15_0 = input.LA(1);

                if ((LA15_0 == COMMA)) {
                    alt15 = 1;
                }
                switch (alt15) {
                case 1:
                // BELScript_v1.g:156:9: COMMA
                {
                    COMMA78 = (Token) match(input, COMMA,
                            FOLLOW_COMMA_in_argument1242);
                    stream_COMMA.add(COMMA78);

                }
                    break;

                }

                pushFollow(FOLLOW_param_in_argument1245);
                param79 = param();

                state._fsp--;

                stream_param.add(param79.getTree());

                // AST REWRITE
                // elements: param
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 156:22: -> param
                {
                    adaptor.addChild(root_0, stream_param.nextTree());

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "argument"

    public static class term_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "term"
    // BELScript_v1.g:159:1: term : function LP ( argument )* RP -> ^( TERMDEF function ( argument )* ) ;
    public final BELScript_v1Parser.term_return term()
            throws RecognitionException {
        BELScript_v1Parser.term_return retval = new BELScript_v1Parser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LP81 = null;
        Token RP83 = null;
        BELScript_v1Parser.function_return function80 = null;

        BELScript_v1Parser.argument_return argument82 = null;

        Object LP81_tree = null;
        Object RP83_tree = null;
        RewriteRuleTokenStream stream_RP = new RewriteRuleTokenStream(adaptor,
                "token RP");
        RewriteRuleTokenStream stream_LP = new RewriteRuleTokenStream(adaptor,
                "token LP");
        RewriteRuleSubtreeStream stream_argument = new RewriteRuleSubtreeStream(
                adaptor, "rule argument");
        RewriteRuleSubtreeStream stream_function = new RewriteRuleSubtreeStream(
                adaptor, "rule function");
        try {
            // BELScript_v1.g:160:5: ( function LP ( argument )* RP -> ^( TERMDEF function ( argument )* ) )
            // BELScript_v1.g:160:9: function LP ( argument )* RP
            {
                pushFollow(FOLLOW_function_in_term1268);
                function80 = function();

                state._fsp--;

                stream_function.add(function80.getTree());

                LP81 = (Token) match(input, LP, FOLLOW_LP_in_term1270);
                stream_LP.add(LP81);

                // BELScript_v1.g:160:21: ( argument )*
                loop17: do {
                    int alt17 = 2;
                    int LA17_0 = input.LA(1);

                    if ((LA17_0 == COMMA || LA17_0 == OBJECT_IDENT
                            || LA17_0 == QUOTED_VALUE
                            || (LA17_0 >= 71 && LA17_0 <= 73) || LA17_0 == 76
                            || (LA17_0 >= 78 && LA17_0 <= 80)
                            || (LA17_0 >= 82 && LA17_0 <= 89)
                            || (LA17_0 >= 91 && LA17_0 <= 92)
                            || (LA17_0 >= 95 && LA17_0 <= 100)
                            || (LA17_0 >= 107 && LA17_0 <= 112)
                            || (LA17_0 >= 115 && LA17_0 <= 122)
                            || LA17_0 == 124
                            || (LA17_0 >= 126 && LA17_0 <= 128)
                            || (LA17_0 >= 130 && LA17_0 <= 137)
                            || (LA17_0 >= 139 && LA17_0 <= 142)
                            || LA17_0 == 144 || (LA17_0 >= 146 && LA17_0 <= 150))) {
                        alt17 = 1;
                    }

                    switch (alt17) {
                    case 1:
                    // BELScript_v1.g:160:22: argument
                    {
                        pushFollow(FOLLOW_argument_in_term1273);
                        argument82 = argument();

                        state._fsp--;

                        stream_argument.add(argument82.getTree());

                    }
                        break;

                    default:
                        break loop17;
                    }
                } while (true);

                RP83 = (Token) match(input, RP, FOLLOW_RP_in_term1277);
                stream_RP.add(RP83);

                // AST REWRITE
                // elements: function, argument
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 160:36: -> ^( TERMDEF function ( argument )* )
                {
                    // BELScript_v1.g:161:9: ^( TERMDEF function ( argument )* )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(TERMDEF, "TERMDEF")
                                , root_1);

                        adaptor.addChild(root_1, stream_function.nextTree());

                        // BELScript_v1.g:161:28: ( argument )*
                        while (stream_argument.hasNext()) {
                            adaptor.addChild(root_1, stream_argument.nextTree());

                        }
                        stream_argument.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "term"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "statement"
    // BELScript_v1.g:166:1: statement : subject= term (rel= relationship ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term ) )? (comment= STATEMENT_COMMENT )? -> ^( STMTDEF ( $comment)? $subject ( $rel)? ( $obj)? ( $obj_sub)? ( $obj_rel)? ( $obj_obj)? ) ;
    public final BELScript_v1Parser.statement_return statement()
            throws RecognitionException {
        BELScript_v1Parser.statement_return retval = new BELScript_v1Parser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token comment = null;
        Token LP84 = null;
        Token RP85 = null;
        BELScript_v1Parser.term_return subject = null;

        BELScript_v1Parser.relationship_return rel = null;

        BELScript_v1Parser.term_return obj_sub = null;

        BELScript_v1Parser.relationship_return obj_rel = null;

        BELScript_v1Parser.term_return obj_obj = null;

        BELScript_v1Parser.term_return obj = null;

        Object comment_tree = null;
        Object LP84_tree = null;
        Object RP85_tree = null;
        RewriteRuleTokenStream stream_RP = new RewriteRuleTokenStream(adaptor,
                "token RP");
        RewriteRuleTokenStream stream_STATEMENT_COMMENT = new RewriteRuleTokenStream(
                adaptor, "token STATEMENT_COMMENT");
        RewriteRuleTokenStream stream_LP = new RewriteRuleTokenStream(adaptor,
                "token LP");
        RewriteRuleSubtreeStream stream_relationship = new RewriteRuleSubtreeStream(
                adaptor, "rule relationship");
        RewriteRuleSubtreeStream stream_term = new RewriteRuleSubtreeStream(
                adaptor, "rule term");
        try {
            // BELScript_v1.g:167:5: (subject= term (rel= relationship ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term ) )? (comment= STATEMENT_COMMENT )? -> ^( STMTDEF ( $comment)? $subject ( $rel)? ( $obj)? ( $obj_sub)? ( $obj_rel)? ( $obj_obj)? ) )
            // BELScript_v1.g:167:9: subject= term (rel= relationship ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term ) )? (comment= STATEMENT_COMMENT )?
            {
                pushFollow(FOLLOW_term_in_statement1319);
                subject = term();

                state._fsp--;

                stream_term.add(subject.getTree());

                // BELScript_v1.g:167:22: (rel= relationship ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term ) )?
                int alt19 = 2;
                int LA19_0 = input.LA(1);

                if (((LA19_0 >= 64 && LA19_0 <= 70)
                        || (LA19_0 >= 74 && LA19_0 <= 75) || LA19_0 == 77
                        || LA19_0 == 81 || LA19_0 == 90
                        || (LA19_0 >= 93 && LA19_0 <= 94)
                        || (LA19_0 >= 101 && LA19_0 <= 106)
                        || (LA19_0 >= 113 && LA19_0 <= 114) || LA19_0 == 123
                        || LA19_0 == 125 || LA19_0 == 129 || LA19_0 == 138
                        || LA19_0 == 143 || LA19_0 == 145)) {
                    alt19 = 1;
                }
                switch (alt19) {
                case 1:
                // BELScript_v1.g:167:23: rel= relationship ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term )
                {
                    pushFollow(FOLLOW_relationship_in_statement1324);
                    rel = relationship();

                    state._fsp--;

                    stream_relationship.add(rel.getTree());

                    // BELScript_v1.g:167:40: ( LP obj_sub= term obj_rel= relationship obj_obj= term RP |obj= term )
                    int alt18 = 2;
                    int LA18_0 = input.LA(1);

                    if ((LA18_0 == LP)) {
                        alt18 = 1;
                    }
                    else if (((LA18_0 >= 71 && LA18_0 <= 73) || LA18_0 == 76
                            || (LA18_0 >= 78 && LA18_0 <= 80)
                            || (LA18_0 >= 82 && LA18_0 <= 89)
                            || (LA18_0 >= 91 && LA18_0 <= 92)
                            || (LA18_0 >= 95 && LA18_0 <= 100)
                            || (LA18_0 >= 107 && LA18_0 <= 112)
                            || (LA18_0 >= 115 && LA18_0 <= 122)
                            || LA18_0 == 124
                            || (LA18_0 >= 126 && LA18_0 <= 128)
                            || (LA18_0 >= 130 && LA18_0 <= 137)
                            || (LA18_0 >= 139 && LA18_0 <= 142)
                            || LA18_0 == 144 || (LA18_0 >= 146 && LA18_0 <= 150))) {
                        alt18 = 2;
                    }
                    else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 18, 0, input);

                        throw nvae;

                    }
                    switch (alt18) {
                    case 1:
                    // BELScript_v1.g:167:41: LP obj_sub= term obj_rel= relationship obj_obj= term RP
                    {
                        LP84 = (Token) match(input, LP,
                                FOLLOW_LP_in_statement1327);
                        stream_LP.add(LP84);

                        pushFollow(FOLLOW_term_in_statement1331);
                        obj_sub = term();

                        state._fsp--;

                        stream_term.add(obj_sub.getTree());

                        pushFollow(FOLLOW_relationship_in_statement1335);
                        obj_rel = relationship();

                        state._fsp--;

                        stream_relationship.add(obj_rel.getTree());

                        pushFollow(FOLLOW_term_in_statement1339);
                        obj_obj = term();

                        state._fsp--;

                        stream_term.add(obj_obj.getTree());

                        RP85 = (Token) match(input, RP,
                                FOLLOW_RP_in_statement1341);
                        stream_RP.add(RP85);

                    }
                        break;
                    case 2:
                    // BELScript_v1.g:167:96: obj= term
                    {
                        pushFollow(FOLLOW_term_in_statement1347);
                        obj = term();

                        state._fsp--;

                        stream_term.add(obj.getTree());

                    }
                        break;

                    }

                }
                    break;

                }

                // BELScript_v1.g:167:115: (comment= STATEMENT_COMMENT )?
                int alt20 = 2;
                int LA20_0 = input.LA(1);

                if ((LA20_0 == STATEMENT_COMMENT)) {
                    alt20 = 1;
                }
                switch (alt20) {
                case 1:
                // BELScript_v1.g:167:115: comment= STATEMENT_COMMENT
                {
                    comment = (Token) match(input, STATEMENT_COMMENT,
                            FOLLOW_STATEMENT_COMMENT_in_statement1354);
                    stream_STATEMENT_COMMENT.add(comment);

                }
                    break;

                }

                // AST REWRITE
                // elements: obj_obj, obj_rel, obj, comment, obj_sub, subject, rel
                // token labels: comment
                // rule labels: obj_obj, retval, obj_sub, subject, obj, rel, obj_rel
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleTokenStream stream_comment = new RewriteRuleTokenStream(
                        adaptor, "token comment", comment);
                RewriteRuleSubtreeStream stream_obj_obj = new RewriteRuleSubtreeStream(
                        adaptor, "rule obj_obj", obj_obj != null ? obj_obj.tree
                                : null);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);
                RewriteRuleSubtreeStream stream_obj_sub = new RewriteRuleSubtreeStream(
                        adaptor, "rule obj_sub", obj_sub != null ? obj_sub.tree
                                : null);
                RewriteRuleSubtreeStream stream_subject = new RewriteRuleSubtreeStream(
                        adaptor, "rule subject", subject != null ? subject.tree
                                : null);
                RewriteRuleSubtreeStream stream_obj = new RewriteRuleSubtreeStream(
                        adaptor, "rule obj", obj != null ? obj.tree : null);
                RewriteRuleSubtreeStream stream_rel = new RewriteRuleSubtreeStream(
                        adaptor, "rule rel", rel != null ? rel.tree : null);
                RewriteRuleSubtreeStream stream_obj_rel = new RewriteRuleSubtreeStream(
                        adaptor, "rule obj_rel", obj_rel != null ? obj_rel.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 167:135: -> ^( STMTDEF ( $comment)? $subject ( $rel)? ( $obj)? ( $obj_sub)? ( $obj_rel)? ( $obj_obj)? )
                {
                    // BELScript_v1.g:168:9: ^( STMTDEF ( $comment)? $subject ( $rel)? ( $obj)? ( $obj_sub)? ( $obj_rel)? ( $obj_obj)? )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(STMTDEF, "STMTDEF")
                                , root_1);

                        // BELScript_v1.g:168:20: ( $comment)?
                        if (stream_comment.hasNext()) {
                            adaptor.addChild(root_1, stream_comment.nextNode());

                        }
                        stream_comment.reset();

                        adaptor.addChild(root_1, stream_subject.nextTree());

                        // BELScript_v1.g:168:39: ( $rel)?
                        if (stream_rel.hasNext()) {
                            adaptor.addChild(root_1, stream_rel.nextTree());

                        }
                        stream_rel.reset();

                        // BELScript_v1.g:168:45: ( $obj)?
                        if (stream_obj.hasNext()) {
                            adaptor.addChild(root_1, stream_obj.nextTree());

                        }
                        stream_obj.reset();

                        // BELScript_v1.g:168:51: ( $obj_sub)?
                        if (stream_obj_sub.hasNext()) {
                            adaptor.addChild(root_1, stream_obj_sub.nextTree());

                        }
                        stream_obj_sub.reset();

                        // BELScript_v1.g:168:61: ( $obj_rel)?
                        if (stream_obj_rel.hasNext()) {
                            adaptor.addChild(root_1, stream_obj_rel.nextTree());

                        }
                        stream_obj_rel.reset();

                        // BELScript_v1.g:168:71: ( $obj_obj)?
                        if (stream_obj_obj.hasNext()) {
                            adaptor.addChild(root_1, stream_obj_obj.nextTree());

                        }
                        stream_obj_obj.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "statement"

    public static class ns_prefix_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "ns_prefix"
    // BELScript_v1.g:171:1: ns_prefix : OBJECT_IDENT COLON !;
    public final BELScript_v1Parser.ns_prefix_return ns_prefix()
            throws RecognitionException {
        BELScript_v1Parser.ns_prefix_return retval = new BELScript_v1Parser.ns_prefix_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OBJECT_IDENT86 = null;
        Token COLON87 = null;

        Object OBJECT_IDENT86_tree = null;
        Object COLON87_tree = null;

        try {
            // BELScript_v1.g:172:5: ( OBJECT_IDENT COLON !)
            // BELScript_v1.g:172:9: OBJECT_IDENT COLON !
            {
                root_0 = (Object) adaptor.nil();

                OBJECT_IDENT86 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_ns_prefix1415);
                OBJECT_IDENT86_tree =
                        (Object) adaptor.create(OBJECT_IDENT86);
                adaptor.addChild(root_0, OBJECT_IDENT86_tree);

                COLON87 = (Token) match(input, COLON,
                        FOLLOW_COLON_in_ns_prefix1417);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "ns_prefix"

    public static class param_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "param"
    // BELScript_v1.g:175:1: param : ( ( ns_prefix )? OBJECT_IDENT -> ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT ) | ( ns_prefix )? QUOTED_VALUE -> ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE ) );
    public final BELScript_v1Parser.param_return param()
            throws RecognitionException {
        BELScript_v1Parser.param_return retval = new BELScript_v1Parser.param_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OBJECT_IDENT89 = null;
        Token QUOTED_VALUE91 = null;
        BELScript_v1Parser.ns_prefix_return ns_prefix88 = null;

        BELScript_v1Parser.ns_prefix_return ns_prefix90 = null;

        Object OBJECT_IDENT89_tree = null;
        Object QUOTED_VALUE91_tree = null;
        RewriteRuleTokenStream stream_OBJECT_IDENT = new RewriteRuleTokenStream(
                adaptor, "token OBJECT_IDENT");
        RewriteRuleTokenStream stream_QUOTED_VALUE = new RewriteRuleTokenStream(
                adaptor, "token QUOTED_VALUE");
        RewriteRuleSubtreeStream stream_ns_prefix = new RewriteRuleSubtreeStream(
                adaptor, "rule ns_prefix");
        try {
            // BELScript_v1.g:176:5: ( ( ns_prefix )? OBJECT_IDENT -> ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT ) | ( ns_prefix )? QUOTED_VALUE -> ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE ) )
            int alt23 = 2;
            int LA23_0 = input.LA(1);

            if ((LA23_0 == OBJECT_IDENT)) {
                int LA23_1 = input.LA(2);

                if ((LA23_1 == COLON)) {
                    int LA23_3 = input.LA(3);

                    if ((LA23_3 == OBJECT_IDENT)) {
                        alt23 = 1;
                    }
                    else if ((LA23_3 == QUOTED_VALUE)) {
                        alt23 = 2;
                    }
                    else {
                        NoViableAltException nvae =
                                new NoViableAltException("", 23, 3, input);

                        throw nvae;

                    }
                }
                else if ((LA23_1 == COMMA || LA23_1 == OBJECT_IDENT
                        || (LA23_1 >= QUOTED_VALUE && LA23_1 <= RP)
                        || (LA23_1 >= 71 && LA23_1 <= 73) || LA23_1 == 76
                        || (LA23_1 >= 78 && LA23_1 <= 80)
                        || (LA23_1 >= 82 && LA23_1 <= 89)
                        || (LA23_1 >= 91 && LA23_1 <= 92)
                        || (LA23_1 >= 95 && LA23_1 <= 100)
                        || (LA23_1 >= 107 && LA23_1 <= 112)
                        || (LA23_1 >= 115 && LA23_1 <= 122) || LA23_1 == 124
                        || (LA23_1 >= 126 && LA23_1 <= 128)
                        || (LA23_1 >= 130 && LA23_1 <= 137)
                        || (LA23_1 >= 139 && LA23_1 <= 142) || LA23_1 == 144 || (LA23_1 >= 146 && LA23_1 <= 150))) {
                    alt23 = 1;
                }
                else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }
            }
            else if ((LA23_0 == QUOTED_VALUE)) {
                alt23 = 2;
            }
            else {
                NoViableAltException nvae =
                        new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
            case 1:
            // BELScript_v1.g:176:9: ( ns_prefix )? OBJECT_IDENT
            {
                // BELScript_v1.g:176:9: ( ns_prefix )?
                int alt21 = 2;
                int LA21_0 = input.LA(1);

                if ((LA21_0 == OBJECT_IDENT)) {
                    int LA21_1 = input.LA(2);

                    if ((LA21_1 == COLON)) {
                        alt21 = 1;
                    }
                }
                switch (alt21) {
                case 1:
                // BELScript_v1.g:176:9: ns_prefix
                {
                    pushFollow(FOLLOW_ns_prefix_in_param1437);
                    ns_prefix88 = ns_prefix();

                    state._fsp--;

                    stream_ns_prefix.add(ns_prefix88.getTree());

                }
                    break;

                }

                OBJECT_IDENT89 = (Token) match(input, OBJECT_IDENT,
                        FOLLOW_OBJECT_IDENT_in_param1440);
                stream_OBJECT_IDENT.add(OBJECT_IDENT89);

                // AST REWRITE
                // elements: ns_prefix, OBJECT_IDENT
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 176:33: -> ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT )
                {
                    // BELScript_v1.g:176:36: ^( PARAM_DEF_ID ( ns_prefix )? OBJECT_IDENT )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(PARAM_DEF_ID,
                                        "PARAM_DEF_ID")
                                , root_1);

                        // BELScript_v1.g:176:51: ( ns_prefix )?
                        if (stream_ns_prefix.hasNext()) {
                            adaptor.addChild(root_1,
                                    stream_ns_prefix.nextTree());

                        }
                        stream_ns_prefix.reset();

                        adaptor.addChild(root_1,
                                stream_OBJECT_IDENT.nextNode()
                                );

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;
            case 2:
            // BELScript_v1.g:177:9: ( ns_prefix )? QUOTED_VALUE
            {
                // BELScript_v1.g:177:9: ( ns_prefix )?
                int alt22 = 2;
                int LA22_0 = input.LA(1);

                if ((LA22_0 == OBJECT_IDENT)) {
                    alt22 = 1;
                }
                switch (alt22) {
                case 1:
                // BELScript_v1.g:177:9: ns_prefix
                {
                    pushFollow(FOLLOW_ns_prefix_in_param1461);
                    ns_prefix90 = ns_prefix();

                    state._fsp--;

                    stream_ns_prefix.add(ns_prefix90.getTree());

                }
                    break;

                }

                QUOTED_VALUE91 = (Token) match(input, QUOTED_VALUE,
                        FOLLOW_QUOTED_VALUE_in_param1464);
                stream_QUOTED_VALUE.add(QUOTED_VALUE91);

                // AST REWRITE
                // elements: QUOTED_VALUE, ns_prefix
                // token labels: 
                // rule labels: retval
                // token list labels: 
                // rule list labels: 
                // wildcard labels: 
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(
                        adaptor, "rule retval", retval != null ? retval.tree
                                : null);

                root_0 = (Object) adaptor.nil();
                // 177:33: -> ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE )
                {
                    // BELScript_v1.g:177:36: ^( PARAM_DEF_QV ( ns_prefix )? QUOTED_VALUE )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot(
                                (Object) adaptor.create(PARAM_DEF_QV,
                                        "PARAM_DEF_QV")
                                , root_1);

                        // BELScript_v1.g:177:51: ( ns_prefix )?
                        if (stream_ns_prefix.hasNext()) {
                            adaptor.addChild(root_1,
                                    stream_ns_prefix.nextTree());

                        }
                        stream_ns_prefix.reset();

                        adaptor.addChild(root_1,
                                stream_QUOTED_VALUE.nextNode()
                                );

                        adaptor.addChild(root_0, root_1);
                    }

                }

                retval.tree = root_0;

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "param"

    public static class function_return extends ParserRuleReturnScope {
        public String r;
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "function"
    // BELScript_v1.g:180:1: function returns [String r] : (fv= 'proteinAbundance' |fv= 'p' |fv= 'rnaAbundance' |fv= 'r' |fv= 'abundance' |fv= 'a' |fv= 'microRNAAbundance' |fv= 'm' |fv= 'geneAbundance' |fv= 'g' |fv= 'biologicalProcess' |fv= 'bp' |fv= 'pathology' |fv= 'path' |fv= 'complexAbundance' |fv= 'complex' |fv= 'translocation' |fv= 'tloc' |fv= 'cellSecretion' |fv= 'sec' |fv= 'cellSurfaceExpression' |fv= 'surf' |fv= 'reaction' |fv= 'rxn' |fv= 'compositeAbundance' |fv= 'composite' |fv= 'fusion' |fv= 'fus' |fv= 'degradation' |fv= 'deg' |fv= 'molecularActivity' |fv= 'act' |fv= 'catalyticActivity' |fv= 'cat' |fv= 'kinaseActivity' |fv= 'kin' |fv= 'phosphataseActivity' |fv= 'phos' |fv= 'peptidaseActivity' |fv= 'pep' |fv= 'ribosylationActivity' |fv= 'ribo' |fv= 'transcriptionalActivity' |fv= 'tscript' |fv= 'transportActivity' |fv= 'tport' |fv= 'gtpBoundActivity' |fv= 'gtp' |fv= 'chaperoneActivity' |fv= 'chap' |fv= 'proteinModification' |fv= 'pmod' |fv= 'substitution' |fv= 'sub' |fv= 'truncation' |fv= 'trunc' |fv= 'reactants' |fv= 'products' |fv= 'list' );
    public final BELScript_v1Parser.function_return function()
            throws RecognitionException {
        BELScript_v1Parser.function_return retval = new BELScript_v1Parser.function_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token fv = null;

        Object fv_tree = null;

        try {
            // BELScript_v1.g:181:5: (fv= 'proteinAbundance' |fv= 'p' |fv= 'rnaAbundance' |fv= 'r' |fv= 'abundance' |fv= 'a' |fv= 'microRNAAbundance' |fv= 'm' |fv= 'geneAbundance' |fv= 'g' |fv= 'biologicalProcess' |fv= 'bp' |fv= 'pathology' |fv= 'path' |fv= 'complexAbundance' |fv= 'complex' |fv= 'translocation' |fv= 'tloc' |fv= 'cellSecretion' |fv= 'sec' |fv= 'cellSurfaceExpression' |fv= 'surf' |fv= 'reaction' |fv= 'rxn' |fv= 'compositeAbundance' |fv= 'composite' |fv= 'fusion' |fv= 'fus' |fv= 'degradation' |fv= 'deg' |fv= 'molecularActivity' |fv= 'act' |fv= 'catalyticActivity' |fv= 'cat' |fv= 'kinaseActivity' |fv= 'kin' |fv= 'phosphataseActivity' |fv= 'phos' |fv= 'peptidaseActivity' |fv= 'pep' |fv= 'ribosylationActivity' |fv= 'ribo' |fv= 'transcriptionalActivity' |fv= 'tscript' |fv= 'transportActivity' |fv= 'tport' |fv= 'gtpBoundActivity' |fv= 'gtp' |fv= 'chaperoneActivity' |fv= 'chap' |fv= 'proteinModification' |fv= 'pmod' |fv= 'substitution' |fv= 'sub' |fv= 'truncation' |fv= 'trunc' |fv= 'reactants' |fv= 'products' |fv= 'list' )
            int alt24 = 59;
            switch (input.LA(1)) {
            case 126: {
                alt24 = 1;
            }
                break;
            case 115: {
                alt24 = 2;
            }
                break;
            case 134: {
                alt24 = 3;
            }
                break;
            case 128: {
                alt24 = 4;
            }
                break;
            case 72: {
                alt24 = 5;
            }
                break;
            case 71: {
                alt24 = 6;
            }
                break;
            case 111: {
                alt24 = 7;
            }
                break;
            case 110: {
                alt24 = 8;
            }
                break;
            case 98: {
                alt24 = 9;
            }
                break;
            case 97: {
                alt24 = 10;
            }
                break;
            case 76: {
                alt24 = 11;
            }
                break;
            case 78: {
                alt24 = 12;
            }
                break;
            case 117: {
                alt24 = 13;
            }
                break;
            case 116: {
                alt24 = 14;
            }
                break;
            case 87: {
                alt24 = 15;
            }
                break;
            case 86: {
                alt24 = 16;
            }
                break;
            case 146: {
                alt24 = 17;
            }
                break;
            case 141: {
                alt24 = 18;
            }
                break;
            case 82: {
                alt24 = 19;
            }
                break;
            case 136: {
                alt24 = 20;
            }
                break;
            case 83: {
                alt24 = 21;
            }
                break;
            case 140: {
                alt24 = 22;
            }
                break;
            case 131: {
                alt24 = 23;
            }
                break;
            case 135: {
                alt24 = 24;
            }
                break;
            case 89: {
                alt24 = 25;
            }
                break;
            case 88: {
                alt24 = 26;
            }
                break;
            case 96: {
                alt24 = 27;
            }
                break;
            case 95: {
                alt24 = 28;
            }
                break;
            case 92: {
                alt24 = 29;
            }
                break;
            case 91: {
                alt24 = 30;
            }
                break;
            case 112: {
                alt24 = 31;
            }
                break;
            case 73: {
                alt24 = 32;
            }
                break;
            case 80: {
                alt24 = 33;
            }
                break;
            case 79: {
                alt24 = 34;
            }
                break;
            case 108: {
                alt24 = 35;
            }
                break;
            case 107: {
                alt24 = 36;
            }
                break;
            case 121: {
                alt24 = 37;
            }
                break;
            case 120: {
                alt24 = 38;
            }
                break;
            case 119: {
                alt24 = 39;
            }
                break;
            case 118: {
                alt24 = 40;
            }
                break;
            case 133: {
                alt24 = 41;
            }
                break;
            case 132: {
                alt24 = 42;
            }
                break;
            case 144: {
                alt24 = 43;
            }
                break;
            case 150: {
                alt24 = 44;
            }
                break;
            case 147: {
                alt24 = 45;
            }
                break;
            case 142: {
                alt24 = 46;
            }
                break;
            case 100: {
                alt24 = 47;
            }
                break;
            case 99: {
                alt24 = 48;
            }
                break;
            case 85: {
                alt24 = 49;
            }
                break;
            case 84: {
                alt24 = 50;
            }
                break;
            case 127: {
                alt24 = 51;
            }
                break;
            case 122: {
                alt24 = 52;
            }
                break;
            case 139: {
                alt24 = 53;
            }
                break;
            case 137: {
                alt24 = 54;
            }
                break;
            case 149: {
                alt24 = 55;
            }
                break;
            case 148: {
                alt24 = 56;
            }
                break;
            case 130: {
                alt24 = 57;
            }
                break;
            case 124: {
                alt24 = 58;
            }
                break;
            case 109: {
                alt24 = 59;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
            case 1:
            // BELScript_v1.g:181:9: fv= 'proteinAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 126, FOLLOW_126_in_function1500);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "p";

            }
                break;
            case 2:
            // BELScript_v1.g:182:9: fv= 'p'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 115, FOLLOW_115_in_function1524);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "p";

            }
                break;
            case 3:
            // BELScript_v1.g:183:9: fv= 'rnaAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 134, FOLLOW_134_in_function1563);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "r";

            }
                break;
            case 4:
            // BELScript_v1.g:184:9: fv= 'r'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 128, FOLLOW_128_in_function1591);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "r";

            }
                break;
            case 5:
            // BELScript_v1.g:185:9: fv= 'abundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 72, FOLLOW_72_in_function1630);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "a";

            }
                break;
            case 6:
            // BELScript_v1.g:186:9: fv= 'a'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 71, FOLLOW_71_in_function1661);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "a";

            }
                break;
            case 7:
            // BELScript_v1.g:187:9: fv= 'microRNAAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 111, FOLLOW_111_in_function1700);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "m";

            }
                break;
            case 8:
            // BELScript_v1.g:188:9: fv= 'm'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 110, FOLLOW_110_in_function1723);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "m";

            }
                break;
            case 9:
            // BELScript_v1.g:189:9: fv= 'geneAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 98, FOLLOW_98_in_function1762);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "g";

            }
                break;
            case 10:
            // BELScript_v1.g:190:9: fv= 'g'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 97, FOLLOW_97_in_function1789);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "g";

            }
                break;
            case 11:
            // BELScript_v1.g:191:9: fv= 'biologicalProcess'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 76, FOLLOW_76_in_function1828);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "bp";

            }
                break;
            case 12:
            // BELScript_v1.g:192:9: fv= 'bp'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 78, FOLLOW_78_in_function1851);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "bp";

            }
                break;
            case 13:
            // BELScript_v1.g:193:9: fv= 'pathology'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 117, FOLLOW_117_in_function1889);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "path";

            }
                break;
            case 14:
            // BELScript_v1.g:194:9: fv= 'path'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 116, FOLLOW_116_in_function1920);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "path";

            }
                break;
            case 15:
            // BELScript_v1.g:195:9: fv= 'complexAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 87, FOLLOW_87_in_function1956);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "complex";

            }
                break;
            case 16:
            // BELScript_v1.g:196:9: fv= 'complex'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 86, FOLLOW_86_in_function1980);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "complex";

            }
                break;
            case 17:
            // BELScript_v1.g:197:9: fv= 'translocation'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 146, FOLLOW_146_in_function2013);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tloc";

            }
                break;
            case 18:
            // BELScript_v1.g:198:9: fv= 'tloc'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 141, FOLLOW_141_in_function2040);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tloc";

            }
                break;
            case 19:
            // BELScript_v1.g:199:9: fv= 'cellSecretion'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 82, FOLLOW_82_in_function2076);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "sec";

            }
                break;
            case 20:
            // BELScript_v1.g:200:9: fv= 'sec'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 136, FOLLOW_136_in_function2103);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "sec";

            }
                break;
            case 21:
            // BELScript_v1.g:201:9: fv= 'cellSurfaceExpression'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 83, FOLLOW_83_in_function2140);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "surf";

            }
                break;
            case 22:
            // BELScript_v1.g:202:9: fv= 'surf'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 140, FOLLOW_140_in_function2159);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "surf";

            }
                break;
            case 23:
            // BELScript_v1.g:203:9: fv= 'reaction'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 131, FOLLOW_131_in_function2195);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "rxn";

            }
                break;
            case 24:
            // BELScript_v1.g:204:9: fv= 'rxn'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 135, FOLLOW_135_in_function2227);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "rxn";

            }
                break;
            case 25:
            // BELScript_v1.g:205:9: fv= 'compositeAbundance'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 89, FOLLOW_89_in_function2264);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "composite";

            }
                break;
            case 26:
            // BELScript_v1.g:206:9: fv= 'composite'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 88, FOLLOW_88_in_function2286);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "composite";

            }
                break;
            case 27:
            // BELScript_v1.g:207:9: fv= 'fusion'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 96, FOLLOW_96_in_function2317);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "fus";

            }
                break;
            case 28:
            // BELScript_v1.g:208:9: fv= 'fus'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 95, FOLLOW_95_in_function2351);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "fus";

            }
                break;
            case 29:
            // BELScript_v1.g:209:9: fv= 'degradation'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 92, FOLLOW_92_in_function2388);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "deg";

            }
                break;
            case 30:
            // BELScript_v1.g:210:9: fv= 'deg'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 91, FOLLOW_91_in_function2417);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "deg";

            }
                break;
            case 31:
            // BELScript_v1.g:211:9: fv= 'molecularActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 112, FOLLOW_112_in_function2454);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "act";

            }
                break;
            case 32:
            // BELScript_v1.g:212:9: fv= 'act'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 73, FOLLOW_73_in_function2477);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "act";

            }
                break;
            case 33:
            // BELScript_v1.g:213:9: fv= 'catalyticActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 80, FOLLOW_80_in_function2514);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "cat";

            }
                break;
            case 34:
            // BELScript_v1.g:214:9: fv= 'cat'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 79, FOLLOW_79_in_function2537);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "cat";

            }
                break;
            case 35:
            // BELScript_v1.g:215:9: fv= 'kinaseActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 108, FOLLOW_108_in_function2574);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "kin";

            }
                break;
            case 36:
            // BELScript_v1.g:216:9: fv= 'kin'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 107, FOLLOW_107_in_function2600);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "kin";

            }
                break;
            case 37:
            // BELScript_v1.g:217:9: fv= 'phosphataseActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 121, FOLLOW_121_in_function2637);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "phos";

            }
                break;
            case 38:
            // BELScript_v1.g:218:9: fv= 'phos'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 120, FOLLOW_120_in_function2658);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "phos";

            }
                break;
            case 39:
            // BELScript_v1.g:219:9: fv= 'peptidaseActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 119, FOLLOW_119_in_function2694);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "pep";

            }
                break;
            case 40:
            // BELScript_v1.g:220:9: fv= 'pep'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 118, FOLLOW_118_in_function2717);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "pep";

            }
                break;
            case 41:
            // BELScript_v1.g:221:9: fv= 'ribosylationActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 133, FOLLOW_133_in_function2754);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "ribo";

            }
                break;
            case 42:
            // BELScript_v1.g:222:9: fv= 'ribo'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 132, FOLLOW_132_in_function2774);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "ribo";

            }
                break;
            case 43:
            // BELScript_v1.g:223:9: fv= 'transcriptionalActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 144, FOLLOW_144_in_function2810);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tscript";

            }
                break;
            case 44:
            // BELScript_v1.g:224:9: fv= 'tscript'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 150, FOLLOW_150_in_function2827);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tscript";

            }
                break;
            case 45:
            // BELScript_v1.g:225:9: fv= 'transportActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 147, FOLLOW_147_in_function2860);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tport";

            }
                break;
            case 46:
            // BELScript_v1.g:226:9: fv= 'tport'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 142, FOLLOW_142_in_function2883);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "tport";

            }
                break;
            case 47:
            // BELScript_v1.g:227:9: fv= 'gtpBoundActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 100, FOLLOW_100_in_function2918);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "gtp";

            }
                break;
            case 48:
            // BELScript_v1.g:228:9: fv= 'gtp'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 99, FOLLOW_99_in_function2942);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "gtp";

            }
                break;
            case 49:
            // BELScript_v1.g:229:9: fv= 'chaperoneActivity'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 85, FOLLOW_85_in_function2979);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "chap";

            }
                break;
            case 50:
            // BELScript_v1.g:230:9: fv= 'chap'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 84, FOLLOW_84_in_function3002);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "chap";

            }
                break;
            case 51:
            // BELScript_v1.g:231:9: fv= 'proteinModification'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 127, FOLLOW_127_in_function3038);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "pmod";

            }
                break;
            case 52:
            // BELScript_v1.g:232:9: fv= 'pmod'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 122, FOLLOW_122_in_function3059);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "pmod";

            }
                break;
            case 53:
            // BELScript_v1.g:233:9: fv= 'substitution'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 139, FOLLOW_139_in_function3095);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "sub";

            }
                break;
            case 54:
            // BELScript_v1.g:234:9: fv= 'sub'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 137, FOLLOW_137_in_function3123);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "sub";

            }
                break;
            case 55:
            // BELScript_v1.g:235:9: fv= 'truncation'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 149, FOLLOW_149_in_function3160);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "trunc";

            }
                break;
            case 56:
            // BELScript_v1.g:236:9: fv= 'trunc'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 148, FOLLOW_148_in_function3190);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "trunc";

            }
                break;
            case 57:
            // BELScript_v1.g:237:9: fv= 'reactants'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 130, FOLLOW_130_in_function3225);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "reactants";

            }
                break;
            case 58:
            // BELScript_v1.g:238:9: fv= 'products'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 124, FOLLOW_124_in_function3256);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "products";

            }
                break;
            case 59:
            // BELScript_v1.g:239:9: fv= 'list'
            {
                root_0 = (Object) adaptor.nil();

                fv = (Token) match(input, 109, FOLLOW_109_in_function3288);
                fv_tree =
                        (Object) adaptor.create(fv);
                adaptor.addChild(root_0, fv_tree);

                retval.r = "list";

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "function"

    public static class relationship_return extends ParserRuleReturnScope {
        public String r;
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "relationship"
    // BELScript_v1.g:242:1: relationship returns [String r] : (rv= 'increases' |rv= '->' |rv= 'decreases' |rv= '-|' |rv= 'directlyIncreases' |rv= '=>' |rv= 'directlyDecreases' |rv= '=|' |rv= 'causesNoChange' |rv= 'positiveCorrelation' |rv= 'negativeCorrelation' |rv= 'translatedTo' |rv= '>>' |rv= 'transcribedTo' |rv= ':>' |rv= 'isA' |rv= 'subProcessOf' |rv= 'rateLimitingStepOf' |rv= 'biomarkerFor' |rv= 'prognosticBiomarkerFor' |rv= 'orthologous' |rv= 'analogous' |rv= 'association' |rv= '--' |rv= 'hasMembers' |rv= 'hasComponents' |rv= 'hasMember' |rv= 'hasComponent' );
    public final BELScript_v1Parser.relationship_return relationship()
            throws RecognitionException {
        BELScript_v1Parser.relationship_return retval = new BELScript_v1Parser.relationship_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token rv = null;

        Object rv_tree = null;

        try {
            // BELScript_v1.g:243:5: (rv= 'increases' |rv= '->' |rv= 'decreases' |rv= '-|' |rv= 'directlyIncreases' |rv= '=>' |rv= 'directlyDecreases' |rv= '=|' |rv= 'causesNoChange' |rv= 'positiveCorrelation' |rv= 'negativeCorrelation' |rv= 'translatedTo' |rv= '>>' |rv= 'transcribedTo' |rv= ':>' |rv= 'isA' |rv= 'subProcessOf' |rv= 'rateLimitingStepOf' |rv= 'biomarkerFor' |rv= 'prognosticBiomarkerFor' |rv= 'orthologous' |rv= 'analogous' |rv= 'association' |rv= '--' |rv= 'hasMembers' |rv= 'hasComponents' |rv= 'hasMember' |rv= 'hasComponent' )
            int alt25 = 28;
            switch (input.LA(1)) {
            case 105: {
                alt25 = 1;
            }
                break;
            case 65: {
                alt25 = 2;
            }
                break;
            case 90: {
                alt25 = 3;
            }
                break;
            case 66: {
                alt25 = 4;
            }
                break;
            case 94: {
                alt25 = 5;
            }
                break;
            case 68: {
                alt25 = 6;
            }
                break;
            case 93: {
                alt25 = 7;
            }
                break;
            case 69: {
                alt25 = 8;
            }
                break;
            case 81: {
                alt25 = 9;
            }
                break;
            case 123: {
                alt25 = 10;
            }
                break;
            case 113: {
                alt25 = 11;
            }
                break;
            case 145: {
                alt25 = 12;
            }
                break;
            case 70: {
                alt25 = 13;
            }
                break;
            case 143: {
                alt25 = 14;
            }
                break;
            case 67: {
                alt25 = 15;
            }
                break;
            case 106: {
                alt25 = 16;
            }
                break;
            case 138: {
                alt25 = 17;
            }
                break;
            case 129: {
                alt25 = 18;
            }
                break;
            case 77: {
                alt25 = 19;
            }
                break;
            case 125: {
                alt25 = 20;
            }
                break;
            case 114: {
                alt25 = 21;
            }
                break;
            case 74: {
                alt25 = 22;
            }
                break;
            case 75: {
                alt25 = 23;
            }
                break;
            case 64: {
                alt25 = 24;
            }
                break;
            case 104: {
                alt25 = 25;
            }
                break;
            case 102: {
                alt25 = 26;
            }
                break;
            case 103: {
                alt25 = 27;
            }
                break;
            case 101: {
                alt25 = 28;
            }
                break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
            case 1:
            // BELScript_v1.g:243:9: rv= 'increases'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 105, FOLLOW_105_in_relationship3337);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "increases";

            }
                break;
            case 2:
            // BELScript_v1.g:244:9: rv= '->'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 65, FOLLOW_65_in_relationship3368);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "increases";

            }
                break;
            case 3:
            // BELScript_v1.g:245:9: rv= 'decreases'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 90, FOLLOW_90_in_relationship3406);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "decreases";

            }
                break;
            case 4:
            // BELScript_v1.g:246:9: rv= '-|'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 66, FOLLOW_66_in_relationship3437);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "decreases";

            }
                break;
            case 5:
            // BELScript_v1.g:247:9: rv= 'directlyIncreases'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 94, FOLLOW_94_in_relationship3475);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyIncreases";

            }
                break;
            case 6:
            // BELScript_v1.g:248:9: rv= '=>'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 68, FOLLOW_68_in_relationship3498);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyIncreases";

            }
                break;
            case 7:
            // BELScript_v1.g:249:9: rv= 'directlyDecreases'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 93, FOLLOW_93_in_relationship3536);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyDecreases";

            }
                break;
            case 8:
            // BELScript_v1.g:250:9: rv= '=|'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 69, FOLLOW_69_in_relationship3559);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "directlyDecreases";

            }
                break;
            case 9:
            // BELScript_v1.g:251:9: rv= 'causesNoChange'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 81, FOLLOW_81_in_relationship3597);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "causesNoChange";

            }
                break;
            case 10:
            // BELScript_v1.g:252:9: rv= 'positiveCorrelation'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 123, FOLLOW_123_in_relationship3623);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "positiveCorrelation";

            }
                break;
            case 11:
            // BELScript_v1.g:253:9: rv= 'negativeCorrelation'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 113, FOLLOW_113_in_relationship3644);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "negativeCorrelation";

            }
                break;
            case 12:
            // BELScript_v1.g:254:9: rv= 'translatedTo'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 145, FOLLOW_145_in_relationship3665);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "translatedTo";

            }
                break;
            case 13:
            // BELScript_v1.g:255:9: rv= '>>'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 70, FOLLOW_70_in_relationship3693);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "translatedTo";

            }
                break;
            case 14:
            // BELScript_v1.g:256:9: rv= 'transcribedTo'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 143, FOLLOW_143_in_relationship3731);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "transcribedTo";

            }
                break;
            case 15:
            // BELScript_v1.g:257:9: rv= ':>'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 67, FOLLOW_67_in_relationship3758);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "transcribedTo";

            }
                break;
            case 16:
            // BELScript_v1.g:258:9: rv= 'isA'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 106, FOLLOW_106_in_relationship3796);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "isA";

            }
                break;
            case 17:
            // BELScript_v1.g:259:9: rv= 'subProcessOf'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 138, FOLLOW_138_in_relationship3833);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "subProcessOf";

            }
                break;
            case 18:
            // BELScript_v1.g:260:9: rv= 'rateLimitingStepOf'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 129, FOLLOW_129_in_relationship3861);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "rateLimitingStepOf";

            }
                break;
            case 19:
            // BELScript_v1.g:261:9: rv= 'biomarkerFor'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 77, FOLLOW_77_in_relationship3883);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "biomarkerFor";

            }
                break;
            case 20:
            // BELScript_v1.g:262:9: rv= 'prognosticBiomarkerFor'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 125, FOLLOW_125_in_relationship3911);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "prognosticBiomarkerFor";

            }
                break;
            case 21:
            // BELScript_v1.g:263:9: rv= 'orthologous'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 114, FOLLOW_114_in_relationship3929);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "orthologous";

            }
                break;
            case 22:
            // BELScript_v1.g:264:9: rv= 'analogous'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 74, FOLLOW_74_in_relationship3958);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "analogous";

            }
                break;
            case 23:
            // BELScript_v1.g:265:9: rv= 'association'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 75, FOLLOW_75_in_relationship3989);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "association";

            }
                break;
            case 24:
            // BELScript_v1.g:266:9: rv= '--'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 64, FOLLOW_64_in_relationship4018);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "association";

            }
                break;
            case 25:
            // BELScript_v1.g:267:9: rv= 'hasMembers'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 104, FOLLOW_104_in_relationship4056);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasMembers";

            }
                break;
            case 26:
            // BELScript_v1.g:268:9: rv= 'hasComponents'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 102, FOLLOW_102_in_relationship4086);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasComponents";

            }
                break;
            case 27:
            // BELScript_v1.g:269:9: rv= 'hasMember'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 103, FOLLOW_103_in_relationship4113);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasMember";

            }
                break;
            case 28:
            // BELScript_v1.g:270:9: rv= 'hasComponent'
            {
                root_0 = (Object) adaptor.nil();

                rv = (Token) match(input, 101, FOLLOW_101_in_relationship4144);
                rv_tree =
                        (Object) adaptor.create(rv);
                adaptor.addChild(root_0, rv_tree);

                retval.r = "hasComponent";

            }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "relationship"

    public static class eq_clause_return extends ParserRuleReturnScope {
        Object tree;

        public Object getTree() {
            return tree;
        }
    };

    // $ANTLR start "eq_clause"
    // BELScript_v1.g:273:1: eq_clause : ( WS )* EQ ( WS )* ;
    public final BELScript_v1Parser.eq_clause_return eq_clause()
            throws RecognitionException {
        BELScript_v1Parser.eq_clause_return retval = new BELScript_v1Parser.eq_clause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS92 = null;
        Token EQ93 = null;
        Token WS94 = null;

        Object WS92_tree = null;
        Object EQ93_tree = null;
        Object WS94_tree = null;

        try {
            // BELScript_v1.g:274:5: ( ( WS )* EQ ( WS )* )
            // BELScript_v1.g:274:9: ( WS )* EQ ( WS )*
            {
                root_0 = (Object) adaptor.nil();

                // BELScript_v1.g:274:9: ( WS )*
                loop26: do {
                    int alt26 = 2;
                    int LA26_0 = input.LA(1);

                    if ((LA26_0 == WS)) {
                        alt26 = 1;
                    }

                    switch (alt26) {
                    case 1:
                    // BELScript_v1.g:274:9: WS
                    {
                        WS92 = (Token) match(input, WS,
                                FOLLOW_WS_in_eq_clause4179);
                        WS92_tree =
                                (Object) adaptor.create(WS92);
                        adaptor.addChild(root_0, WS92_tree);

                    }
                        break;

                    default:
                        break loop26;
                    }
                } while (true);

                EQ93 = (Token) match(input, EQ, FOLLOW_EQ_in_eq_clause4182);
                EQ93_tree =
                        (Object) adaptor.create(EQ93);
                adaptor.addChild(root_0, EQ93_tree);

                // BELScript_v1.g:274:16: ( WS )*
                loop27: do {
                    int alt27 = 2;
                    int LA27_0 = input.LA(1);

                    if ((LA27_0 == WS)) {
                        alt27 = 1;
                    }

                    switch (alt27) {
                    case 1:
                    // BELScript_v1.g:274:16: WS
                    {
                        WS94 = (Token) match(input, WS,
                                FOLLOW_WS_in_eq_clause4184);
                        WS94_tree =
                                (Object) adaptor.create(WS94);
                        adaptor.addChild(root_0, WS94_tree);

                    }
                        break;

                    default:
                        break loop27;
                    }
                } while (true);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start,
                    input.LT(-1), re);

        }

        finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR end "eq_clause"

    // Delegated rules

    protected DFA2 dfa2 = new DFA2(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA2_eotS =
            "\15\uffff";
    static final String DFA2_eofS =
            "\15\uffff";
    static final String DFA2_minS =
            "\1\34\1\27\1\40\1\26\4\uffff\1\40\4\uffff";
    static final String DFA2_maxS =
            "\1\u0096\2\77\1\57\4\uffff\1\77\4\uffff";
    static final String DFA2_acceptS =
            "\4\uffff\1\10\1\1\1\2\1\3\1\uffff\1\4\1\5\1\6\1\7";
    static final String DFA2_specialS =
            "\15\uffff}>";
    static final String[] DFA2_transitionS = {
        "\1\1\11\uffff\1\2\1\uffff\1\3\36\uffff\3\4\2\uffff\1\4\1\uffff"
                +
                "\3\4\1\uffff\10\4\1\uffff\2\4\2\uffff\6\4\6\uffff\6\4\2\uffff"
                +
                "\10\4\1\uffff\1\4\1\uffff\3\4\1\uffff\10\4\1\uffff\4\4\1\uffff"
                +
                "\1\4\1\uffff\5\4",
        "\1\6\6\uffff\1\5\5\uffff\1\5\32\uffff\1\6",
        "\1\11\6\uffff\1\12\7\uffff\1\7\17\uffff\1\10",
        "\1\14\20\uffff\1\13\7\uffff\1\14",
        "",
        "",
        "",
        "",
        "\1\11\6\uffff\1\12\27\uffff\1\10",
        "",
        "",
        "",
        ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA
            .unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA
            .unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }

        public String getDescription() {
            return "72:1: record : ( define_namespace | define_annotation | set_annotation | set_document | set_statement_group | unset_statement_group | unset | statement );";
        }
    }

    static final String DFA5_eotS =
            "\14\uffff";
    static final String DFA5_eofS =
            "\14\uffff";
    static final String DFA5_minS =
            "\1\46\2\40\2\31\2\23\2\57\3\uffff";
    static final String DFA5_maxS =
            "\1\46\10\77\3\uffff";
    static final String DFA5_acceptS =
            "\11\uffff\1\1\1\2\1\3";
    static final String DFA5_specialS =
            "\14\uffff}>";
    static final String[] DFA5_transitionS = {
        "\1\1",
        "\1\3\36\uffff\1\2",
        "\1\3\36\uffff\1\2",
        "\3\5\1\uffff\1\5\1\uffff\1\5\1\uffff\1\5\1\uffff\1\5\6\uffff" +
                "\1\5\24\uffff\1\4",
        "\3\5\1\uffff\1\5\1\uffff\1\5\1\uffff\1\5\1\uffff\1\5\6\uffff" +
                "\1\5\24\uffff\1\4",
        "\1\7\53\uffff\1\6",
        "\1\7\53\uffff\1\6",
        "\1\13\3\uffff\1\11\12\uffff\1\12\1\10",
        "\1\13\3\uffff\1\11\12\uffff\1\12\1\10",
        "",
        "",
        ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA
            .unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA
            .unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }

        public String getDescription() {
            return "87:1: set_document : ( set_doc_expr document_property eq_clause val= QUOTED_VALUE -> ^( DOCSET_QV document_property $val) | set_doc_expr document_property eq_clause val= VALUE_LIST -> ^( DOCSET_LIST document_property $val) | set_doc_expr document_property eq_clause val= OBJECT_IDENT -> ^( DOCSET_ID document_property $val) );";
        }
    }

    static final String DFA7_eotS =
            "\11\uffff";
    static final String DFA7_eofS =
            "\11\uffff";
    static final String DFA7_minS =
            "\1\46\2\47\2\23\2\57\2\uffff";
    static final String DFA7_maxS =
            "\1\46\6\77\2\uffff";
    static final String DFA7_acceptS =
            "\7\uffff\1\1\1\2";
    static final String DFA7_specialS =
            "\11\uffff}>";
    static final String[] DFA7_transitionS = {
        "\1\1",
        "\1\3\27\uffff\1\2",
        "\1\3\27\uffff\1\2",
        "\1\5\53\uffff\1\4",
        "\1\5\53\uffff\1\4",
        "\1\10\3\uffff\1\7\13\uffff\1\6",
        "\1\10\3\uffff\1\7\13\uffff\1\6",
        "",
        ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA
            .unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA
            .unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }

        public String getDescription() {
            return "100:1: set_statement_group : ( set_sg_expr eq_clause val= QUOTED_VALUE -> ^( SG_SET_QV $val) | set_sg_expr eq_clause val= OBJECT_IDENT -> ^( SG_SET_ID $val) );";
        }
    }

    static final String DFA8_eotS =
            "\11\uffff";
    static final String DFA8_eofS =
            "\11\uffff";
    static final String DFA8_minS =
            "\1\46\1\57\2\23\2\57\3\uffff";
    static final String DFA8_maxS =
            "\1\46\1\57\4\77\3\uffff";
    static final String DFA8_acceptS =
            "\6\uffff\1\1\1\2\1\3";
    static final String DFA8_specialS =
            "\11\uffff}>";
    static final String[] DFA8_transitionS = {
        "\1\1",
        "\1\2",
        "\1\4\53\uffff\1\3",
        "\1\4\53\uffff\1\3",
        "\1\10\3\uffff\1\6\12\uffff\1\7\1\5",
        "\1\10\3\uffff\1\6\12\uffff\1\7\1\5",
        "",
        "",
        ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA
            .unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA
            .unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }

        public String getDescription() {
            return "105:1: set_annotation : ( KWRD_SET OBJECT_IDENT eq_clause val= QUOTED_VALUE -> ^( ANNO_SET_QV OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= VALUE_LIST -> ^( ANNO_SET_LIST OBJECT_IDENT $val) | KWRD_SET OBJECT_IDENT eq_clause val= OBJECT_IDENT -> ^( ANNO_SET_ID OBJECT_IDENT $val) );";
        }
    }

    static final String DFA13_eotS =
            "\12\uffff";
    static final String DFA13_eofS =
            "\12\uffff";
    static final String DFA13_minS =
            "\1\34\2\27\2\57\1\30\1\42\3\uffff";
    static final String DFA13_maxS =
            "\1\34\4\77\1\30\1\51\3\uffff";
    static final String DFA13_acceptS =
            "\7\uffff\1\1\1\2\1\3";
    static final String DFA13_specialS =
            "\12\uffff}>";
    static final String[] DFA13_transitionS = {
        "\1\1",
        "\1\3\47\uffff\1\2",
        "\1\3\47\uffff\1\2",
        "\1\5\17\uffff\1\4",
        "\1\5\17\uffff\1\4",
        "\1\6",
        "\1\7\2\uffff\1\11\3\uffff\1\10",
        "",
        "",
        ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA
            .unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA
            .unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA
            .unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }

        public String getDescription() {
            return "134:1: define_annotation : ( define_anno_expr OBJECT_IDENT KWRD_AS KWRD_LIST val= VALUE_LIST -> ^( ANNO_DEF_LIST OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_URL val= QUOTED_VALUE -> ^( ANNO_DEF_URL OBJECT_IDENT $val) | define_anno_expr OBJECT_IDENT KWRD_AS KWRD_PATTERN val= QUOTED_VALUE -> ^( ANNO_DEF_PTRN OBJECT_IDENT $val) );";
        }
    }

    public static final BitSet FOLLOW_NEWLINE_in_document327 = new BitSet(
            new long[] { 0x0000214010040000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_DOCUMENT_COMMENT_in_document331 = new BitSet(
            new long[] { 0x0000214010040000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_record_in_document335 = new BitSet(
            new long[] { 0x0000214010040000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_EOF_in_document339 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_define_namespace_in_record375 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_define_annotation_in_record385 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_annotation_in_record395 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_document_in_record405 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_statement_group_in_record415 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_unset_statement_group_in_record425 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_unset_in_record435 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_statement_in_record445 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_doc_expr464 = new BitSet(
            new long[] { 0x8000000100000000L });
    public static final BitSet FOLLOW_WS_in_set_doc_expr466 = new BitSet(
            new long[] { 0x8000000100000000L });
    public static final BitSet FOLLOW_KWRD_DOCUMENT_in_set_doc_expr469 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_WS_in_set_doc_expr471 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_set_doc_expr_in_set_document491 = new BitSet(
            new long[] { 0x0000040AAE000000L });
    public static final BitSet FOLLOW_document_property_in_set_document493 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_document495 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_document499 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_doc_expr_in_set_document528 = new BitSet(
            new long[] { 0x0000040AAE000000L });
    public static final BitSet FOLLOW_document_property_in_set_document530 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_document532 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_set_document536 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_doc_expr_in_set_document565 = new BitSet(
            new long[] { 0x0000040AAE000000L });
    public static final BitSet FOLLOW_document_property_in_set_document567 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_document569 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_document573 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_sg_expr611 = new BitSet(
            new long[] { 0x8000008000000000L });
    public static final BitSet FOLLOW_WS_in_set_sg_expr613 = new BitSet(
            new long[] { 0x8000008000000000L });
    public static final BitSet FOLLOW_KWRD_STMT_GROUP_in_set_sg_expr616 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_sg_expr_in_set_statement_group635 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_statement_group637 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_statement_group641 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_sg_expr_in_set_statement_group660 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_statement_group662 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_statement_group666 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_annotation694 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation696 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_annotation698 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_set_annotation702 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_annotation731 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation733 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_annotation735 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_set_annotation739 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_SET_in_set_annotation768 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation770 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_eq_clause_in_set_annotation772 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_set_annotation776 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_UNSET_in_unset_statement_group814 = new BitSet(
            new long[] { 0x0000008000000000L });
    public static final BitSet FOLLOW_KWRD_STMT_GROUP_in_unset_statement_group816 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_UNSET_in_unset841 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_unset845 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_UNSET_in_unset864 = new BitSet(
            new long[] { 0x0000000000400000L });
    public static final BitSet FOLLOW_IDENT_LIST_in_unset868 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_DEFINE_in_define_namespace896 = new BitSet(
            new long[] { 0x0000000040000000L });
    public static final BitSet FOLLOW_KWRD_DFLT_in_define_namespace898 = new BitSet(
            new long[] { 0x0000001000000000L });
    public static final BitSet FOLLOW_KWRD_NS_in_define_namespace900 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_namespace902 = new BitSet(
            new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_KWRD_AS_in_define_namespace904 = new BitSet(
            new long[] { 0x0000020000000000L });
    public static final BitSet FOLLOW_KWRD_URL_in_define_namespace906 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_namespace908 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_DEFINE_in_define_namespace936 = new BitSet(
            new long[] { 0x0000001000000000L });
    public static final BitSet FOLLOW_KWRD_NS_in_define_namespace938 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_namespace940 = new BitSet(
            new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_KWRD_AS_in_define_namespace942 = new BitSet(
            new long[] { 0x0000020000000000L });
    public static final BitSet FOLLOW_KWRD_URL_in_define_namespace944 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_namespace946 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_KWRD_DEFINE_in_define_anno_expr983 = new BitSet(
            new long[] { 0x8000000000800000L });
    public static final BitSet FOLLOW_WS_in_define_anno_expr985 = new BitSet(
            new long[] { 0x8000000000800000L });
    public static final BitSet FOLLOW_KWRD_ANNO_in_define_anno_expr988 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_WS_in_define_anno_expr990 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_define_anno_expr_in_define_annotation1010 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_annotation1012 = new BitSet(
            new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_KWRD_AS_in_define_annotation1014 = new BitSet(
            new long[] { 0x0000000400000000L });
    public static final BitSet FOLLOW_KWRD_LIST_in_define_annotation1016 = new BitSet(
            new long[] { 0x4000000000000000L });
    public static final BitSet FOLLOW_VALUE_LIST_in_define_annotation1020 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_define_anno_expr_in_define_annotation1049 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_annotation1051 = new BitSet(
            new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_KWRD_AS_in_define_annotation1053 = new BitSet(
            new long[] { 0x0000020000000000L });
    public static final BitSet FOLLOW_KWRD_URL_in_define_annotation1055 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_annotation1059 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_define_anno_expr_in_define_annotation1088 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_define_annotation1090 = new BitSet(
            new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_KWRD_AS_in_define_annotation1092 = new BitSet(
            new long[] { 0x0000002000000000L });
    public static final BitSet FOLLOW_KWRD_PATTERN_in_define_annotation1094 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_define_annotation1098 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_COMMA_in_argument1225 = new BitSet(
            new long[] { 0x0000000000000000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_term_in_argument1228 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_COMMA_in_argument1242 = new BitSet(
            new long[] { 0x0008800000000000L });
    public static final BitSet FOLLOW_param_in_argument1245 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_function_in_term1268 = new BitSet(
            new long[] { 0x0000100000000000L });
    public static final BitSet FOLLOW_LP_in_term1270 = new BitSet(new long[] {
        0x0018800000000800L, 0xD7F9F81F9BFDD380L, 0x00000000007D7BFDL });
    public static final BitSet FOLLOW_argument_in_term1273 = new BitSet(
            new long[] { 0x0018800000000800L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_RP_in_term1277 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_term_in_statement1319 = new BitSet(
            new long[] { 0x0080000000000002L, 0x280607E064022C7FL,
                0x0000000000028402L });
    public static final BitSet FOLLOW_relationship_in_statement1324 = new BitSet(
            new long[] { 0x0000100000000000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_LP_in_statement1327 = new BitSet(
            new long[] { 0x0000000000000000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_term_in_statement1331 = new BitSet(
            new long[] { 0x0000000000000000L, 0x280607E064022C7FL,
                0x0000000000028402L });
    public static final BitSet FOLLOW_relationship_in_statement1335 = new BitSet(
            new long[] { 0x0000000000000000L, 0xD7F9F81F9BFDD380L,
                0x00000000007D7BFDL });
    public static final BitSet FOLLOW_term_in_statement1339 = new BitSet(
            new long[] { 0x0010000000000000L });
    public static final BitSet FOLLOW_RP_in_statement1341 = new BitSet(
            new long[] { 0x0080000000000002L });
    public static final BitSet FOLLOW_term_in_statement1347 = new BitSet(
            new long[] { 0x0080000000000002L });
    public static final BitSet FOLLOW_STATEMENT_COMMENT_in_statement1354 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_ns_prefix1415 = new BitSet(
            new long[] { 0x0000000000000400L });
    public static final BitSet FOLLOW_COLON_in_ns_prefix1417 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ns_prefix_in_param1437 = new BitSet(
            new long[] { 0x0000800000000000L });
    public static final BitSet FOLLOW_OBJECT_IDENT_in_param1440 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ns_prefix_in_param1461 = new BitSet(
            new long[] { 0x0008000000000000L });
    public static final BitSet FOLLOW_QUOTED_VALUE_in_param1464 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_126_in_function1500 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_115_in_function1524 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_134_in_function1563 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_128_in_function1591 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_72_in_function1630 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_71_in_function1661 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_111_in_function1700 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_110_in_function1723 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_98_in_function1762 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_97_in_function1789 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_76_in_function1828 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_78_in_function1851 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_117_in_function1889 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_116_in_function1920 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_87_in_function1956 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_86_in_function1980 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_146_in_function2013 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_141_in_function2040 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_82_in_function2076 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_136_in_function2103 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_83_in_function2140 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_140_in_function2159 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_131_in_function2195 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_135_in_function2227 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_89_in_function2264 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_88_in_function2286 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_96_in_function2317 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_95_in_function2351 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_92_in_function2388 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_91_in_function2417 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_112_in_function2454 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_73_in_function2477 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_80_in_function2514 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_79_in_function2537 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_108_in_function2574 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_107_in_function2600 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_121_in_function2637 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_120_in_function2658 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_119_in_function2694 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_118_in_function2717 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_133_in_function2754 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_132_in_function2774 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_144_in_function2810 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_150_in_function2827 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_147_in_function2860 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_142_in_function2883 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_100_in_function2918 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_99_in_function2942 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_85_in_function2979 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_84_in_function3002 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_127_in_function3038 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_122_in_function3059 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_139_in_function3095 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_137_in_function3123 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_149_in_function3160 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_148_in_function3190 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_130_in_function3225 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_124_in_function3256 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_109_in_function3288 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_105_in_relationship3337 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_65_in_relationship3368 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_90_in_relationship3406 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_66_in_relationship3437 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_94_in_relationship3475 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_68_in_relationship3498 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_93_in_relationship3536 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_69_in_relationship3559 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_81_in_relationship3597 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_123_in_relationship3623 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_113_in_relationship3644 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_145_in_relationship3665 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_70_in_relationship3693 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_143_in_relationship3731 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_67_in_relationship3758 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_106_in_relationship3796 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_138_in_relationship3833 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_129_in_relationship3861 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_77_in_relationship3883 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_125_in_relationship3911 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_114_in_relationship3929 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_74_in_relationship3958 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_75_in_relationship3989 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_64_in_relationship4018 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_104_in_relationship4056 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_102_in_relationship4086 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_103_in_relationship4113 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_101_in_relationship4144 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_WS_in_eq_clause4179 = new BitSet(
            new long[] { 0x8000000000080000L });
    public static final BitSet FOLLOW_EQ_in_eq_clause4182 = new BitSet(
            new long[] { 0x8000000000000002L });
    public static final BitSet FOLLOW_WS_in_eq_clause4184 = new BitSet(
            new long[] { 0x8000000000000002L });

}