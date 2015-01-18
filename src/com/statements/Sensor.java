/*
 * @(#) Sensor.java
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
package com.statements;

import android.content.Context;
import android.content.Intent;

import com.compile.PStatement;
import com.compile.Program;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.example.scanner.Extras;

public class Sensor extends PStatement
{
	public static final int PRESS = 419;
	public static final int RELEASE = 425;
	public static final int LIGHT = 453;
	public static final int DARK = 465;
	public static final int OBJECT = 31;
	public static final int SOUND = 47;
	public static final int MUTE = 55;
	private Extras ext = new Extras();

	public Sensor(TopCode top, Extras ex)
	{
		super(top, ex);
		ext = ex;
	}

	public static void register()
	{
		StatementFactory.registerStatementType(new Sensor(new TopCode(PRESS), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(RELEASE), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(LIGHT), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(DARK), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(OBJECT), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(SOUND), new Extras()));
		StatementFactory.registerStatementType(new Sensor(new TopCode(MUTE), new Extras()));
	}

	public int getCode()
	{
		return top.getCode();
	}

	public String getName()
	{
		switch (top.getCode())
		{
			case PRESS:
				return "PRESS";
				//return "UNTIL-PRESS";
			case RELEASE:
				return "UNTIL-RELEASE";
			case LIGHT:
				return "UNTIL-LIGHT";
			case DARK:
				return "UNTIL-DARK";
			case OBJECT:
				return "UNTIL-OBJECT";
			case SOUND:
				return "UNTIL-SOUND";
			case MUTE:
				return "UNTIL-MUTE";
			default:
				return "SENSOR";

		}
	}

	public Statement newInstance(TopCode top, Extras ex)
	{
		return new Sensor(top, ex);
	}

	public String getTest()
	{
		switch (top.getCode())
		{
			case PRESS:
				System.out.println("SENSOR_1 == 0");
				return "SENSOR_1 == 0";//"!SensorValueBool(SENSOR_1)";
			case RELEASE:
				return "SENSOR_1 == 1";
			case DARK:
				return "SensorValue(S3) > 42";
			case LIGHT:
				return "SensorValue(S3) < 42";
			case OBJECT:
				return "SensorUS(IN_4) < 15 ";
			case SOUND:
				return "SensorValue(S2) > 10";
			case MUTE:
				return "SensorValue(S2)<10";
			default:
				return "1";
		}
	}

	public String getSensorID()
	{
		switch (top.getCode())
		{
			case PRESS:
			case RELEASE:
				return "IN_1";

			case DARK:
			case LIGHT:
				return "IN_3";

			case OBJECT:
				return "IN_4";
			case SOUND:
			case MUTE:
				return "IN_2";
			default:
				return "IN_1";

		}
	}

	public String getType()
	{
		switch (top.getCode())
		{
			case PRESS:
			case RELEASE:
				return "Touch";

			case LIGHT:
			case DARK:
				return "Light";
			case OBJECT:
				return "Ultrasonic";

			case SOUND:
			case MUTE:
				return "Sound";
			default:
				return "Touch";
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
