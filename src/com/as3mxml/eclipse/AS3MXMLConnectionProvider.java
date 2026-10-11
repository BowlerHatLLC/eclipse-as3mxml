package com.as3mxml.eclipse;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.runtime.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.osgi.framework.Bundle;

import com.as3mxml.eclipse.preferences.AS3MXMLPreferences;

public class AS3MXMLConnectionProvider extends ProcessStreamConnectionProvider {
	public AS3MXMLConnectionProvider() {
		Bundle bundle = Activator.getDefault().getBundle();
		Path languageServerDir = Path.EMPTY;
		try {
			languageServerDir = new Path(FileLocator.toFileURL(FileLocator.find(bundle, new Path("language-server"), null)).getPath());
		}
		catch(IOException e) {
			e.printStackTrace(System.err);
		}
		
		String frameworkSDKHome = Activator.getDefault().getPreferenceStore().getString(AS3MXMLPreferences.PREFERENCE_AS3MXML_SDK_FRAMEWORK);
		
		List<String> commands = new ArrayList<>();
		commands.add("java");
        commands.add("-Dfile.encoding=UTF8");
		commands.add("-Droyalelib=" + frameworkSDKHome + "/frameworks");
        commands.add("-cp");
		commands.add(languageServerDir.toOSString() + "/bundled-compiler/*" + File.pathSeparator + languageServerDir.toOSString() + "/bin/*");
		commands.add("com.as3mxml.vscode.Main");
		setCommands(commands);
		setWorkingDirectory(Platform.getLocation().toOSString());
	}

	@Override
	public Map<String, Object> getInitializationOptions(final URI projectRootUri) {
		final var opts = new HashMap<String, Object>();

		opts.put("preferredRoyaleTarget", "SWF");
		opts.put("notifyActiveProject", true);
		
		return opts;
	}

	@Override
	public String toString() {
		return "ActionScript & MXML Connection Provider: " + super.toString();
	}
	
	
}
