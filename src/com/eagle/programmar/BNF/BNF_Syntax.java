// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.core.EagleSyntax;

public class BNF_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "BNF";
	}

	public BNF_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_extraCharacters = "";
		_punctuationExceptions = new String[] {
				"::="
		};
	}
}
