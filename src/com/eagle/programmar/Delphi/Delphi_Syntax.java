// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleSyntax;

public class Delphi_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Delphi";
	}

	public Delphi_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		// _commentInstance = new Delphi_Comment();
		_punctuationExceptions = new String[] {
				"<>", "<=", ">=", ":=", ".."
		};

		addReservedWords(Delphi_Reserved_Words.RESERVED_WORDS);
	}
}
