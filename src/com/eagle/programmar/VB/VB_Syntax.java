// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.VB;

import com.eagle.core.EagleSyntax;

public class VB_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "VB";
	}

	public VB_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "_";
		_autoAdvance = false;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"!=", "<=", "==", ">=", "/*"
		};

		addReservedWords(VB_Reserved_Words.RESERVED_WORDS);
	}
}
