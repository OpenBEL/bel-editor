package org.openbel.editor.ui;

import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.viewsupport.ISelectionListenerWithAST;
import org.eclipse.dltk.ui.viewsupport.SelectionListenerWithASTManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.openbel.editor.core.parser.BELScriptDocument;
import org.openbel.editor.core.parser.ast.ASTStatement;
import org.openbel.editor.core.parser.ast.AnnotationSetField;
import org.openbel.editor.core.parser.ast.AnnotationSetListField;

public class AstTreeViewPart extends ViewPart implements
        ISelectionListenerWithAST
{
    public static final String ID = "org.openbel.editor.ui.AstTreeViewPart";
    private Table table;
    private Display display;

    public AstTreeViewPart() {
    }

    @Override
    public void createPartControl(Composite parent) {
        display = parent.getDisplay();
        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .
                getActivePage().getActiveEditor();
        if (part instanceof ITextEditor)
        {
            SelectionListenerWithASTManager.getDefault().addListener(
                    (ITextEditor) part,
                    this);
        }
        table = new Table(parent, SWT.MULTI | SWT.BORDER
                | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        String[] titles = { "Annotation Name", "Annotation Value" };
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
        }

        for (int i = 0; i < titles.length; i++) {
            table.getColumn(i).pack();
        }
        table.setSize(table.computeSize(SWT.DEFAULT, 200));
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }

    @Override
    public void selectionChanged(final IEditorPart part,
            ITextSelection selection,
            ISourceModule module, IModuleDeclaration astRoot) {
        final IDocument document = ((ITextEditor) part).getDocumentProvider()
                .getDocument(
                        part.getEditorInput());
        BELScriptDocument script = (BELScriptDocument) astRoot;
        String selectedText = null;
        try {
            //get line from offset
            IRegion lineInformation = document
                    .getLineInformationOfOffset(selection.getOffset());

            //get selected line length
            int lineLength = document.getLineLength(selection.getStartLine());
            //get selected line start offset
            int offset = lineInformation.getOffset();
            //get text from line
            selectedText = document
                    .get(offset, lineLength);
            //search for multi-line statements
            String nextline = null;
            String previousLine = null;
            if (selectedText.endsWith("\\\r\n")) {
                //the cursor is on the first line of the multiple line statement
                nextline = document.get(
                        document.getLineOffset(selection.getStartLine() + 1),
                        document.getLineLength(selection.getStartLine() + 1));
                selectedText = selectedText + nextline;
            }
            previousLine = document.get(
                    document.getLineOffset(selection.getStartLine() - 1),
                    document.getLineLength(selection.getStartLine() - 1));
            if (previousLine.endsWith("\\\r\n")) {
                ///the cursor is on the second line of the multiple line statement
                selectedText = previousLine
                        + selectedText;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        //remove newline char and \
        selectedText = selectedText.replaceAll("\r\n", "")
                .replaceAll("\\\\", " ");
        display.syncExec(
                new Runnable() {
                    public void run() {
                        table.removeAll();
                    }
                });
        for (ASTStatement field : script.getDocDef().getStatementsList()) {
            if (selectedText.replaceAll(" ", "").equals(
                    field.toString().replaceAll(" ", ""))) {
                for (final AnnotationSetField annotatioSetField : field
                        .getAnnotationsList()) {
                    display.syncExec(
                            new Runnable() {
                                public void run() {
                                    TableItem item = new TableItem(table,
                                            SWT.NONE);
                                    item.setText(0, annotatioSetField.getName()
                                            .getName());
                                    item.setText(1, annotatioSetField
                                            .getValue().getText());
                                }
                            });

                }
                for (final AnnotationSetListField annotatioSetField : field
                        .getAnnotationSetList()) {
                    display.syncExec(
                            new Runnable() {
                                public void run() {
                                    TableItem item = new TableItem(table,
                                            SWT.NONE);
                                    item.setText(0, annotatioSetField
                                            .getObjectIdent()
                                            .getName());
                                    item.setText(1, annotatioSetField
                                            .getValueList()
                                            .getText());
                                }
                            });

                }
            }
        }

    }

}
