// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class FSharp_Program extends EagleLanguage
{
	public static final String FSHARP = "FSharp";
	
	public FSharp_Program()
	{
		super(FSHARP, new FSharp_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<FSharp_Element> elements;
	
	public static class FSharp_Element extends TokenChooser
	{
	}
}
