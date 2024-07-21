// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleSyntax;

public class Algol68_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Algol68";
	}

	public Algol68_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = true;
		_punctuationExceptions = new String[] {
				":=", "+:=", ">=", "<=", "~+", "/="
		};

		addReservedWords(Algol68_Reserved_Words.RESERVED_WORDS);
	}
}
