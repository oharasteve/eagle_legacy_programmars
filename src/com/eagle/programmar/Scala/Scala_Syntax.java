// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Scala;

import com.eagle.core.EagleSyntax;

public class Scala_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Scala";
	}
	
	public Scala_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "<-",
				"==", "!=", "<=", ">=",
				"+=", "-=", "*=", "/="};
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
			"def",
			"else",
			"for",
			"if",
			"object",
			"val"
	};
}
