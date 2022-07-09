// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleSyntax;

public class Ruby_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Ruby";
	}
	
	public Ruby_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "==", "!=", "<=", ">=", "++", "--", "+=", ".." };
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
			"end",
			"for",
			"if",
	};
}
