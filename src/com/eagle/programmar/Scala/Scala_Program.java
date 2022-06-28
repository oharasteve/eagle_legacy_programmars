// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Scala;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Scala_Program extends EagleLanguage
{
	public static final String SCALA = "Scala";
	
	public Scala_Program()
	{
		super(SCALA, new Scala_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Scala_Element> elements;
	
	public static class Scala_Element extends TokenChooser
	{
	}
}
