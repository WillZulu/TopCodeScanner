package com.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * User: links Date: 5/2/14
 */
public class CloudObject
{

	private String name;
	private JSONObject value;

	private JSONObject data;

	public CloudObject(String n, JSONObject v)
	{
		name = n;
		value = v;
	}

	public CloudObject(String n)
	{
		name = n;
		value = new JSONObject();
	}

	public static CloudObject find(String name)
	{
		return find(GCS.getDefaultBucketName(), name);
	}

	public static CloudObject find(String bucket, String name)
	{
		GCS.initGCS();
		return GCS.find(bucket, name);
	}

	public void save(String bucket)
	{
		value.put("data", data.toJSONString());

		GCS.put(bucket, this);
	}

	public void save()
	{
		save(GCS.getDefaultBucketName());
	}

	public JSONObject getData()
	{
		if (data != null)
			return data;

		JSONObject json = getValue();
		JSONParser p = new JSONParser();

		JSONObject ret = null;

		try
		{
			ret = (JSONObject) p.parse((String) json.get("data"));
		} catch (Exception e)
		{
			ret = new JSONObject();
		}

		data = ret;

		return ret;
	}

	/* Getters and setters */
	public JSONObject getValue()
	{
		return value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String k)
	{
		name = k;
	}

	public void setValue(JSONObject v)
	{
		value = v;
	}
}