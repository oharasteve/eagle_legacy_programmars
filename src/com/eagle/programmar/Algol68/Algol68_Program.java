// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Algol68_Program extends EagleLanguage
{
	public static final String ALGOL68 = "Algol68";
	
	public Algol68_Program()
	{
		super(ALGOL68, new Algol68_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Algol68_Element> elements;
	
	public static class Algol68_Element extends TokenChooser
	{
	}
}
