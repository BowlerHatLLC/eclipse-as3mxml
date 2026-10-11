package com.as3mxml.eclipse.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.as3mxml.eclipse.Activator;

public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public GeneralPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
	}

   @Override
   public void init(final IWorkbench workbench) {
		IPreferenceStore store =
			Activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
   }

	@Override
	protected void createFieldEditors() {
	}
}
