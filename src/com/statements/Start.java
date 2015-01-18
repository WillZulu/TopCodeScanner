/*
 * @(#) Start.java
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

import com.compile.PStatement;
import com.compile.Program;
import com.compile.StartStatement;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.example.scanner.Extras;
import com.example.scanner.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public class Start extends PStatement implements StartStatement
{
	private Extras ext = new Extras();
	public static final int CODE = 569;

	public Start(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Start(new TopCode(CODE), new Extras()));
	}

	public int getCode()
	{
		return CODE;
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Start(top, ex);
	}

	public String getName()
	{
		return "startcode";
	}

	public boolean isUnique()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "Start";
	}
	
	public void compile(Program program)
	{
		program.addInstruction("function " + ext.getName() + "() {");
		program.addInstruction("var d = new Drone(me, me.location);");
		if (next != null)
		{
			next.compile(program);
		}
	}

	@Override
	public Intent getInfo(Context appContext)
	{
		return null;
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
		
	}
}
