package com.example.scanner;


import com.compile.Statement;
import com.statements.Box;
import com.statements.CallFunction;
import com.statements.Drone;
import com.statements.PotionEffect;
import com.statements.Repeat;
import com.statements.Summon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends Activity
{
	private Statement statement;
	private Intent closeIntent = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		TextView tv = (TextView) findViewById(R.id.topCodeType);
		statement = (Statement) getIntent().getExtras().get("Statement");
		tv.setText(tv.getText() + statement.getName());
		if (statement.getInfo(getApplicationContext()) != null)
		{
			Button optionsButton = (Button) findViewById(R.id.codeOptionsButton);
			optionsButton.setVisibility(Button.VISIBLE);
			optionsButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent i = statement.getInfo(getApplicationContext());
					startActivityForResult(i, statement.getTopCode().getCode());
				}
			});
		}
		Button closeButton = (Button) findViewById(R.id.closeScreenButton);
		closeButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				setResult(RESULT_OK, closeIntent);
				closeIntent.putExtra("EXTRA_TYPE", statement.getCode());
				finish();
			}
		});
		Button moreOptions = (Button) findViewById(R.id.evenMoreOption);
		if (statement.getCode() == Drone.CODE)
		{
			moreOptions.setVisibility(Button.VISIBLE);
		}
		moreOptions.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				Intent i = new Intent(getApplicationContext(), NumberPickerActivity.class);
				startActivityForResult(i, Repeat.BEGIN);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != Activity.RESULT_OK)
			return;

		if (requestCode == Repeat.BEGIN)
		{
			closeIntent.putExtra("RESULT_VALUE", data.getIntExtra("RESULT_VALUE", 5));
		}

		if (requestCode == Summon.CODE)
		{
			String mob = data.getStringExtra("RESULT_MOB");
			if (mob == null || mob == "")
				mob = "pig";
			int path = data.getIntExtra("RESULT_ID", R.drawable.pig);
			closeIntent.putExtra("RESULT_MOB", mob);
			closeIntent.putExtra("RESULT_ID", path);
		}

		if (requestCode == Drone.CODE)
		{
			int path = data.getIntExtra("RESULT_PATH", R.drawable.uparrowicon);
			String dir = data.getStringExtra("RESULT_DIR");
			if (dir == null || dir == "")
				dir = "up";
			closeIntent.putExtra("RESULT_DIR", dir);
			closeIntent.putExtra("RESULT_PATH", path);
		}

		if (requestCode == Box.CODE)
		{
			int path = data.getIntExtra("RESULT_PATH", R.drawable.grass);
			String str = data.getStringExtra("RESULT_DIR");
			if (str == null || str == "")
				str = "wood";
			closeIntent.putExtra("RESULT_BLOCK", str);
			closeIntent.putExtra("RESULT_BLOCK_PATH", path);
		}

		if (requestCode == PotionEffect.CODE)
		{
			int path = data.getIntExtra("RESULT_PATH", R.drawable.grass);
			String str = data.getStringExtra("RESULT_DIR");
			if (str == null || str == "")
				str = "speed";
			closeIntent.putExtra("RESULT_POTION", str);
			closeIntent.putExtra("RESULT_POTION_PATH", path);
		}
		
		if (requestCode == CallFunction.CODE)
		{
			String name = data.getStringExtra("EXTRA");
			closeIntent.putExtra("RESULT_CALL", name);
		}
	}
}
