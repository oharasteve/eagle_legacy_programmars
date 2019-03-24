package com.eagle.programmar.Python;

import com.eagle.core.EagleLanguageLookup;
import com.eagle.programmar.Python.Python3_Program.Python3_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;

public class Python3_Program extends Python_Program<Python3_Simple_Statement>
{
	public static final String NAME = "Python3";
	
	static {
		EagleLanguageLookup.addLanguage(NAME, Python3_Program.class);
		EagleLanguageLookup.setLanguageSuffix(".py", NAME);
	}

	public Python3_Program()
	{
		super(NAME, new Python_Syntax());
	}

	public static class Python3_Simple_Statement extends Python_Simple_Statement
	{
		
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.python.org/3/reference/index.html";
	}
}
