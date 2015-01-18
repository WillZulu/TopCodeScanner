package com.example.scanner;

public class JSCodeHolder
{
	private String allCode;
	
	public String getCode()
	{
		return allCode;
	}
	
	public void appendCode(String str)
	{
		allCode += str;
	}
	
	public void eraseCode()
	{
		allCode = "";
	}
}
