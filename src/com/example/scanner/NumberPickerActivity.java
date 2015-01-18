package com.example.scanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

@SuppressLint("NewApi")
public class NumberPickerActivity extends Activity implements NumberPicker.OnValueChangeListener
{
	private int value = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank_layout);
		show();
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal)
	{
		value = newVal;
	}

	public void show()
	{
		final Dialog d = new Dialog(this);
		d.setTitle("Number Picker");
		d.setContentView(R.layout.number_picker);
		Button finishButton = (Button) d.findViewById(R.id.finishButton);
		Button cancelButton = (Button) d.findViewById(R.id.cancelButton);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
		np.setMaxValue(60);
		np.setMinValue(1);
		np.setWrapSelectorWheel(false);
		np.setOnValueChangedListener(this);
		finishButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				d.dismiss();
				Intent i = new Intent();
				i.putExtra("RESULT_VALUE", value);
				setResult(RESULT_OK, i);
				finish();
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				d.dismiss();
				Intent i = new Intent();
				i.putExtra("RESULT_VALUE", 1);
				setResult(RESULT_OK, i);
				finish();
			}
		});
		d.show();
	}
}
