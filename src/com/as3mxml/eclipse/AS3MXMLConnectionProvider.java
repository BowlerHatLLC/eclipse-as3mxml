package com.as3mxml.eclipse;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Path;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.osgi.framework.Bundle;

public class AS3MXMLConnectionProvider extends ProcessStreamConnectionProvider {
	public AS3MXMLConnectionProvider() {
		Bundle bundle = Activator.getDefault().getBundle();
		Path bundleDir = Path.EMPTY;
		try {
			bundleDir = new Path(FileLocator.toFileURL(FileLocator.find(bundle, new Path("language-server"), null)).getPath());
		}
		catch(IOException e) {}
		
		String frameworkSDKHome = Activator.getDefault().getPreferenceStore().getString(AS3MXMLPreferenceInitializer.explicitSdkFrameworkPreference);
		
		List<String> commands = new ArrayList<>();
		commands.add("java");
        commands.add("-Dfile.encoding=UTF8");
		commands.add("-Droyalelib=" + frameworkSDKHome + "/frameworks");
        commands.add("-cp");
		commands.add(bundleDir.toOSString() + "/bundled-compiler/*" + File.pathSeparator + bundleDir.toOSString() + "/bin/*");
		commands.add("com.as3mxml.vscode.Main");
		setCommands(commands);
		setWorkingDirectory(Platform.getLocation().toOSString());
	}

	@Override
	public String toString() {
		return "ActionScript & MXML Connection Provider: " + super.toString();
	}
}
