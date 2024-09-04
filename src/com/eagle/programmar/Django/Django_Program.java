// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2014

package com.eagle.programmar.Django;

import com.eagle.core.AbstractLanguage;
import com.eagle.tokens.TokenList;

public class Django_Program extends AbstractLanguage
{
	public static final String DJANGO = "Django";

	public Django_Program()
	{
		super(DJANGO, new Django_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	// Components of a Django Program
	public @S(10) TokenList<Django_Element> elements;
}