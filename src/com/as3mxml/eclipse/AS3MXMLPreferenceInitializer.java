package com.as3mxml.eclipse;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class AS3MXMLPreferenceInitializer extends AbstractPreferenceInitializer {

	public static String explicitSdkFrameworkPreference = "as3mxml.sdk.framework"; //$NON-NLS-1$

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.setDefault(explicitSdkFrameworkPreference, "c:/Users/josht/Development/flash/sdks/Flex4.16.1_AIR32");
	}
}
