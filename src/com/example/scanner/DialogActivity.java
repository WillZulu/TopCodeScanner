package com.example.scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DialogActivity extends Activity
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_layout);
		String text = getIntent().getExtras().getString("TEXT");
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		final EditText name = new EditText(this);
		name.setHint(text);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(name);
		dialog.setView(ll);
		dialog.setTitle("Dialog");
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				Intent closeIntent = new Intent();
				closeIntent.putExtra("EXTRA", name.getText().toString());
				setResult(RESULT_OK, closeIntent);
				finish();
			}

		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		dialog.show();
	}
}
