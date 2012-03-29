package org.openbel.workbench.ui.preferences;

import static org.eclipse.debug.internal.ui.SWTFactory.createComposite;
import static org.eclipse.debug.internal.ui.SWTFactory.createPushButton;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.SINGLE;
import static org.eclipse.swt.layout.GridData.FILL_BOTH;
import static org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
import static org.openbel.workbench.core.common.BELUtilities.constrainedHashMap;
import static org.openbel.workbench.core.common.BELUtilities.createDirectory;
import static org.openbel.workbench.core.common.BELUtilities.hasLength;
import static org.openbel.workbench.core.common.PathConstants.SYSCONFIG_FILENAME;
import static org.openbel.workbench.ui.Activator.*;
import static org.openbel.workbench.ui.util.StackUtilities.myFrame;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateCytoscape;
import static org.openbel.workbench.ui.util.ValidationUtilities.validateFramework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.openbel.workbench.core.index.AnnotationInfo;
import org.openbel.workbench.core.index.NamespaceInfo;
import org.openbel.workbench.core.index.Resource;
import org.openbel.workbench.core.index.ResourceIndex;
import org.openbel.workbench.core.record.Records;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.util.ValidationUtilities.FileState;
import org.openbel.workbench.ui.views.AnnotationView;
import org.openbel.workbench.ui.views.NamespaceView;
import org.openbel.workbench.ui.views.ResourceView;

/**
 * {@link BELFrameworkPreferencePage} represents a workbench preference page
 * that allows the user to set the BEL Framework home location.
 */
public class BELFrameworkPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {
    private final static String err1;
    private final static String err2;
    private final static String err3;
    private final static String err4;
    static {
        err1 = "Missing BEL Framework installation path.";
        err2 = "Invalid BEL Framework installation path.";
        err3 = "Cytoscape support is disabled.";
        err4 = "Invalid Cytoscape path, support is disabled.";
    }

    private String bfPrefValue;
    private String cyPrefValue;
    private final ISchedulingRule indexRule = new ResourceRule();
    private final ISchedulingRule resourceRule = new ResourceRule();
    private Text txtBELFrameworkInstall;
    private Button btnBFBrowse;
    private Text txtCytoscapeInstall;
    private Button btnCYBrowse;

