/*
 * @(#) Program.java
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
import java.util.Map;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import android.util.Log;

public class Program
{
	protected List<String> code;
	protected int lid;
	protected double xmin, ymin, xmax, ymax;
	protected List<Statement> statements;
	protected List<Statement> compiled;
	public int COMPILE_ID = 0;
	private ArrayList<String> output = new ArrayList<String>();

	public Program()
	{
		this.code = new java.util.ArrayList<String>();
		this.lid = 0;
		this.xmin = 1600;
		this.ymin = 1200;
		this.xmax = 0;
		this.ymax = 0;
		this.statements = null;
		this.compiled = new java.util.ArrayList<Statement>();
	}

	/**
	 * Called by individual statements in a program. Adds a line of code to the
	 * program.
	 */
	public void addInstruction(String instr)
	{
		output.add(instr);
		this.code.add(instr);
	}
	
	public ArrayList<String> getCode()
	{
		return output;
	}

	/**
	 * Fetch an instruction by line number.
	 */
	public String getInstruction(int line)
	{
		if (line < 0 || line >= code.size())
		{
			return null;
		} else
		{
			return code.get(line).trim();
		}
	}

	/**
	 * Add a statement to the list of compiled statements in this program
	 */
	public void addCompiledStatement(Statement s)
	{
		this.compiled.add(s);
	}

	/**
	 * Returns a list of compiled statements
	 */
	public List<Statement> getCompiledStatements()
	{
		return this.compiled;
	}

	/**
	 * Used to generate unique label names for flow-of-control structures.
	 */
	public String genLabel()
	{
		return ("L" + (lid++));
	}

	public int getLineCount()
	{
		return this.code.size();
	}

	public String toString()
	{
		String s = "";
		for (String line : code)
		{
			s += line;
		}
		return s;
	}

	public List<Statement> getStatements()
	{
		return this.statements;
	}

	public void setStatements(List<Statement> statements)
	{
		this.statements = statements;
	}

	/**
	 * Returns a statement by compile-time ID number
	 */
	public Statement getStatement(int c_id)
	{
		if (statements == null)
			return null;
		for (Statement s : statements)
		{
			if (s.getCompileID() == c_id)
			{
				return s;
			}
		}
		return null;
	}

	public boolean isEmpty()
	{
		return (statements == null || statements.isEmpty());
	}

	/**
	 * Called by individual statements. Expands the bounding box that frames a
	 * program in a JPEG image.
	 */
	public void expandBoundingBox(double cx, double cy)
	{
		xmax = (cx > xmax) ? cx : xmax;
		ymax = (cy > ymax) ? cy : ymax;
		xmin = (cx < xmin) ? cx : xmin;
		ymin = (cy < ymin) ? cy : ymin;
	}

	/**
	 * Returns a bounding box around a program in a JPEG image.
	 */
}
