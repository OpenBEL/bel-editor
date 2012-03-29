package org.openbel.workbench.core.parser;

import static org.openbel.workbench.core.Nature.BEL_NATURE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.MethodCallExpression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.AbstractSourceElementParser;

/**
 * TODO Source parsers build abstract syntax trees. Me, I grow plants.
 */
public class BELScriptElementParser extends AbstractSourceElementParser {
    private IProblemReporter pr = null;
    private ISourceElementRequestor req = null;

    /**
     * {@link BELScriptElementParser}
     */
    public BELScriptElementParser() {

    }

    private boolean isObjectIdent(String name) {
        if (name.matches("\\w+"))
            return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parseSourceModule(IModuleSource source) {
        String sc = source.getSourceContents();
        BELModuleDeclaration modules = new BELModuleDeclaration(sc.length());

        StringReader sr = new StringReader(source.getSourceContents());
        BELModel model = parse(sr, modules);
        modules.setFunctions(model.getFunctions());
        modules.setVariables(model.getVariables());
        processNode(model, modules);
        // return modules;
    }

    private BELModel parse(StringReader reader,
            BELModuleDeclaration moduleDeclaration) {
        BELModel model = new BELModel();

        BufferedReader bReader = new BufferedReader(reader);
        String line;
        int lineStart = 0;
        Set<String> functionNames = new HashSet<String>();
        Set<String> varNames = new HashSet<String>();
        MethodDeclaration mDeclaration = null;
        Stack<Declaration> tmp = new Stack<Declaration>();
        boolean isPrevLnContinued = false;

        try {
            while ((line = bReader.readLine()) != null) {
                if (line.trim().length() == 0 || line.trim().charAt(0) == '#') {
                    lineStart += line.length() + 1;
                    continue;
                }
                if (line.contains("()")) {
                    int lBracket = line.indexOf('{') == -1 ? 0 : line
                            .indexOf('{');
                    int fPlusEight = line.indexOf("function") == -1 ? 0 : line
                            .indexOf("function") + 8;
                    mDeclaration = new MethodDeclaration(line.substring(
                            fPlusEight, line.indexOf('(')).trim(), lineStart,
                            lineStart + line.length() - 1,
                            lBracket + lineStart, lBracket + lineStart);
                    functionNames.add(line.substring(fPlusEight,
                            line.indexOf('(')).trim());
                    tmp.push(mDeclaration);
                    model.addFunction(mDeclaration);
                } else if (line.contains("function ")) {
                    int fPlusEight = line.indexOf("function") + 8;
                    int lBracket = line.indexOf('{') == -1 ? line.length()
                            : line.indexOf('{') - 1;
                    if (fPlusEight >= line.length())
                        continue;
                    mDeclaration = new MethodDeclaration(line.substring(
                            fPlusEight, lBracket).trim(), lineStart, lineStart
                            + line.length() - 1, lBracket + lineStart, lBracket
                            + lineStart);
                    functionNames.add(line.substring(fPlusEight, lBracket)
                            .trim());
                    tmp.push(mDeclaration);
                    model.addFunction(mDeclaration);
                } else if (line.trim().equals("}")) {
                    if (mDeclaration != null) {
                        if (!tmp.isEmpty()) {
                            mDeclaration = (MethodDeclaration) tmp.pop();
                            mDeclaration.setEnd(lineStart + line.length());
                        }
                    }
                }
                Pattern assignmentPattern = Pattern.compile("(^|\\W)\\w*=");
                Matcher matcher = assignmentPattern.matcher(line);
                if (matcher.find()) {
                    String name = line.substring(matcher.start(),
                            matcher.end() - 1);
                    if (isObjectIdent(name)) {
                        FieldDeclaration variable = new FieldDeclaration(name,
                                lineStart + matcher.start(), lineStart
                                        + matcher.end(), lineStart
                                        + matcher.start(), lineStart
                                        + matcher.end());
                        varNames.add(name);
                        model.addVariable(variable);
                    }
                }

                // start of if statement
                if (line.contains("if ") && !line.contains("elif ")) {
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("if"),
                            lineStart + line.length() - 1, lineStart, lineStart
                                    + line.length());
                    model.addStatement(mDeclaration);
                    tmp.push(mDeclaration);

                    // end of if statement
                } else if (line.trim().equals("fi")) {
                    if (!tmp.isEmpty()) {
                        mDeclaration = (MethodDeclaration) tmp.pop();
                        mDeclaration.setEnd(lineStart + line.indexOf("fi"));
                    }

                    // start of while statement
                } else if (line.contains("while ")) {
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("while"), lineStart + line.length()
                            - 1, lineStart, lineStart + line.length());
                    model.addStatement(mDeclaration);
                    tmp.push(mDeclaration);

                    // start of until statement
                } else if (line.contains("until ")) {
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("until"), lineStart + line.length()
                            - 1, lineStart, lineStart + line.length());
                    model.addStatement(mDeclaration);
                    tmp.push(mDeclaration);

                    // done statement encountered
                } else if (line.contains("done ") || line.trim().equals("done")) {
                    if (!tmp.isEmpty()) {
                        mDeclaration = (MethodDeclaration) tmp.pop();
                        mDeclaration.setEnd(lineStart + line.indexOf("done"));
                    }

                    // start of for statement
                } else if (line.contains("for ")) {
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("for"), lineStart + line.length()
                            - 1, lineStart, lineStart + line.length());
                    model.addStatement(mDeclaration);
                    tmp.push(mDeclaration);

                    // start of case statement
                } else if (line.contains("case ")) {
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("case"), lineStart + line.length()
                            - 1, lineStart, lineStart + line.length());
                    model.addStatement(mDeclaration);
                    tmp.push(mDeclaration);

