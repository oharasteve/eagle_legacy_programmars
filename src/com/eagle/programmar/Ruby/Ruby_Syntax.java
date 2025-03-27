// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleSyntax;

public class Ruby_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "Ruby";
	}

	public Ruby_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"==", "!=", "<=", ">=", "++", "--", "+=", ".."
		};

		addReservedWords(Ruby_Reserved_Words.RESERVED_WORDS);
	}
}
