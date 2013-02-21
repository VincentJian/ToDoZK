package org.zkoss.todoZK;

import org.zkoss.zk.ui.util.Clients;

public class Utils {
	/**
	 * The same as <code>$("#"+id).load(url)</code> in jQuery.
	 * @param id
	 * @param url
	 */
	public static void changeContent(String id, String url) {
		Clients.evalJavaScript("jq('#" + id + "').load('"+url+"');");
	}

	public static int getIndex(String[] array, String string) {
		for(int i=0; i<array.length; i++){
			if(array[i].equals(string)){
				return i;
			}
		}
		return -1;
	}
}
