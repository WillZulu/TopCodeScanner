/*
 * @(#) Go.java
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

public class Go extends PStatement
{
	private Extras ext = new Extras();
	public static final int FWD_A = 211;
	public static final int REV_A = 229;
	public static final int FWD_B = 117;
	public static final int REV_B = 87;
	public static final int FWD_C = 217;
	public static final int REV_C = 241;

	public Go(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Go(new TopCode(FWD_A), new Extras()));
		StatementFactory.registerStatementType(new Go(new TopCode(REV_A), new Extras()));
		StatementFactory.registerStatementType(new Go(new TopCode(FWD_B), new Extras()));
		StatementFactory.registerStatementType(new Go(new TopCode(REV_B), new Extras()));
		StatementFactory.registerStatementType(new Go(new TopCode(FWD_C), new Extras()));
		StatementFactory.registerStatementType(new Go(new TopCode(REV_C), new Extras()));
	}

	public int getCode()
	{
		return top.getCode();
	}

	public String getName()
	{
		return ("GO");
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Go(top, ex);
	}

	public void compile(Program program)
	{
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
