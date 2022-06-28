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
		_punctuationExceptions = new String[] { "==", "!=", "<=", ">=", "++", "--", "+=", "!~" };
		
		addReservedWords(keywords);
		addReservedWords(FUNCTIONS);
	}

	public static final String[] FUNCTIONS = {
		"getline",
		"index",
		"int",
		"length",
		"match",
		"sprintf",
		"strftime",
		"substr",
	};
	
	private String[] keywords = new String[] {
		"case",
		"function",
		"gsub",
		"if",
		"next",
		"print",
		"printf",
		"split",
		"sub",
		"switch",
		"while",
	};
}
