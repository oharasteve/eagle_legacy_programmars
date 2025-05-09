// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.core.EagleSyntax;

public class Basic_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "Basic";
	}

	public Basic_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<=", ">="
		};

		addReservedWords(Basic_Reserved_Words.RESERVED_WORDS);
	}
}
