package com.statements;

import android.content.Context;
import android.content.Intent;

import com.compile.PStatement;
import com.compile.Program;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.example.scanner.Extras;
import com.example.scanner.NumberPickerActivity;

/*
 * @(#) Repeat.java
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
 * 
 * Modified by Mariam Hussien - Oct,2011
 */

public class Repeat extends PStatement
{

	public static final int BEGIN = 171;
	public static final int END = 179;
	private Extras ext = new Extras();
	public static int NEST = 0;
	public static int VAR = 0;
	private int repeats = 1;

	public Repeat(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Repeat(new TopCode(BEGIN), new Extras()));
		StatementFactory.registerStatementType(new Repeat(new TopCode(END), new Extras()));
	}

	public int getCode()
	{
		return top.getCode();
	}

	public String getName()
	{
		return ("Repeat");
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Repeat(top, ex);
	}

	public void compile(Program program)
	{
		setDebugInfo(program);
		repeats = ext.getRepeats();
		if (getCode() == BEGIN)
		{
			compileBeginRepeat(program);
		} else if (getCode() == END)
		{
			compileEndRepeat(program);
		}
	}
	
	@Override 
	public void setRepeats(int r)
	{
		repeats = r;
	}

	protected void compileEndRepeat(Program program)
	{
		if (Repeat.NEST > 0)
		{
			program.addInstruction("}");
//			program.addInstruction("   }");
			Repeat.NEST--;
		}
		if (this.next != null)
			next.compile(program);
	}

	protected void compileBeginRepeat(Program program)
	{
		if (next != null && next instanceof Num)
		{
			String var = "count" + VAR++;
			int param = ((Num) next).getValue();
			program.addInstruction("   int " + var + " = 0;");
			program.addInstruction("   while (" + var + " < " + param + ") {");
			program.addInstruction("      " + var + "++;");
		} else if (next != null && next instanceof Sensor)
		{
			Sensor sen = (Sensor) next;
			String senType = sen.getType();
			program.addInstruction("   SetSensor" + senType + "(" + sen.getSensorID() + ");");
			program.addInstruction("   while (" + sen.getTest() + ") {");
		} else
		{
			program.addInstruction("for(var i = 0; i < " + repeats + "; i++) {");
		}
		int nest = NEST;
		NEST++;
		if (this.next != null)
			next.compile(program);
		if (NEST > nest)
		{
//			program.addInstruction("   End Repeat()");
//			program.addInstruction("   }");
			NEST--;
		}
	}

	@Override
	public Intent getInfo(Context appContext)
	{
		Intent i = new Intent(appContext, NumberPickerActivity.class);
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
		int repeats = data.getIntExtra("RESULT_VALUE", 1);
		ext.setRepeats(repeats);
	}
}