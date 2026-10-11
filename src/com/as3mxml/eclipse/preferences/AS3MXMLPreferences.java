package com.as3mxml.eclipse.preferences;

import java.util.concurrent.CompletableFuture;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.lsp4e.LanguageServers;
import org.eclipse.lsp4j.DidChangeConfigurationParams;

import com.as3mxml.eclipse.Activator;
import com.google.gson.JsonObject;

public class AS3MXMLPreferences {
	public static final String PREFERENCE_AS3MXML_SDK_FRAMEWORK = "as3mxml.sdk.framework";
	
	public static void sendDidChangeConfiguration()
	{
		IPreferenceStore store =
				Activator.getDefault().getPreferenceStore();
    	JsonObject settings = new JsonObject();
    	JsonObject as3mxmlSettings = new JsonObject();
    	JsonObject sdkSettings = new JsonObject();
    	as3mxmlSettings.add("sdk", sdkSettings);
    	sdkSettings.addProperty("framework", store.getString(PREFERENCE_AS3MXML_SDK_FRAMEWORK));
    	settings.add("as3mxml", as3mxmlSettings);
    	
    	DidChangeConfigurationParams params = new DidChangeConfigurationParams(settings);
    	LanguageServers
    		.forProject(null)
    		.collectAll((w, lsFuture) -> CompletableFuture.completedFuture(lsFuture))
    		.thenAccept(servers -> servers.forEach(server -> server.getWorkspaceService().didChangeConfiguration(params)));
	}
}
