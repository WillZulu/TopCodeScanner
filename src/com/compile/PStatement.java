/*
 * @(#) PStatement.java
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

import com.example.scanner.Extras;


public abstract class PStatement extends Statement
{
	protected String tag = "HI";

	public PStatement(TopCode top, Extras ex)
	{
		super(top, ex);
	}

	protected void setDebugInfo(Program program)
	{
		setCompileID(program.COMPILE_ID++);
		program.expandBoundingBox(top.getCenterX(), top.getCenterY());
		program.addCompiledStatement(this);
	}
	
	public void setRepeats(int r)
	{
	}
}
