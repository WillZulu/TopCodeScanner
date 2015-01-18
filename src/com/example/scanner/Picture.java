package com.example.scanner;

import java.io.File;
import java.io.IOException;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.util.Log;
import android.view.Display;

public class Picture
{
	private float scaler;
	
	public File createTemporaryFile(String part, String str)
	{
		File tempDir = Environment.getExternalStorageDirectory();
		tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
		
		if (!tempDir.exists())
			tempDir.mkdir();
		
		try
		{
			return File.createTempFile(part, str, tempDir);
		} catch (IOException e)
		{
			Log.i("HI", "ERROR " + e);
		}
		return null;
	}
	
	public Bitmap scaleToWidth(Bitmap image, float width)
	{
		float imageWidth = image.getWidth();
		scaler = width / image.getWidth();
		float imageHeight = image.getHeight();
		float newHeight = (width * imageHeight) / imageWidth;
		Bitmap bmap = Bitmap.createScaledBitmap(image, (int) width, (int) newHeight, false);
		return bmap;
	}
	
	/**
	 * Returns the difference between the screen width and image width
	 */
	public float getDifference()
	{
		return scaler;
	}
}
