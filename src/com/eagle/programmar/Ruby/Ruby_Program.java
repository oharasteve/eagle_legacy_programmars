// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Ruby_Program extends EagleLanguage
{
	public static final String RUBY = "Ruby";
	
	public Ruby_Program()
	{
		super(RUBY, new Ruby_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Ruby_Element> elements;
	
	public static class Ruby_Element extends TokenChooser
	{
	}
}
