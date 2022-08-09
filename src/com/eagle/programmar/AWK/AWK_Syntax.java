// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleSyntax;

public class AWK_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "AWK";
	}
	
	public AWK_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "==", "!=", "<=", ">=", "++", "--", "+=", "!~" };
		
		addReservedWords(reservedWords);
		// addReservedWords(FUNCTIONS);
	}

	// See https://www.gnu.org/software/gawk/manual/html_node/Built_002din.html
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
	
	// From https://www.gnu.org/software/gawk/manual/html_node/Glossary.html under "Keyword"
	private String[] reservedWords = new String[] {
			"BEGIN",
			"BEGINFILE",
			"END",
			"ENDFILE",
			"break",
			"case",
			"continue",
			"default",
			"delete",
			"do",
			"else",
			"exit",
			"for",
			"function",
			"func",
			"if",
			"in",
			"next",
			"nextfile",
			"switch",
			"while",
	};
}
