// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2024

package com.eagle.programmar.Rexx;

import com.eagle.core.EagleSyntax;

public class Rexx_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "Rexx";
	}

	public Rexx_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_autoAdvance = false;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"\\=", "<=", ">=", "//", "=="
		};

		addReservedWords(Rexx_Reserved_Words.RESERVED_WORDS);
	}
}