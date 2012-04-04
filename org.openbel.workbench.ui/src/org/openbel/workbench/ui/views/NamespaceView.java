package org.openbel.workbench.ui.views;

import static java.lang.String.format;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.UIFunctions.logError;
import static org.openbel.workbench.ui.util.StackUtilities.callerFrame;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.part.ViewPart;
import org.openbel.workbench.core.common.Strings;
import org.openbel.workbench.core.index.NamespaceInfo;
import org.openbel.workbench.core.index.ResourceIndex;

/**
 * {@link NamespaceView} defines the list of {@link NamespaceInfo namespaces}
 * available from the BEL Framework set in preferences. This view allows the
 * user to explore the domain of each {@link NamespaceInfo namespace}.
 */
public class NamespaceView extends ViewPart {

    /**
     * The ID of the view as specified by the extension.
     */
    public static final String ID = "org.openbel.workbench.ui.views.NamespaceView";

    private static final String NS_NAME_FMT = "%s (%s)";
    private Combo combo;
    private int oldSelection = -1;
    private Action detailsAction;
    private Table table;

    /**
     * The constructor.
     */
    public NamespaceView() {
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
        combo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1,
                1));
        combo.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final int selected = combo.getSelectionIndex();

                // skip event if selection hasn't changed
                if (selected == oldSelection) {
                    return;
                }

                final NamespaceInfo ns = getSelectedNamespaceInfo(selected);
                if (ns != null) {
                    final Display display = parent.getDisplay();
                    display.asyncExec(new LoadData(ns, table));
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

        final TableColumn fullColumn = new TableColumn(table, SWT.NONE);
        fullColumn.setWidth(212);
        fullColumn.setText("Value");

        new Label(child, SWT.NONE);
        new Label(child, SWT.NONE);

        // TODO This column resize code might be important when we bring back
        // TODO the encoding for a namespace value.
        //        child.addControlListener(new ControlAdapter() {
        //            @Override
        //            public void controlResized(ControlEvent e) {
        //                Rectangle area = child.getClientArea();
        //                Point preferredSize = table.computeSize(SWT.DEFAULT,
        //                        SWT.DEFAULT);
        //                int width = area.width - (2 * table.getBorderWidth());
        //                if (preferredSize.y > (area.height + table.getHeaderHeight())) {
        //                    // Subtract the scrollbar width from the total column width
        //                    // if a vertical scrollbar will be required
        //                    Point vBarSize = table.getVerticalBar().getSize();
        //                    width -= vBarSize.x;
        //                }
        //                valueColumn.setWidth((int) (width * 100.0f)
        //                        - (6 * table.getBorderWidth()));
        //                table.setSize(area.width, area.height);
        //            }
        //        });

        makeActions();
        makeTableDoubleClick();
        contributeToActionBars();
        loadContent();
    }

    public void loadContent() {
        final ResourceIndex index = getDefault().getResourceIndex();
        if (index == null) {
            // view can load before resource index loads so provide empty
            // content
            return;
        }

        final List<NamespaceInfo> nsl = index.getNamespaces();
        combo.removeAll();
        for (final NamespaceInfo n : nsl) {
            combo.add(format(NS_NAME_FMT, n.getName(), n.getKeyword()));
        }
    }

    private NamespaceInfo getSelectedNamespaceInfo(final int index) {
        if (index >= 0) {
            final ResourceIndex resIndex = getDefault().getResourceIndex();
            if (resIndex == null) {
                logError("resource index is null at " + callerFrame());
                return null;
            }

            final NamespaceInfo ns = resIndex.getNamespaces().get(index);
            return ns;
        }

        return null;
    }

    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager manager) {
        manager.add(detailsAction);
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        manager.add(detailsAction);
    }

    private void makeActions() {
        detailsAction = new Action() {
            @Override
            public void run() {
                launchBrowserForSelection();
            }
        };
        detailsAction.setText("Show Details");
        detailsAction
        .setToolTipText("Provide detailed information from web resource.");
        detailsAction.setImageDescriptor(PlatformUI.getWorkbench()
                .getSharedImages()
                .getImageDescriptor(ISharedImages.IMG_LCL_LINKTO_HELP));
    }

    private void makeTableDoubleClick() {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent arg0) {
                launchBrowserForSelection();
            }

            @Override
            public void mouseUp(MouseEvent ev) {
            }

            @Override
            public void mouseDown(MouseEvent ev) {
            }
        });
    }

    private void launchBrowserForSelection() {
        // find selected namespace and entity
        final NamespaceInfo ns = getSelectedNamespaceInfo(combo
                .getSelectionIndex());
        int si = table.getSelectionIndex();
        String entity = table.getItem(si).getText();

        String link = ns.getQueryValueURL();
        if (link == null) {
            return;
        }

        try {
            // open browser for query value url + value
            IWebBrowser netscape = PlatformUI.getWorkbench()
                    .getBrowserSupport().createBrowser("entity-details");
            link = link.replace("[VALUE]",
                    URLEncoder.encode(entity, Strings.UTF_8));
            netscape.openURL(new URL(link));
        } catch (PartInitException e) {
            logError("Cound not load browser.");
            logError(e);
        } catch (MalformedURLException e) {
            logError("Entity url of '" + link + "' is invalid.");
            logError(e);
        } catch (UnsupportedEncodingException e) {
            logError("Entity url of '" + link + "' is invalid.");
            logError(e);
        }
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        combo.setFocus();
    }
}
