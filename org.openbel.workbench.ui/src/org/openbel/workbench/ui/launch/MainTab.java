package org.openbel.workbench.ui.launch;

import static java.util.Collections.emptyMap;
import static org.eclipse.debug.internal.ui.SWTFactory.createComposite;
import static org.eclipse.swt.SWT.*;
import static org.eclipse.swt.layout.GridData.FILL_BOTH;
import static org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
import static org.openbel.workbench.core.CoreConstants.BUILDER_FILE_NAME;
import static org.openbel.workbench.core.common.BELUtilities.hasLength;
import static org.openbel.workbench.core.common.BELUtilities.noItems;
import static org.openbel.workbench.core.common.BELUtilities.noLength;
import static org.openbel.workbench.ui.Activator.getDefault;
import static org.openbel.workbench.ui.Activator.logError;
import static org.openbel.workbench.ui.launch.LaunchDelegate.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.openbel.workbench.ui.Activator;
import org.openbel.workbench.ui.DocumentContentProvider;
import org.openbel.workbench.ui.OpenBELLabelProvider;

/**
 * The main tab responsible for selecting the project, builder, and document
 * groups/documents.
 */
public class MainTab extends AbstractLaunchConfigurationTab {
    private final ILabelProvider provider;

    private Button btnBuilder;
    private Button btnProject;
    private Text txtBuilder;
    private Text txtProject;
    private Text txtName;
    private Text txtDescription;
    private CheckboxTreeViewer treeViewer;

