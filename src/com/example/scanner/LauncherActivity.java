package com.example.scanner;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LauncherActivity extends Activity
{
	private String filename = "loginData";
	private boolean dataExist = false;
	private Button loginButton;
	private UserLoginData data = new UserLoginData();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open_screen);
		Button openMod = (Button) findViewById(R.id.openMod);
		openMod.setBackgroundColor(Color.parseColor("#808080"));
		Button deleteMod = (Button) findViewById(R.id.deleteMod);
		deleteMod.setBackgroundColor(Color.parseColor("#808080"));
		loginButton = (Button) findViewById(R.id.login);
		dataExist = data.checkIfDataExist();
		if (dataExist)
			loginButton.setText(getApplicationContext().getString(R.string.logout_title));
		loginButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (dataExist)
				{
					data.erase();
					loginButton.setText(R.string.login_title);
				} else
				{
					dialog();
				}
				dataExist = data.checkIfDataExist();
				if (dataExist)
					loginButton.setText(getApplicationContext().getString(R.string.logout_title));
				else
					loginButton.setText(getApplicationContext().getString(R.string.login_title));
			}
		});
		Button createButton = (Button) findViewById(R.id.createMod);
		createButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Intent i = new Intent(getApplicationContext(), TestingScrollActivity.class);
				startActivity(i);
			}
		});
	}

	public void dialog()
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		final EditText username = new EditText(this);
		final EditText key = new EditText(this);
		username.setHint("Username");
		key.setHint("Key");
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(key);
		ll.addView(username);
		dialog.setView(ll);
		dialog.setTitle("Login");
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				if (Environment.getExternalStorageState() == null)
				{
					error();
					return;
				} else
				{
					ArrayList<String> names = new ArrayList<String>();
					names.add(username.getText().toString());
					names.add(key.getText().toString());
					data.writeLoginData(filename, names);
					saved();
				}
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
			}
		});
		dialog.show();
	}
	
	public void saved()
	{
		Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	}

	public void error()
	{
		Toast.makeText(this, "Sd Card is not pluged in", Toast.LENGTH_SHORT).show();
	}
}
