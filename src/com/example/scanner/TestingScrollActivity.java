package com.example.scanner;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TestingScrollActivity extends FragmentActivity
{
	private PagerAdapter mPagerAdapter;
	public CustomViewPager mPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.view_pager_layout);
		this.initialisePaging();
	}

	private void initialisePaging()
	{
		List<CreateModFragment> fragments = new ArrayList<CreateModFragment>();
		for(int i = 0; i < 9; i++)
		{
			CreateModFragment frag = CreateModFragment.newInstance();
			fragments.add(frag);
		}
		mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		mPager = (CustomViewPager) super.findViewById(R.id.viewpager);
		mPager.setCustomAdapter(this.mPagerAdapter);
		mPager.setPagingEnabled(false);
	}

}
