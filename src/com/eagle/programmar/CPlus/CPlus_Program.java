// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.CPlus;

import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.programmar.CPlus.Terminals.CPlus_Literal;
import com.eagle.programmar.CPlus.Terminals.CPlus_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CPlus_Program extends C_Program
{
	public static final String CPP = "Cpp";
	
	public CPlus_Program()
	{
		super(CPP, new CPlus_Syntax());
		
		super.addModifier("constexpr");
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	@Override
	public void findLanguageOverrides(EagleOverrideManager overrider)
	{
		// overrider.override(C_Expression.class, CPlus_Expression.class); // Times out at 60 seconds. Why?
		overrider.override(C_Literal.class, CPlus_Literal.class);
		overrider.override(C_Number.class, CPlus_Number.class);
	}
	
	// Step is 5 to avoid duplicate @S(10) in C_Program
	public @S(5) @OPT TokenList<CPlus_Element> items;

	public static class CPlus_Element extends TokenChooser
	{
		public @FIRST CPlus_Namespace namespace;
		public @CHOICE @SYNTAX(C_Syntax.class) C_Comment comment;
		public @CHOICE CPlus_Class classDefinition;
		public @CHOICE CPlus_Using using;
		public @CHOICE CPlus_Method method;
		public @CHOICE CPlus_Extern extern;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
		public @LAST @SYNTAX(C_Syntax.class) C_StatementOrComment statementOrComment;
	}
}
