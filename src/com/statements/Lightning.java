/*
 * @(#) Spin.java
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
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.example.scanner.Extras;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Lightning extends PStatement
{
	private Extras ext = new Extras();
	public static final int CODE = 331;

	public Lightning(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Lightning(new TopCode(CODE), new Extras()));
	}

	public int getCode()
	{
		return CODE;
	}

	public String getName()
	{
		return "Strike Lightning";
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Lightning(top, ex);
	}

	public void compile(Program program)
	{
		setDebugInfo(program);
		program.addInstruction("world.strikeLightning(d.getLocation());");
		next.compile(program);
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
