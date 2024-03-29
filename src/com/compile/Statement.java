/*
 * @(#) Statement.java
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

import java.io.Serializable;

import com.example.scanner.Extras;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * A base class for all tangible language statements. A statement is any element
 * that can be connected in a program's flow-of-control. A statement must have
 * at least one socket or one connector (most have both a socket and a
 * connector). Statements have no implicit data type and carry no return value.
 * 
 * @author Michael Horn
 * @version $Revision: 1.8 $, $Date: 2008/03/18 15:08:39 $
 */
public abstract class Statement implements Serializable
{

	/** Next statement in the flow-of-control chain */
	protected Statement next;

	/** TopCode for this statement */
	protected TopCode top;

	/** Statement's compile-time ID number */
	protected int c_id;
	
	protected Extras ex;
	
	public Statement(TopCode top, Extras ex)
	{
		this.next = null;
		this.top = top;
		this.c_id = -1;
		this.ex = ex;
	}

	/**
	 * Name of the statement
	 */
	public abstract String getName();

	/**
	 * The unique identifier for this statement type. This number is encoded in
	 * a statement's topcode.
	 */
	public abstract int getCode();

	/**
	 * Translates a tangible statement into an "assembly" instruction
	 */
//	public abstract void compile(Program p) throws CompileException;
	
	public abstract void compile(Program p);

	/**
	 * Factory method. Creates a new statement of the correct type.
	 */
	public abstract Statement newInstance(TopCode top, Extras ex);

	public String toString()
	{
		return getName();
	}

	public TopCode getTopCode()
	{
		return top;
	}
	
	public abstract Extras getExtras();
	
	public abstract void setExtras(Extras e);

	public void setCompileID(int c_id)
	{
		this.c_id = c_id;
	}

	public int getCompileID()
	{
		return this.c_id;
	}

	public boolean isCompiled()
	{
		return (this.c_id >= 0);
	}

	/**
	 * Used by compiler to connect the next statement in the flow chain.
	 */
	public void connect(Statement next)
	{
		this.next = next;
	}

	protected void connect(Statement next, String name)
	{
		this.next = next;
	}

	public void registerConnections(ConnectionMap map)
	{
		Log.i("HI", "Registering connections");
		map.addSocket(this, -1.6, 0.25);
		map.addConnector(this, "next", 1.8, 0.25);
	}
	
	public abstract Intent getInfo(Context appContext);
	
	public abstract void unpack(Intent data);
	
	/**
	 * Called by the compiler. Registers the top-relative location of this
	 * statement's socket and connector. Statements with non-standard shapes
	 * should override this function.
	 */
}
