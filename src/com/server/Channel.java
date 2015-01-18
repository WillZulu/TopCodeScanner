package com.server;

import org.json.simple.JSONObject;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Channel
{
	String token;
	String username;
	String tsname;
	private String tag = "HI";
	CloudObject cloud_object;

	public Channel(String token, String username)
	{
		this.token = token;
		this.username = username;

		try
		{
			this.cloud_object = CloudObject.find(token);
		} catch (Exception e)
		{
			this.cloud_object = new CloudObject(token, new JSONObject());
		}

		initChannel();
	}
	
	/**
	 * Sends a script to data['scripts'][name][text]
	 * 
	 * @param name
	 *            the name of the script
	 * @param text
	 *            the actual script
	 */
	public void sendScript(String name, String text)
	{
		if (!this.cloud_object.getData().containsKey("scripts"))
		{
			this.cloud_object.getData().put("scripts", new JSONObject());
		}
		((JSONObject) this.cloud_object.getData().get("scripts")).put(name, text);
		this.cloud_object.save();
	}

	public String getToken()
	{
		return token;
	}

	public String getUsername()
	{
		return username;
	}

	@SuppressWarnings("unchecked")
	public void initChannel()
	{
		Log.i(tag, this.username);
		this.cloud_object.getData().put("minecraft_username", this.username);
		this.cloud_object.getData().put("token", this.token);
		this.cloud_object.save();
	}

	public void refresh()
	{
		this.cloud_object = CloudObject.find(this.token);
	}

	public String poll_status()
	{
		refresh();

		this.tsname = (String) this.cloud_object.getData().get("thoughtstem_username");

		if (this.tsname == null)
			return "Waiting";

		return "Established";
	}

	public void write(String key, JSONObject value)
	{
		refresh();

		this.cloud_object.getData().put(key, value);
		this.cloud_object.save();
	}

	public String tsname()
	{
		return (String) this.cloud_object.getData().get("thoughtstem_username");
	}

	public static String generate_token()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String time = dateFormat.format(date);

		Random gen = new Random();
		float rand = gen.nextFloat();

		String ret = null;

		try
		{
			ret = sha1(time + rand);
		} catch (Exception e)
		{
			System.err.println(e);
		}

		return ret;
	}

	static String sha1(String input) throws NoSuchAlgorithmException
	{
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++)
		{
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/*
	 * public static void main(String[] args){ Channel c = new
	 * Channel("3f060e06178e851bc6b5446be9ac16ee91bf9a29", "thoughtstem");
	 * 
	 * System.out.println(c.tsname()); }
	 */
}