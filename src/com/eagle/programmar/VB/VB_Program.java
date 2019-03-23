// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleLanguageLookup;
import com.eagle.tokens.TokenList;

public class VB_Program extends EagleLanguage
{
	public static final String NAME = "VB";
	
	static {
		EagleLanguageLookup.addLanguage(NAME, VB_Program.class);
		EagleLanguageLookup.setLanguageSuffix(".cls", NAME);
	}

	public VB_Program()
	{
		super(NAME, new VB_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://msdn.microsoft.com/en-us/library/";
	}
	
	public @OPT TokenList<VB_Statement> elements;
}
