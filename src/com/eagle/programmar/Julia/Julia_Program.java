// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Julia;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Julia_Program extends EagleLanguage
{
	public static final String JULIA = "Julia";
	
	public Julia_Program()
	{
		super(JULIA, new Julia_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Julia_Element> elements;
	
	public static class Julia_Element extends TokenChooser
	{
	}
}
