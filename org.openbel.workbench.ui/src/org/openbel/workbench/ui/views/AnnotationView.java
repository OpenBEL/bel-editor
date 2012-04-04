package org.openbel.workbench.ui.views;

import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIFunctions.logError;
import static org.openbel.workbench.ui.util.StackUtilities.callerFrame;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.openbel.workbench.core.index.AnnotationInfo;
import org.openbel.workbench.core.index.ResourceIndex;

/**
 * {@link AnnotationView} defines the list of {@link AnnotationInfo annotations}
 * available from the BEL Framework set in preferences. This view allows the
 * user to explore the domain of each {@link AnnotationInfo annotation}.
 */
public class AnnotationView extends ViewPart {

    /**
     * The ID of the view as specified by the extension.
     */
    public static final String ID = "org.openbel.workbench.ui.views.AnnotationView";

    private Combo combo;
    private int oldSelection = -1;
    private Table table;

    /**
     * The constructor.
     */
    public AnnotationView() {
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    @Override
    public void createPartControl(final Composite parent) {
        final Composite child = new Composite(parent, SWT.NONE);
        child.setLayout(new GridLayout(2, false));

        combo = new Combo(child, SWT.NONE);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
                1));
        combo.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final int selected = combo.getSelectionIndex();

                // skip event if selection hasn't changed
                if (selected == oldSelection) {
                    return;
                }

                final AnnotationInfo ai = getSelectedAnnotationInfo(selected);
                if (ai != null) {
                    final Display display = parent.getDisplay();
                    display.asyncExec(new LoadData(ai, table));
                    oldSelection = selected;
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });
        new Label(child, SWT.NONE);

        table = new Table(child, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

        final TableColumn valueColumn = new TableColumn(table, SWT.NONE);
        valueColumn.setWidth(212);
        valueColumn.setText("Value");

        new Label(child, SWT.NONE);
        new Label(child, SWT.NONE);

        child.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                Rectangle area = child.getClientArea();
                Point preferredSize = table.computeSize(SWT.DEFAULT,
                        SWT.DEFAULT);
                int width = area.width - (2 * table.getBorderWidth());
                if (preferredSize.y > (area.height + table.getHeaderHeight())) {
                    // Subtract the scrollbar width from the total column width
                    // if a vertical scrollbar will be required
                    Point vBarSize = table.getVerticalBar().getSize();
                    width -= vBarSize.x;
                }
                valueColumn.setWidth((int) (width * 100.0f)
                        - (6 * table.getBorderWidth()));
                table.setSize(area.width, area.height);
            }
        });
    }

    public void loadContent() {
        final ResourceIndex index = getDefault().getResourceIndex();
        if (index == null) {
            // view can load before resource index loads so provide empty
            // content
            return;
        }

        final List<AnnotationInfo> anl = index.getAnnotations();
        combo.removeAll();
        for (final AnnotationInfo a : anl) {
            combo.add(a.getName());
        }
    }

    private AnnotationInfo getSelectedAnnotationInfo(final int index) {
        if (index >= 0) {
            final ResourceIndex resIndex = getDefault().getResourceIndex();
            if (resIndex == null) {
                logError("resource index is null at " + callerFrame());
                return null;
            }

            final AnnotationInfo ns = resIndex.getAnnotations().get(index);
            return ns;
        }

        return null;
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        combo.setFocus();
    }
}
