package com.eagle.programmar.Python;

import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;

public class Python2_Program extends Python_Program
{
	public static final String NAME = "Python2";
	
	public Python2_Program()
	{
		super(NAME, new Python_Syntax());
	}
	
	public static class Python2_Simple_Statement extends Python_Simple_Statement
	{
		public @CHOICE Python_PrintStatement printStatement;
	}

	@Override
	public void findClassOverrides(EagleOverrideManager overrider)
	{
		overrider.override(Python_Simple_Statement.class, Python2_Simple_Statement.class);
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.python.org/2/reference/index.html";
	}
}
