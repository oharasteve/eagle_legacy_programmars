// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.core.AbstractLanguage;
import com.eagle.tokens.TokenList;

public class BNF_Program extends AbstractLanguage
{
	public static final String BNF = "BNF";

	public BNF_Program()
	{
		super(BNF, new BNF_Syntax());
	}

	public @S(10) TokenList<BNF_Rule> rules;
}
