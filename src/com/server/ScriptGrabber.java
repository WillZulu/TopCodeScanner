package com.server;


/**
 * User: links Date: 5/8/14
 */
public class ScriptGrabber
{
//	private static final String GCS_PREFIX = "scripts_";
//
//	private static final String SCRIPTS_DIR = "scripts/";
//
//	public static void grabScriptsFor(String playerName, Channel channel) throws ParseException
//	{
//		CloudObject playerScriptFile = GCS.find(GCS.getDefaultBucketName(), channel.getToken());
//		//System.err.println("trying to find scripts for player:"+playerName+" on channel: "+channel.getToken());
//		if (playerScriptFile == null)
//		{
//			//System.err.println("Could not find GCS playerScriptFile for: "+playerName+" on channel" +
//			//        ": "+channel.getToken());
//			return;
//		}
//		JSONObject json = playerScriptFile.getValue();
//		//System.err.println(json.toJSONString());
//		JSONParser p = new JSONParser();
//		JSONObject data = (JSONObject) p.parse((String) json.get("data"));
//		JSONObject scripts = (JSONObject) data.get("scripts");
//
//		if (scripts == null)
//		{
//			//System.out.println("Scripts were null");
//			return;
//
//		}
//
//		File playerScriptDirectory = new File(SCRIPTS_DIR + playerName + "/");
//		if (!playerScriptDirectory.exists())
//		{
//			playerScriptDirectory.mkdirs(); // handles both script/ and script/playerName directory
//		}
//
//		Iterator<?> keys = scripts.keySet().iterator();
//
//		BufferedWriter bufferedWriter;
//
//		try
//		{
//			while (keys.hasNext())
//			{
//				String key = (String) keys.next();
//				String value = (String) scripts.get(key);
//				File script = new File(playerScriptDirectory.getAbsolutePath() + "/" + key + ".js");
//				if (value == null)
//				{
//					//System.err.println("Value was null");
//					continue;
//				}
//				bufferedWriter = new BufferedWriter(new FileWriter(script, false));
//				bufferedWriter.write(value);
//				bufferedWriter.close();
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

//	public static void grabScriptsForOnlinePlayers(HashMap<String, Channel> channels) throws ParseException
//	{
//		for (Player p : Bukkit.getOnlinePlayers())
//		{
//			Channel channel = channels.get(p.getName());
//			if (channel != null && p != null)
//				grabScriptsFor(p.getName(), channel);
//			else
//				p.sendMessage("You are not connected.  Go to workbook.thoughtstem.com/channels.");
//		}
//	}
}