                    // end of case statement
                } else if (line.trim().equals("esac")) {
                    if (!tmp.isEmpty()) {
                        mDeclaration = (MethodDeclaration) tmp.pop();
                        mDeclaration.setEnd(lineStart + line.indexOf("esac"));
                    }
                }

                // multi-line commands and literals
                if (line.charAt(line.length() - 1) == '\\'
                        && !isPrevLnContinued) {
                    isPrevLnContinued = true;
                    mDeclaration = new MethodDeclaration(line.substring(0,
                            line.length()).trim(), lineStart
                            + line.indexOf("\\"),
                            lineStart + line.length() - 1, lineStart, lineStart
                                    + line.length());
                    tmp.push(mDeclaration);
                    model.addStatement(mDeclaration);
                } else if (line.charAt(line.length() - 1) == '\\'
                        && isPrevLnContinued) {
                    if (!tmp.isEmpty()) {
                        mDeclaration = (MethodDeclaration) tmp.pop();
                        mDeclaration.setEnd(lineStart + line.indexOf('\\'));
                        tmp.push(mDeclaration);
                    }
                } else if (isPrevLnContinued) {
                    isPrevLnContinued = false;
                    if (!tmp.isEmpty()) {
                        mDeclaration = (MethodDeclaration) tmp.pop();
                        mDeclaration.setEnd(lineStart + line.length() - 2);
                    }
                }

                for (String funcName : functionNames) {
                    if (line.contains(funcName)) {
                        moduleDeclaration
                                .addStatement(new MethodCallExpression(
                                        lineStart + line.indexOf(funcName),
                                        lineStart + line.indexOf(funcName)
                                                + funcName.length(), null,
                                        funcName, null));
                    }
                }
                for (String varName : varNames) {
                    Pattern varRefPattern = Pattern.compile("(^|\\W)\\$\\b"
                            + varName + "\\b");
                    Matcher varRefMatcher = varRefPattern.matcher(line);
                    while (varRefMatcher.find()) {
                        moduleDeclaration.addStatement(new VariableReference(
                                lineStart + varRefMatcher.start(), lineStart
                                        + varRefMatcher.end(), varName));
                    }
                }

                lineStart += line.length() + 1;
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    private void processNode(BELModel parse, ModuleDeclaration moduleDeclaration) {
        for (MethodDeclaration functionNode : parse.getFunctions()) {
            moduleDeclaration.addStatement(functionNode);
        }
        for (FieldDeclaration variableNode : parse.getVariables()) {
            moduleDeclaration.addStatement(variableNode);
        }
        for (MethodDeclaration statement : parse.getStatements()) {
            moduleDeclaration.addStatement(statement);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReporter(IProblemReporter arg0) {
        this.pr = arg0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequestor(ISourceElementRequestor arg0) {
        this.req = arg0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getNatureId() {
        return BEL_NATURE;
    }

}
