package com.example.scanner;

import java.io.Serializable;
import java.util.Locale;

public class Extras implements Serializable
{
	private int repeats = 1;
	public String mob = "Pig";
	private String name;
	private String otherName = "";
	private int droneDistance = 1;
	private String droneCode = "up";
	private String droneBlock = "Grass";
	private String potionType = "speed";

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public int getRepeats()
	{
		return repeats;
	}

	public void setRepeats(int r)
	{
		repeats = r;
	}

	public String getMob()
	{
		return mob;
	}

	public void setMob(String m)
	{
		mob = m;
	}

	public String getOtherName()
	{
		return otherName;
	}

	public String getDroneCode()
	{
		return droneCode;
	}

	public void setDroneCode(String code)
	{
		droneCode = code;
	}

	public void setDirection(String dir)
	{
		String[] directions = { "Up", "Down", "Right", "Left", "Forwards", "Backwards" };
		if (dir.equals(directions[0]))
		{
			droneCode = ("up");
		} else if (dir.equals(directions[1]))
		{
			droneCode = ("down");
		} else if (dir.equals(directions[2]))
		{
			droneCode = ("right");
		} else if (dir.equals(directions[3]))
		{
			droneCode = ("left");
		} else if (dir.equals(directions[4]))
		{
			droneCode = ("fwd");
		} else if (dir.equals(directions[5]))
		{
			droneCode = ("back");
		}
	}

	public void setOtherName(String on)
	{
		otherName = on;
	}
	
	public void setBlock(String block)
	{
		droneBlock = block;
	}
	
	public String getBlock()
	{
		return droneBlock.toUpperCase(Locale.getDefault());
	}

	public void setDroneDistance(int d)
	{
		droneDistance = d;
	}

	public int getDroneDistance()
	{
		return droneDistance;
	}
	
	public String getPotionType()
	{
		return potionType;
	}
	
	public void setPotionType(String pt)
	{
		potionType = pt;
	}
}
