package com.eagle.programmar.Python;

import com.eagle.core.EagleLanguageLookup;
import com.eagle.programmar.Python.Python2_Program.Python2_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;
import com.eagle.tokens.TokenList;

public class Python2_Program extends Python_Program<Python2_Simple_Statement>
{
	public static final String NAME = "Python2";
	
	static {
		EagleLanguageLookup.addLanguage(NAME, Python2_Program.class);
	}

	public Python2_Program()
	{
		super(NAME, new Python_Syntax());
	}
	
	public static class Python2_Simple_Statement extends Python_Simple_Statement
	{
		public @CHOICE Python_PrintStatement printStatement;
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.python.org/2/reference/index.html";
	}
	
	public @OPT TokenList<Python_Statement<Python2_Simple_Statement>> entries;
}
