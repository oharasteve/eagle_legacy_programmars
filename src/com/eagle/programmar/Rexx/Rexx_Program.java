// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rexx;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Rexx_Program extends EagleLanguage
{
	public static final String REXX = "Rexx";

	public Rexx_Program()
	{
		super(REXX, new Rexx_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.ibm.com/docs/en/cics-ts/6.x?topic=applications-writing-running-rexx-application";
	}

	public @S(10) TokenList<Rexx_Element> elements;

	public static class Rexx_Element extends TokenChooser
	{
		public @CHOICE Rexx_Comment XXcomment;
	}
}