package org.openbel.editor.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.openbel.editor.core.parser.BELScriptDocument;
import org.openbel.editor.core.parser.BELScriptSourceParserFactory;
import org.openbel.editor.core.parser.ast.ASTStatement;
import org.openbel.editor.core.parser.ast.AnnotationSetField;

public class AstTreeViewPart extends ViewPart implements
        ISelectionListener
{
    public static final String ID = "org.openbel.editor.ui.AstTreeViewPart";
    private ISourceParser createSourceParser;
    private Table table;
    private final List<AnnotationSetField> fields = new ArrayList<AnnotationSetField>();

    public AstTreeViewPart() {
    }

    @Override
    public void createPartControl(Composite parent) {
        getSite().getWorkbenchWindow().getSelectionService()
                .addSelectionListener(this);
        table = new Table(parent, SWT.MULTI | SWT.BORDER
                | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        String[] titles = { "Description", "Resource" };
        for (String title : titles) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(title);
        }

        for (int i = 0; i < titles.length; i++) {
            table.getColumn(i).pack();
        }
        table.setSize(table.computeSize(SWT.DEFAULT, 200));

        createSourceParser = new BELScriptSourceParserFactory()
                .createSourceParser();

    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {

        if (selection instanceof ITextSelection) {
            String selectedText = ((ITextSelection) selection).getText();

            table.removeAll();
            IEditorInput editorInput = part.getSite()
                    .getWorkbenchWindow().getActivePage().getActiveEditor()
                    .getEditorInput();

            IFile file = (IFile) editorInput.getAdapter(IFile.class);
            BELScriptDocument script = null;
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        file
                                .getContents()));

                String read = br.readLine();

                while (read != null) {
                    sb.append(read);
                    sb.append("\n");
                    read = br.readLine();

                }

                script = (BELScriptDocument) createSourceParser.parse(
                        new ModuleSource(sb.toString()
                                .toCharArray()), null);
            } catch (CoreException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (ASTStatement field : script.getStatementsList()) {
                if (selectedText.equals(field.toString())) {
                    for (AnnotationSetField field2 : field.getAnnotationsList()) {
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(0, field2.getName().getName());
                        item.setText(1, field2.getValue().getText());
                    }
                }
            }

        }
    }
}
