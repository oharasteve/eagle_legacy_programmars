// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ada;

import com.eagle.core.EagleSyntax;

public class Ada_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "Ada";
	}

	public Ada_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_extraCharacters = "_";
		_autoAdvance = true;
		_punctuationExceptions = new String[] {
				":=", "/=", "..", "=>", "<=", ">="
		};

		addReservedWords(Ada_Reserved_Words.RESERVED_WORDS);
	}
}
