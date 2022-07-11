// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ada;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Ada.Terminals.Ada_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Ada_Program extends EagleLanguage
{
	public static final String ADA = "Ada";
	
	public Ada_Program()
	{
		super(ADA, new Ada_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Ada_Element> elements;
	
	public static class Ada_Element extends TokenChooser
	{
		public @CHOICE Ada_Comment comment;
		public @CHOICE Ada_Statement stmt;
	}
}