    /**
     * Creates the main tab.
     */
    public MainTab() {
        provider = new OpenBELLabelProvider();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControl(Composite parent) {
        Font f = parent.getFont();
        Composite composite = createComposite(parent, f, 1, 1, FILL_BOTH);
        setControl(composite);
        ((GridLayout) composite.getLayout()).verticalSpacing = 5;

        Listener listener = new Listener();

        Group g1 = new Group(composite, NONE);
        g1.setText("&Project:");
        GridData gd = new GridData(FILL_HORIZONTAL);
        g1.setLayoutData(gd);
        GridLayout gl = new GridLayout();
        gl.numColumns = 2;
        g1.setLayout(gl);
        g1.setFont(f);
        txtProject = new Text(g1, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtProject.setLayoutData(gd);
        txtProject.setFont(f);
        txtProject.addModifyListener(listener);
        txtProject.setEnabled(false);
        btnProject = createPushButton(g1, "&Browse...", null);
        btnProject.addSelectionListener(listener);

        Group g2 = new Group(composite, NONE);
        g2.setText("&Default KAM Builder:");
        gd = new GridData(FILL_HORIZONTAL);
        g2.setLayoutData(gd);
        g2.setLayout(gl);
        g2.setFont(f);
        txtBuilder = new Text(g2, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtBuilder.setLayoutData(gd);
        txtBuilder.setFont(f);
        txtBuilder.addModifyListener(listener);
        txtBuilder.setEnabled(false);
        btnBuilder = createPushButton(g2, "Br&owse...", null);
        btnBuilder.addSelectionListener(listener);

        Group g3 = new Group(composite, NONE);
        g3.setText("KAM Nam&e:");
        gd = new GridData(FILL_HORIZONTAL);
        g3.setLayoutData(gd);
        g3.setLayout(gl);
        g3.setFont(f);
        txtName = new Text(g3, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtName.setLayoutData(gd);
        txtName.setFont(f);
        txtName.addModifyListener(listener);

        Group g4 = new Group(composite, NONE);
        g4.setText("KAM De&scription:");
        gd = new GridData(FILL_HORIZONTAL);
        g4.setLayoutData(gd);
        g4.setLayout(gl);
        g4.setFont(f);
        txtDescription = new Text(g4, SINGLE | BORDER);
        gd = new GridData(FILL_HORIZONTAL);
        txtDescription.setLayoutData(gd);
        txtDescription.setFont(f);
        txtDescription.addModifyListener(listener);

        Label lbl = new Label(composite, NONE);
        lbl.setText("Documents and Document &Groups:");
        int style = FULL_SELECTION | BORDER | H_SCROLL | V_SCROLL | MULTI;
        treeViewer = new CheckboxTreeViewer(composite, style);
        gd = new GridData(FILL_BOTH);
        treeViewer.getTree().setLayoutData(gd);
        // FIXME use a single project document provider
        treeViewer.setContentProvider(new DocumentContentProvider());
        treeViewer.setLabelProvider(provider);
        treeViewer.setInput("root");
        treeViewer.expandAll();

        // XXX support for checking/unchecking children of tree nodes
        treeViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent ev) {
                if (ev.getChecked()) {
                    treeViewer.setSubtreeChecked(ev.getElement(), true);
                } else {
                    treeViewer.setSubtreeChecked(ev.getElement(), false);
                }
                // XXX trigger isValid/canSave
                updateLaunchConfigurationDialog();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Main";
    }

    /**
     * Sets widget values from launch attributes.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initializeFrom(ILaunchConfiguration launch) {
        Map<String, String> none = emptyMap();
        Map<String, String> map;
        try {
            map = launch.getAttribute(ATTR_LAUNCH, none);
        } catch (CoreException e) {
            logError(e);
            return;
        }

        String prjname = map.get(ATTR_PRJ);
        if (prjname == null) {
            setErrorMessage("Select a project.");
        } else {
            IProject prj = getDefault().getProject(prjname);
            if (prj == null) {
                setErrorMessage("Select a project.");
            } else {
                txtProject.setText(prjname);
                String bldrname = map.get(ATTR_BLDR);
                if (bldrname != null) {
                    IFile bldr = prj.getFile(bldrname);
                    txtBuilder.setText(display(prj, bldr));
                }
            }
        }

        String kamname = map.get(KAM_NAME);
        if (hasLength(kamname)) {
            txtName.setText(kamname);
        }

        String kamdesc = map.get(KAM_DESC);
        if (hasLength(kamdesc)) {
            txtDescription.setText(kamdesc);
        }

        String selection = map.get(ATTR_DOC);
        if (noLength(selection)) {
            return;
        }

        String[] tokens = tokenize(selection);
        List<Object> objs = new ArrayList<Object>();
        Activator act = getDefault();
        for (final String token : tokens) {
            IResource rsrc = act.getResource(token);
            if (rsrc == null) continue;
            if (rsrc instanceof IProject) {
                //IProject p = (IProject) rsrc;
                objs.add(rsrc);
            } else if (rsrc instanceof IFolder) {
                //IFolder f = (IFolder) rsrc;
                objs.add(rsrc);
            } else if (rsrc instanceof IFile) {
                //IFile f = (IFile) rsrc;
                objs.add(rsrc);
            }
        }
        Object[] selobjs = objs.toArray(new Object[0]);
        treeViewer.setCheckedElements(selobjs);
    }

    /**
     * Returns {@code true} if the state of this tab matches the launch
     * argument, {@code false} otherwise.
     * 
     * @return boolean
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(ILaunchConfiguration launch) {
        // XXX isValid affects the ability to run
        Map<String, String> none = emptyMap();
        Map<String, String> map;
        try {
            map = launch.getAttribute(ATTR_LAUNCH, none);
        } catch (CoreException e) {
            logError(e);
            return false;
        }

        String prjname = map.get(ATTR_PRJ);
        if (noLength(prjname)) {
            setErrorMessage("Select a project.");
            return false;
        }
        IProject prj = getDefault().getProject(prjname);
        if (prj == null) {
            setErrorMessage("Missing project \"" + prjname + "\".");
            return false;
        }
        String kamname = map.get(KAM_NAME);
        if (noLength(kamname)) {
            setErrorMessage("Enter a KAM name.");
            return false;
        }

        String kamdesc = map.get(KAM_DESC);
        if (noLength(kamdesc)) {
            setErrorMessage("Enter a KAM description.");
            return false;
        }

        String bldr = map.get(ATTR_BLDR);
        if (noLength(bldr)) {
            setErrorMessage("Choose a default KAM builder.");
            return false;
        }

        String selection = map.get(ATTR_DOC);
        if (noLength(selection) || (tokenize(selection).length == 0)) {
            setErrorMessage("Select documents or document groups.");
            return false;
        }
        setErrorMessage(null);
        return true;
    }

    /**
     * Returns {@code true} if the widgets on this tab are in a state suitable
     * for saving, {@code false} otherwise.
     * 
     * @return boolean
     */
    @Override
    public boolean canSave() {
        // XXX canSave affects the ability to apply
        if (noLength(txtBuilder.getText())) {
            return false;
        }
        if (noLength(txtBuilder.getText())) {
            return false;
        }
        if (noLength(txtName.getText())) {
            return false;
        }
        if (noLength(txtDescription.getText())) {
            return false;
        }
        Object[] objs = treeViewer.getCheckedElements();
        if (noItems(objs)) {
            return false;
        }
        return true;
    }

    /**
     * Reads current values from widgets and sets attributes in working copy.
     */
    @Override
    public void performApply(ILaunchConfigurationWorkingCopy launch) {
        String prjname = txtProject.getText();
        String bldrdisp = txtBuilder.getText();
        String kamname = txtName.getText();
        String kamdesc = txtDescription.getText();
        String bldr = bldrdisp.split(" ")[0];

        Map<String, String> map = new HashMap<String, String>();
        launch.setAttribute(ATTR_LAUNCH, map);
        map.put(ATTR_PRJ, prjname);
        map.put(ATTR_BLDR, bldr);
        map.put(KAM_NAME, kamname);
        map.put(KAM_DESC, kamdesc);

        final List<String> elements = new ArrayList<String>();
        Object[] objs = treeViewer.getCheckedElements();
        for (final Object o : objs) {
            elements.add(o.toString());
        }
        if (!elements.isEmpty()) {
            String selection = marshal(elements.toArray(new String[0]));
            map.put(ATTR_DOC, selection);
        } else {
            map.remove(ATTR_DOC);
        }
    }

    /**
     * Called when a new config is created.
     */
    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy launch) {
        final Activator act = getDefault();
        IProject[] prjs = act.getOpenedBELProjects();
        List<String> selPrjs = act.getSelectedProjects();
        if ((prjs.length != 1) && (selPrjs.size() != 1)) {
            return;
        }
        IProject prj;
        if (prjs.length == 1) {
            prj = prjs[0];
        } else {
            prj = act.getProject(selPrjs.get(0));
        }
        Map<String, String> value = new HashMap<String, String>();
        // Default txtProject to the only open BEL project.
        IFile file = prj.getFile(BUILDER_FILE_NAME);
        // TODO user may have removed file from project (file is null)
        value.put(ATTR_PRJ, prj.getName());
        value.put(ATTR_BLDR, file.getName());
        launch.setAttribute(ATTR_LAUNCH, value);
    }

    private static String display(IProject p, IFile f) {
        String filename = f.getName();
        String prjname = p.getName();
        return filename.concat(" - ").concat(prjname);
    }

    private static String marshal(String... strings) {
        String ret = "";
        for (final String string : strings) {
            ret = ret.concat(string).concat(";");
        }
        return ret;
    }

    private class Listener implements ModifyListener, SelectionListener {

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
            if (src == btnProject) {
                IProject p = chooseProject();
                if (p == null) {
                    return;
                }
                String name = p.getName();
                txtProject.setText(name);
                if (noLength((txtBuilder.getText()))) {
                    IFile bldr = getDefault().getBuilder(p);
                    if (bldr != null) {
                        txtBuilder.setText(display(p, bldr));
                    }
                }
                // XXX trigger isValid/canSave
                updateLaunchConfigurationDialog();
            } else if (src == btnBuilder) {
                IFile f = chooseFile();
                if (f == null) {
                    return;
                }
                txtBuilder.setText(display(f.getProject(), f));
                // XXX trigger isValid/canSave
                updateLaunchConfigurationDialog();
            }
        }

        private IFile chooseFile() {
            IFile[] files = getDefault().getBuilders();
            Shell shell = getShell();
            SelectionDialog d = new ResourceListSelectionDialog(shell, files);
            int result = d.open();
            if (result == Window.OK) {
                Object[] objs = d.getResult();
                return (IFile) objs[0];
            }

            return null;
        }

        private IProject chooseProject() {
            IProject[] prjs = getDefault().getOpenedBELProjects();
            ElementListSelectionDialog prjdialog;
            prjdialog = new ElementListSelectionDialog(getShell(), provider);
            prjdialog.setElements(prjs);
            int result = prjdialog.open();
            if (result == Window.OK) {
                Object[] objs = prjdialog.getResult();
                return (IProject) objs[0];
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void modifyText(ModifyEvent ev) {
            Object src = ev.getSource();
            if (src == txtName) {
                updateLaunchConfigurationDialog();
            } else if (src == txtDescription) {
                updateLaunchConfigurationDialog();
            }
        }
    }

}
