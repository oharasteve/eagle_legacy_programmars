// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleLanguageLookup;
import com.eagle.tokens.TokenList;

public class BNF_Program extends EagleLanguage
{
	public static final String NAME = "BNF";
	
	public BNF_Program()
	{
		super(NAME, new BNF_Syntax());
	}
	
	static {
		EagleLanguageLookup.addLanguage(NAME, BNF_Program.class);
		EagleLanguageLookup.setLanguageSuffix(".bnf", NAME);
	}

	public TokenList<BNF_Rule> rules;
}