    /**
     * BEL framework preference page.
     */
    public BELFrameworkPreferencePage() {
        this.setDescription("Specify paths to software installations.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(Composite parent) {
        Font f = parent.getFont();
        Composite composite = createComposite(parent, f, 1, 1, FILL_BOTH);
        //setControl(composite);
        ((GridLayout) composite.getLayout()).verticalSpacing = 5;

        Listener listener = new Listener();

        Group g1 = new Group(composite, NONE);
        g1.setText("&BEL Framework installation:");
        GridData gd = new GridData(FILL_HORIZONTAL);
        g1.setLayoutData(gd);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        g1.setLayout(gl);
        g1.setFont(f);
        txtBELFrameworkInstall = new Text(g1, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtBELFrameworkInstall.setLayoutData(gd);
        txtBELFrameworkInstall.setFont(f);
        txtBELFrameworkInstall.setEnabled(false);
        btnBFBrowse = createPushButton(g1, "&Browse...", null);
        btnBFBrowse.addSelectionListener(listener);

        Group g2 = new Group(composite, NONE);
        g2.setText("&Cytoscape installation:");
        gd = new GridData(FILL_HORIZONTAL);
        g2.setLayoutData(gd);
        g2.setLayout(gl);
        g2.setFont(f);
        txtCytoscapeInstall = new Text(g2, SINGLE | BORDER);
        txtCytoscapeInstall.setLayoutData(gd);
        txtCytoscapeInstall.setFont(f);
        txtCytoscapeInstall.setEnabled(false);
        btnCYBrowse = createPushButton(g2, "Br&owse...", null);
        btnCYBrowse.addSelectionListener(listener);

        if (bfPrefValue != null) {
            txtBELFrameworkInstall.setText(bfPrefValue);
        }

        IPreferenceStore prefs = getDefault().getPreferenceStore();
        if (prefs.contains(BF_PREF_KEY)) {
            txtBELFrameworkInstall.setText(prefs.getString(BF_PREF_KEY));
        } else {
            initializeDefaults();
        }
        if (prefs.contains(CY_PREF_KEY)) {
            txtCytoscapeInstall.setText(prefs.getString(CY_PREF_KEY));
        }

        update();
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        initializeDefaults();
        txtBELFrameworkInstall.setText(ENV_BELFRAMEWORK_HOME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench wb) {
    }

    /**
     * <p>
     * Retrieve the Open BEL Workbench preference store.
     * </p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        return getDefault().getPreferenceStore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performApply() {
        logError("(unbound apply handler - " + myFrame() + ")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        doGetPreferenceStore().setValue(BF_PREF_KEY, bfPrefValue);
        if (hasLength(cyPrefValue)) {
            doGetPreferenceStore().setValue(CY_PREF_KEY, cyPrefValue);
        }

        // load resource view
        final Job vj = new Job("Loading Resource Index") {

            @Override
            protected IStatus run(IProgressMonitor m) {
                m.beginTask("Loading the resource index.", 100);
                if (!getDefault().reloadResourceView()) {
                    IPreferenceStore ps = Activator.getDefault()
                            .getPreferenceStore();
                    final StringBuilder b = new StringBuilder(
                            ps.getString(BF_PREF_KEY))
                            .append(File.separator).append("config")
                            .append(File.separator)
                            .append(SYSCONFIG_FILENAME);

                    Display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            setErrorMessage("Cannot process system configuration in '"
                                    + b.toString() + "'.");
                        }
                    });
                }
                m.worked(50);

                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final IWorkbenchPage page = PlatformUI
                                    .getWorkbench()
                                    .getActiveWorkbenchWindow()
                                    .getActivePage();
                            final IViewPart rpart = page
                                    .showView(ResourceView.ID);
                            if ((rpart != null)
                                    && (rpart instanceof ResourceView)) {
                                final ResourceView rview = (ResourceView) rpart;
                                rview.loadContent();
                            }

                            final IViewPart npart = page
                                    .findView(NamespaceView.ID);
                            if ((npart != null)
                                    && (npart instanceof NamespaceView)) {
                                final NamespaceView nview = (NamespaceView) npart;
                                nview.loadContent();
                            }

                            final IViewPart apart = page
                                    .findView(AnnotationView.ID);
                            if ((apart != null)
                                    && (apart instanceof AnnotationView)) {
                                final AnnotationView aview = (AnnotationView) apart;
                                aview.loadContent();
                            }

                        } catch (PartInitException e) {
                            logError(e);
                        }
                    }
                });
                m.worked(50);

                return Status.OK_STATUS;
            }
        };
        vj.setRule(indexRule);
        vj.schedule();

        // load annotation/namespace resources in background job
        final Job rj = new Job("Loading Resources") {

            /**
             * This job builds and loads all annotations/namespace resources.
             * 
             * {@inheritDoc}
             */
            @Override
            protected IStatus run(IProgressMonitor m) {
                m.beginTask("Loading all namespaces and resources.", 100);

                final ResourceIndex index = getDefault().getResourceIndex();
                final String rloc = getPluginLocation().append("resources")
                        .makeAbsolute().toString();
                createDirectory(rloc);
                final File rlocFile = new File(rloc);

                try {
                    final Records records = new Records(rlocFile);
                    records.build(index);

                    final List<AnnotationInfo> anl = index.getAnnotations();
                    final List<NamespaceInfo> nsl = index.getNamespaces();
                    int c = anl.size() + nsl.size();
                    final Map<Resource, List<String>> resourceCatalog = constrainedHashMap(c);

                    final List<Resource> resources = new ArrayList<Resource>(
                            c);
                    resources.addAll(anl);
                    resources.addAll(nsl);

                    int workAmt = (int) (((float) 1 / resources.size()) * 100);
                    for (final Resource r : resources) {
                        m.subTask("Loading resource: " + r);
                        resourceCatalog.put(r,
                                records.retrieve(r.getResourceLocation()));
                        m.worked(workAmt);
                    }

                    // set resources into activator
                    getDefault().setResourceCatalog(resourceCatalog);
                } catch (IOException e) {
                    logError(e);
                }

                return Status.OK_STATUS;
            }
        };
        rj.setRule(resourceRule);
        rj.schedule();

        return true;
    }

    /**
     * Set default preference value and validate.
     */
    private void initializeDefaults() {
        bfPrefValue = ENV_BELFRAMEWORK_HOME;
        update();
    }

    private void update() {
        String bftxt = txtBELFrameworkInstall.getText();
        boolean haveBF = hasLength(bftxt);
        String cytxt = txtCytoscapeInstall.getText();
        boolean haveCY = hasLength(cytxt);

        if (!haveBF) {
            setErrorMessage(err1);
            setValid(false);
            return;
        }
        FileState state = validateFramework(bftxt);
        if (state != FileState.OK) {
            setErrorMessage(err2);
            setValid(false);
            return;
        }

        bfPrefValue = bftxt;
        setValid(true);

        if (!haveCY) {
            setErrorMessage(err3);
            return;
        }

        state = validateCytoscape(cytxt);
        if (state != FileState.OK) {
            setErrorMessage(err4);
            return;
        }
        setErrorMessage(null);
    }

    private class ResourceRule implements ISchedulingRule {

        @Override
        public boolean contains(ISchedulingRule rule) {
            return rule == this;
        }

        @Override
        public boolean isConflicting(ISchedulingRule rule) {
            return rule == this;
        }
    }

    private class Listener implements SelectionListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetDefaultSelected(SelectionEvent ev) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(SelectionEvent ev) {
            Object src = ev.getSource();
            if (src == btnBFBrowse) {
                final DirectoryDialog fd = new DirectoryDialog(getShell());
                fd.setMessage("Locate the BEL Framework installation");
                fd.setText("Open");
                fd.setFilterPath(System.getenv("user.dir")); //$NON-NLS-1$
                String selected = fd.open();
                if (selected != null) {
                    bfPrefValue = selected;
                    txtBELFrameworkInstall.setText(selected);
                    update();
                }
            } else if (src == btnCYBrowse) {
                final DirectoryDialog fd = new DirectoryDialog(getShell());
                fd.setMessage("Locate the Cytoscape installation");
                fd.setText("Open");
                fd.setFilterPath(System.getenv("user.dir")); //$NON-NLS-1$
                String selected = fd.open();
                if (selected != null) {
                    cyPrefValue = selected;
                    txtCytoscapeInstall.setText(selected);
                    update();
                }
            }
        }
    }
}
