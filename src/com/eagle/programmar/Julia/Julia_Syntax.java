// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Julia;

import com.eagle.core.EagleSyntax;

public class Julia_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Julia";
	}

	public Julia_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"==", "!=", "<=", ">=", "++", "--", "+=", "-=", "*=", "/="
		};

		addReservedWords(Julia_Reserved_Words.RESERVED_WORDS);
	}
}
