package com.example.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

public class UserLoginData
{
	private String passkey;
	private String username;
	private String filename = "loginData";
	
	public void getLoginData()
	{
		File root = new File(Environment.getExternalStorageDirectory(), "loginData");
		final File gpxfile = new File(root, filename);
		if (gpxfile.exists())
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(gpxfile));
				String line;
				StringBuilder text = new StringBuilder();
				while ((line = br.readLine()) != null)
				{
					text.append(line);
				}
				String data[] = text.toString().split("/n");
				username = data[0];
				passkey = data[1];
			} catch (IOException e)
			{
				Log.e("HI", "ERROR " + e.getMessage());
			}
		}
	}
	
	public void writeLoginData(String filename, ArrayList<String> data)
	{
		try
		{
			File root = new File(Environment.getExternalStorageDirectory(), "loginData");
			if (!root.exists())
			{
				root.mkdirs();
			}
			File gpxfile = new File(root, filename);
			FileWriter writer = new FileWriter(gpxfile);
			for (int i = 0; i < data.size(); i++)
			{
				String s = data.get(i);
				writer.append(s);
				if (i == 0)
				{
					writer.append("/n");
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			Log.i("HI", "" + e);
		}
	}
	
	public boolean checkIfDataExist()
	{
		File root = new File(Environment.getExternalStorageDirectory(), "loginData");
		if (!root.exists())
			root.mkdirs();
		final File gpxfile = new File(root, filename);
		if (gpxfile.exists())
			return true;
		return false;
	}

	public void erase()
	{
		File root = new File(Environment.getExternalStorageDirectory(), "loginData");
		if (!root.exists())
			root.mkdirs();
		final File gpxfile = new File(root, filename);
		gpxfile.delete();
	}
	
	public String getPasskey()
	{
		if (this.passkey == null || this.passkey == "")
			getLoginData();
		return passkey;
	}
	
	public String getUsername()
	{
		if (this.username == null || this.username == "")
			getLoginData();
		return username;
	}
}
