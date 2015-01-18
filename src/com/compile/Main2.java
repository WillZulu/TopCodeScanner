/*
 * @(#) Main.java
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
package com.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import com.statements.*;
import android.graphics.Bitmap;
import android.util.Log;

public class Main2 implements Runnable
{
	protected static Main2 instance;
	protected TangibleCompiler compiler;
	protected Properties props;
	protected Program program;

	public Main2()
	{
		Main2.instance = this;
		this.compiler = new TangibleCompiler();
		this.program = null;
		{
			Backward.register();
			Beep.register();
			End.register();
			Forward.register();
			Go.register();
			Box.register();
			Left.register();
			Num.register();
			Repeat.register();
			Right.register();
			Sensor.register();
			PotionEffect.register();
			Shuffle.register();
			Drone.register();
			Lightning.register();
			Start.register();
			Stop.register();
			Wait.register();
			Summon.register();
			CallFunction.register();
		}
	}

	public Program compileCodes(ArrayList<Statement> states)
	{
		return compiler.doCompile(states);
	}

	public List<TopCode> getTopCodes(Bitmap b)
	{
		return compiler.getCodes(b);
	}

	@Override
	public void run()
	{
	}
}
