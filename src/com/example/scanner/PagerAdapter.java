package com.example.scanner;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PagerAdapter extends FragmentPagerAdapter
{
	private List<CreateModFragment> fragments;

	public PagerAdapter(FragmentManager fm, List<CreateModFragment> fragments)
	{
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position)
	{
		return fragments.get(position);
	}

	@Override
	public int getCount()
	{
		return fragments.size();
	}

	public String compile()
	{
		String str = "";
		for (CreateModFragment mod : fragments)
		{
			if(mod.isStarted)
			{
				str += mod.compileCodes();
			}
		}
		return str;
	}
}