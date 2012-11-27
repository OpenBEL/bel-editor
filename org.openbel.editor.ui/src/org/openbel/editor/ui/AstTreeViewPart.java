package org.openbel.editor.ui;

import static org.eclipse.swt.widgets.Display.getDefault;

import java.util.Set;

import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.viewsupport.ISelectionListenerWithAST;
import org.eclipse.dltk.ui.viewsupport.SelectionListenerWithASTManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.openbel.editor.core.parser.BELScriptDocument;
import org.openbel.editor.core.parser.ast.ASTStatement;
import org.openbel.editor.core.parser.ast.AnnotationSetField;
import org.openbel.editor.core.parser.ast.AnnotationSetListField;
import org.openbel.editor.ui.editor.BELScriptEditor;

public class AstTreeViewPart extends ViewPart implements
        ISelectionListenerWithAST, ISelectionListener {

    public static final String ID = "org.openbel.editor.ui.AstTreeViewPart";
    private Table table;
    private IWorkbenchPartSite site;
    private BELScriptEditor activeEditor;
    private SelectionListenerWithASTManager sellstnr;

    public AstTreeViewPart() {
    }

    @Override
    public void createPartControl(Composite parent) {
        sellstnr = SelectionListenerWithASTManager.getDefault();
        site = getSite();
        IWorkbenchWindow wwin = site.getWorkbenchWindow();
        ISelectionService selsvc = wwin.getSelectionService();
        selsvc.addSelectionListener(this);

        IWorkbenchPage page = wwin.getActivePage();
        IEditorPart editor = page.getActiveEditor();

        if (editor instanceof BELScriptEditor) {
            this.activeEditor = (BELScriptEditor) editor;
            sellstnr.addListener(this.activeEditor, this);
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

    }

    @Override
    public void selectionChanged(final IEditorPart part,
            ITextSelection selection,
            ISourceModule module, IModuleDeclaration astRoot) {

        if (part != this.activeEditor) return;
        BELScriptEditor editor = (BELScriptEditor) part;
        IDocumentProvider provider = editor.getDocumentProvider();
        IDocument doc = provider.getDocument(editor.getEditorInput());

        BELScriptDocument script = (BELScriptDocument) astRoot;
        String selectedText = null;
        try {
            //get line from offset
            int seloff = selection.getOffset();
            IRegion lineInformation = doc.getLineInformationOfOffset(seloff);

            //get selected line length
            int lineLength = doc.getLineLength(selection.getStartLine());
            //get selected line start offset
            int offset = lineInformation.getOffset();
            //get text from line
            selectedText = doc.get(offset, lineLength);
            //search for multi-line statements
            String nextline = null;
            String previousLine = null;
            if (selectedText.endsWith("\\\r\n")) {
                //the cursor is on the first line of the multiple line statement
                nextline = doc.get(
                        doc.getLineOffset(selection.getStartLine() + 1),
                        doc.getLineLength(selection.getStartLine() + 1));
                selectedText = selectedText + nextline;
            }
            previousLine = doc.get(
                    doc.getLineOffset(selection.getStartLine() - 1),
                    doc.getLineLength(selection.getStartLine() - 1));
            if (previousLine.endsWith("\\\r\n")) {
                ///the cursor is on the second line of the multiple line statement
                selectedText = previousLine
                        + selectedText;
            }
        } catch (BadLocationException e) {
            //e.printStackTrace();
        }
        //remove newline char and \
        selectedText = selectedText.replaceAll("\r\n", "")
                .replaceAll("\\\\", " ");

        getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                table.removeAll();
            }
        });

        for (ASTStatement fld : script.getDocDef().getStatementsList()) {
            if (selectedText.replaceAll(" ", "").equals(
                    fld.toString().replaceAll(" ", ""))) {
                Set<AnnotationSetField> flds = fld.getAnnotationsList();
                for (final AnnotationSetField annotation : flds) {
                    getDefault().syncExec(new Runnable() {
                        @Override
                        public void run() {
                            TableItem item = new TableItem(table, SWT.NONE);
                            String name = annotation.getName().getName();
                            String text = annotation.getValue().getText();
                            item.setText(0, name);
                            item.setText(1, text);
                        }
                    });
                }

                Set<AnnotationSetListField> lists = fld.getAnnotationSetList();
                for (final AnnotationSetListField list : lists) {
                    getDefault().syncExec(new Runnable() {
                        @Override
                        public void run() {
                            TableItem item = new TableItem(table, SWT.NONE);
                            String name = list.getObjectIdent().getName();
                            String text = list.getValueList().getText();
                            item.setText(0, name);
                            item.setText(1, text);
                        }
                    });
                }
            }
        }

    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (!(part instanceof BELScriptEditor)) {
            this.activeEditor = null;
            return;
        }
        BELScriptEditor editor = (BELScriptEditor) part;
        if (this.activeEditor == editor) return;

        // Active editor has changed.

        // Don't listen to the old editor.
        sellstnr.removeListener(this.activeEditor, this);

        // Listen to the new editor.
        this.activeEditor = editor;
        sellstnr.addListener(this.activeEditor, this);
    }
}
