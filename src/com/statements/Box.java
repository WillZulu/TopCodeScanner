/*
 * @(#) Growl.java
 * 
 * Tern Tangible Programming System
 * Copyright (C) 2009 Michael S. Horn 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.statements;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.example.scanner.CustomArrayControllerActivity;
import com.example.scanner.Extras;
import com.example.scanner.R;
import com.compile.PStatement;
import com.compile.Program;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;

public class Box extends PStatement
{
	public static final int CODE = 307;
	private Extras ext = new Extras();

	public Box(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Box(new TopCode(CODE), new Extras()));
	}

	public int getCode()
	{
		return CODE;
	}

	public String getName()
	{
		return "Box";
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Box(top, ex);
	}

	public void compile(Program program)
	{
		setDebugInfo(program);
		program.addInstruction("d.box(Material." + ext.getBlock() + ");");
		if (this.next != null)
			next.compile(program);
	}

	@Override
	public Intent getInfo(Context appContext)
	{
		Intent i = new Intent(appContext, CustomArrayControllerActivity.class);
		ArrayList<String> names = new ArrayList<String>();
		String[] blocks = { "Grass", "Wood", "Air" };
		for (String s : blocks)
			names.add(s);
		int[] blockIds = { R.drawable.grass, R.drawable.wood, R.drawable.air };
		i.putExtra("Names_List", names);
		i.putExtra("Id_List", blockIds);
		return i;
	}

	@Override
	public Extras getExtras()
	{
		return ext;
	}

	@Override
	public void setExtras(Extras e)
	{
		ext = e;
	}

	@Override
	public void unpack(Intent data)
	{
		String name = data.getStringExtra("RESULT_BLOCK");
		ext.setBlock(name);
	}
}
