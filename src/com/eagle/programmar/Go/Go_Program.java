// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Go;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Go_Program extends EagleLanguage
{
	public static final String GO = "Go";
	
	public Go_Program()
	{
		super(GO, new Go_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Go_Element> elements;
	
	public static class Go_Element extends TokenChooser
	{
	}
}
