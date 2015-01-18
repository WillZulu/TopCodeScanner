/*
 * @(#) Num.java
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

import android.content.Context;
import android.content.Intent;

import com.compile.PStatement;
import com.compile.Program;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.example.scanner.Extras;

public class Num extends PStatement
{
	public static final int CODE_2 = 203;
	public static final int CODE_3 = 339;
	public static final int CODE_4 = 587;
	public static final int CODE_5 = 595;
	private Extras ext = new Extras();

	public Num(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Num(new TopCode(CODE_2), new Extras()));
		StatementFactory.registerStatementType(new Num(new TopCode(CODE_3), new Extras()));
		StatementFactory.registerStatementType(new Num(new TopCode(CODE_4), new Extras()));
		StatementFactory.registerStatementType(new Num(new TopCode(CODE_5), new Extras()));
	}

	public int getCode()
	{
		return top.getCode();
	}

	public String getName()
	{
		return "NUMBER";
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Num(top, ex);
	}

	public int getValue()
	{
		switch (getCode())
		{
			case CODE_2:
				return 2;
			case CODE_3:
				return 3;
			case CODE_4:
				return 4;
			case CODE_5:
				return 5;
			default:
				return 0;
		}
	}

	public void compile(Program program)
	{
		setDebugInfo(program);
		if (this.next != null)
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
