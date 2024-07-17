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
		_punctuationExceptions = new String[] {
				"<-", "==", "!=", "<=", ">=", "+=", "-=", "*=", "/="
		};

		addReservedWords(reservedWords);
	}

	// From
	// https://www.geeksforgeeks.org/scala-keywords/#:~:text=Keywords%20or%20Reserved%20words%20are,in%20a%20compile%2Dtime%20error.
	private static String[] reservedWords = new String[] {
			"abstract",
			"case",
			"catch",
			"class",
			"def",
			"do",
			"else",
			"equals",
			"extends",
			"false",
			"final",
			"finally",
			"for",
			"forSome",
			"if",
			"implicit",
			"import",
			"lazy",
			"List",
			"match",
			"new",
			"null",
			"object",
			"override",
			"package",
			"println",
			"private",
			"protected",
			"return",
			"sealed",
			"super",
			"this",
			"throw",
			"trait",
			"true",
			"try",
			"type",
			"val",
			"var",
			"while",
			"with",
			"yield",
	};
}
