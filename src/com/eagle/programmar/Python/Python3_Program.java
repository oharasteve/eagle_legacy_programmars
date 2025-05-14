// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 23, 2019

package com.eagle.programmar.Python;

import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;

public class Python3_Program extends Python_Program
{
	public static final String PYTHON3 = "Python3";

	public Python3_Program()
	{
		super(PYTHON3, new Python_Syntax());
	}

	public static class Python3_Simple_Statement extends Python_Statement
	{

	}

	@Override
	public void findLanguageOverrides(EagleOverrideManager overrider)
	{
		overrider.override(Python_Statement.class, Python3_Simple_Statement.class);
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.python.org/3/reference/index.html";
	}
}
