// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2024

package com.eagle.programmar.Rexx;

import com.eagle.core.EagleSyntax;

public class Rexx_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Rexx";
	}

	public Rexx_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"!=",
		};

//		addReservedWords(Rexx_Reserved_Words.RESERVED_WORDS);
	}
}