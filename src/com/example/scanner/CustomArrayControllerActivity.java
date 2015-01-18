package com.example.scanner;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CustomArrayControllerActivity extends ListActivity
{
	private int[] ids;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Bitmap> maps = new ArrayList<Bitmap>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		names = getIntent().getStringArrayListExtra("Names_List");
		int[] newIds = getIntent().getIntArrayExtra("Id_List");
		ids = newIds;
		pathToBitmap();
		setListAdapter(new CustomArrayAdapter(getApplicationContext(), names, maps));
	}

	private void pathToBitmap()
	{
		for (int path : ids)
		{
			maps.add(((BitmapDrawable) getApplicationContext().getResources().getDrawable(path)).getBitmap());
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent i = new Intent();
		i.putExtra("RESULT_PATH", ids[position]);
		i.putExtra("RESULT_DIR", names.get(position));
		setResult(RESULT_OK, i);
		finish();
	}
}
