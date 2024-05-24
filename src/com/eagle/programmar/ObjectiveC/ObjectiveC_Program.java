// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.ObjectiveC;

import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class ObjectiveC_Program extends C_Program
{
	public static final String OBJECTIVEC = "ObjectiveC";

	public ObjectiveC_Program()
	{
		super(OBJECTIVEC, new ObjectiveC_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	// Step is 9 to avoid duplicate @S(10) in C_Program
	public @S(9) @OPT TokenList<ObjectiveC_Element> items;

	public static class ObjectiveC_Element extends TokenChooser
	{
		public @CHOICE @SYNTAX(C_Syntax.class) C_Comment comment;
		public @LAST @SYNTAX(C_Syntax.class) C_StatementOrComment statementOrComment;
	}
}
