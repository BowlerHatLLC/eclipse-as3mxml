package com.as3mxml.eclipse.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;	

import com.as3mxml.eclipse.Activator;

public class InstalledSDKsPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public InstalledSDKsPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
		IPreferenceStore store =
				Activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
	}

   @Override
   public void init(final IWorkbench workbench) {
   }

	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor("as3mxml.sdk.framework", "SDK Path", getFieldEditorParent()));
	}

    @Override
    public boolean performOk() {
        boolean result = super.performOk();
        if (result) {
        	AS3MXMLPreferences.sendDidChangeConfiguration();
        }
        return result;
    }
}
