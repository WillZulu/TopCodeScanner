/*
 * @(#) TangibleCompiler.java
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
import java.util.Collections;
import java.util.List;

import com.example.scanner.Extras;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Compiles a tangible program.
 * 
 * @author Michael Horn
 * @version $Revision: 1.8 $, $Date: 2008/11/08 16:56:57 $
 */
public class TangibleCompiler
{
	private final String tag = "HI";
	protected Scanner scanner;
	protected boolean compiling;
	protected boolean annotate;
	protected ConnectionMap map;
	private Statement prevStatement = null;
	private boolean isLookingForStartCode = false;

	public TangibleCompiler()
	{
		this.map = new ConnectionMap();
		this.scanner = new Scanner();
		this.compiling = false;
		this.annotate = false;
	}

	public boolean isCompiling()
	{
		return this.compiling;
	}

	/**
	 * Returns an image that shows the result of the binary threshold filter.
	 */
	// it was private - by Mariam

	public List<TopCode> getCodes(List<TopCode> spots)
	{
		return spots;
	}

	public Program doCompile(ArrayList<Statement> states)
	{
		try
		{
			Program program = new Program();
			this.map.clear();
			for (Statement statement : states)
			{
//				s = StatementFactory.createStatement(top);
//				statements.add(s);
//				s.registerConnections(map);
				if (prevStatement != null && statement != null)
				{
					prevStatement.connect(statement);
				}
				prevStatement = statement;
				//				s.registerConnections(map);
			}
			//			for (int i = 0; i < spots.size(); i++)
			//			{
			//				s = StatementFactory.createStatement(spots.get(i));
			//				Log.i("HI", "s = " + statements.toString());
			//				statements.add(s);
			//				s.registerConnections(map);
			//			}
			// -----------------------------------------
			// 3. Figure out what each statement is connected to...
			// -----------------------------------------
//						map.draw();
//						map.formConnections();
			// -----------------------------------------
			// 6. Generate program
			// -----------------------------------------
			// program.setStatements(statements);
			// // for (int i = 0; i < statements.size(); i++)
			// // {
			// s = (Statement) statements.get(0);
			// // if (s instanceof StartStatement && ((StartStatement)
			// s).isUnique())
			// // {
			// // if(!s.equals(null))
			// // {
			// s.compile(program);
			// }
			// if (!unique)
			// {
			// unique = true;
			// s.compile(program);
			// }
			// } else
			// {
			// }
			// }
			// Log.i("HI", "inside doCompile() " + program.toString());
			// for (int i = 0; i < statements.size(); i++)
			// {
			// s = (Statement) statements.get(i);
			// }
			//			if(s != null)
			//			{
			//				Log.i("HI", "This statment may be added: " + s.toString());
			//			}
			boolean unique = false;
			Statement s = null;
			for (int i = 0; i < states.size(); i++)
			{
				s = (Statement) states.get(i);
				if (s instanceof StartStatement)
				{
					if (((StartStatement) s).isUnique())
					{
						if (!unique)
						{
							unique = true;
							s.compile(program);
						}
					} else
					{
						s.compile(program);
					}
				}
			}


//			for (Object tempStatement : statements)
//			{
//				Log.i("HI", "s = " + tempStatement.toString());
//			}
			program.setStatements(states);

			return program;
		} catch (Exception x)
		{
			Log.e("HI", "ERROR!");
			return null;
		} finally
		{
			// this.compiling = false;
			// notifyProgress("Done.", 1.0);
			// notifyEnd();
		}
	}

	/**
	 * Tangible compile function: generate a program from a buffered image
	 */
//	public Program compile(Bitmap image) throws CompileException
//	{
//		this.compiling = true;
////		scanner.lookingForStart(isLookingForStartCode);
//		List<TopCode> spots = scanner.scan(image);
//		// for (TopCode top : spots)
//		// Log.i("HI", top.toString());
////		if (spots.size() >= 3)
////		{
////			List<TopCode> newList = fix(spots);
////			if (newList != null)
////				return doCompile(newList);
////		}
//		return doCompile(spots);
//	}
	
	public void setLookingForStart(boolean isLookingForStart)
	{
		isLookingForStartCode = isLookingForStart;
	}


//	public String getTypeById(TopCode code)
//	{
//		if (code.getCode() == START_CODE)
//		{
//			return "Start";
//		} else if (code.getCode() == FORWARD_CODE)
//		{
//			return "Forward";
//		} else if (code.getCode() == END_CODE)
//		{
//			return "End";
//		} else if (code.getCode() == REPEAT_CODE_START)
//		{
//			return "Repeat Start";
//		} else if (code.getCode() == REPEAT_CODE_END)
//		{
//			return "Repeat End";
//		} else
//			return "null";
//	}

	public List<TopCode> compileCodes(Bitmap image)
	{
		List<TopCode> spots = scanner.scan(image);
		// for (TopCode top : spots)
		// Log.i("HI", top.toString());
//		if (spots.size() >= 3)
//		{
//			List newList = fix(spots);
//			if (newList != null)
//				return newList;
//		}
		return spots;
	}

	public List<TopCode> getCodes(Bitmap image)
	{
		List<TopCode> spots = scanner.scan(image);
		return spots;
	}
	
	public boolean getAnnotate()
	{
		return this.annotate;
	}

	public void setAnnotate(boolean annotate)
	{
		this.annotate = annotate;
	}

	protected void notifyBegin()
	{
	}

	protected void notifyEnd()
	{
	}

	protected void notifyProgress(String task, double progress)
	{
	}
}
