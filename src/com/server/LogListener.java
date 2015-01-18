package com.server;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * User: links Date: 7/15/14
 */
public class LogListener
{

	/**
	 * private static String logString = "";
	 * 
	 * public LogListener(Server server) { server.getLogger().addHandler(this);
	 * } public void publish(LogRecord lr) { logString +=
	 * "["+lr.getLevel()+"] "+lr.getMillis()+" "+lr.getMessage() + "\n"; }
	 * public void close() { flush(); } public void flush() { logString = ""; }
	 * public JSONObject toJSONObject() { HashMap<String, String> hm = new
	 * HashMap(); hm.put("log", logString);
	 * com.tennysonholloway.net.gcs.LogListener.jsonObject = new JSONObject();
	 * com.tennysonholloway.net.gcs.LogListener.jsonObject.putAll(hm); return
	 * com.tennysonholloway.net.gcs.LogListener.jsonObject; }
	 **/
}