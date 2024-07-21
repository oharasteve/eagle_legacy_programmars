// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleSyntax;

public class Eaglish_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Eaglish";
	}

	public Eaglish_Syntax()
	{
		_isCaseSensitive = false;
		_autoAdvance = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = new String[] {
				"<=", ">="

		};
		addReservedWords(RESERVED_WORDS);
	}

	private static final String[] RESERVED_WORDS = new String[] {
			"NOT"
	};
}
