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
		
		addReservedWords(reservedWords);
	}

	// From https://www.geeksforgeeks.org/ruby-keywords/
	private static String[] reservedWords = new String[] {
			"__ENCODING__",
			"__LINE__",
			"__FILE__",
			"BEGIN",
			"END",
			"alias",
			"and",
			"begin",
			"break",
			"case",
			"class",
			"def",
			"defined?",
			"do",
			"else",
			"elsif",
			"end",
			"ensure",
			"false",
			"for",
			"if",
			"in",
			"module",
			"next",
			"nil",
			"not",
			"or",
			"redo",
			"rescue",
			"retry",
			"return",
			"self",
			"super",
			"then",
			"true",
			"undef",
			"unless",
			"until",
			"when",
			"while",
			"yield",
	};
}
