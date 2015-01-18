package com.example.scanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MobChooser extends ListActivity
{
	private static final String[] sizeList = new String[] {"Pig", "Zombie", "Iron_Golem"};
	private static final int[] idList = new int[] {R.drawable.pig, R.drawable.zombie, R.drawable.golem};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list, sizeList));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent i = new Intent();
				i.putExtra("RESULT_MOB", sizeList[position]);
				i.putExtra("RESULT_ID", idList[position]);
				setResult(RESULT_OK, i);
				finish();
			}
		});

	}
}
