// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class AWK_Program extends EagleLanguage
{
	public static final String AWK = "AWK";
	
	public AWK_Program()
	{
		super(AWK, new AWK_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<AWK_Element> elements;
	
	public static class AWK_Element extends TokenChooser
	{
		public @CHOICE AWK_Command command;
		public @CHOICE AWK_Comment comment;
		public @CHOICE AWK_Function function;
	}
}
