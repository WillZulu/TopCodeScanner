package com.example.scanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager
{
	private boolean enabled;
	public JSCodeHolder hold = new JSCodeHolder();
	private PagerAdapter adapt;

	public CustomViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		this.enabled = true;
	}
	
	public void setCustomAdapter(PagerAdapter adapt)
	{
		this.adapt = adapt;
		super.setAdapter(adapt);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (this.enabled)
			return super.onTouchEvent(event);

		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event)
	{
		if (this.enabled)
			return super.onInterceptTouchEvent(event);

		return false;
	}
	
	public void setPagingEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public int getNextIndex()
	{
		if(getCurrentItem() >= 9)
			return 0;
		return getCurrentItem() + 1;
	}
	
	public int getPrevIndex()
	{
		if(getCurrentItem() <= 0)
			return 9;
		return getCurrentItem() - 1;
	}
	
	public void compile()
	{
		hold.eraseCode();
		hold.appendCode(adapt.compile());
	}
}
