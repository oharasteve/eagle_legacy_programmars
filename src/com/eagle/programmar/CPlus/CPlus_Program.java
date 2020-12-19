// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Program;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CPlus_Program extends C_Program
{
	public static final String NAME = "Cpp";
	
	public CPlus_Program()
	{
		super(NAME, new CPlus_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	// Step is 5 to avoid duplicate @S(10) in C_Program
	public @S(5) @OPT TokenList<CPlus_Element> items;

	public static class CPlus_Element extends TokenChooser
	{
		public @LAST C_StatementOrComment statementOrComment;
		public @CHOICE CPlus_Class classDefinition;
		public @CHOICE CPlus_Using using;
		public @CHOICE CPlus_Namespace namespace;
		public @CHOICE CPlus_Method method;
	}
}
