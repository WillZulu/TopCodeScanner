package com.example.scanner;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String>
{
	private Context appContext;
	private ArrayList<Bitmap> bitmaps;
	private ArrayList<String> values;

	public CustomArrayAdapter(Context context, ArrayList<String> names, ArrayList<Bitmap> bmap)
	{
		super(context, R.layout.custom_list, names.toArray(new String[names.size()]));
		appContext = context;
		bitmaps = bmap;
		values = names;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_list, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setTextColor(Color.BLACK);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values.get(position));
		imageView.setImageBitmap(bitmaps.get(position));
		return rowView;
	}
}
