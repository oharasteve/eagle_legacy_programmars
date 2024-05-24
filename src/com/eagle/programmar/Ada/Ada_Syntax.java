// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ada;

import com.eagle.core.EagleSyntax;

public class Ada_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Ada";
	}

	public Ada_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = true;
		_punctuationExceptions = new String[] {
				":=", "/=", "..", "=>", "<=", ">="
		};

		addReservedWords(reservedWords);
	}

	// From
	// https://www.adaic.org/resources/add_content/standards/05rm/html/RM-2-9.html
	private static String[] reservedWords = new String[] {
			"abort",
			"abs",
			"abstract",
			"accept",
			"access",
			"aliased",
			"all",
			"and",
			"array",
			"at",
			"begin",
			"body",
			"case",
			"constant",
			"declare",
			"delay",
			"delta",
			"digits",
			"do",
			"else",
			"elsif",
			"end",
			"entry",
			"exception",
			"exit",
			"for",
			"function",
			"generic",
			"goto",
			"if",
			"in",
			"interface",
			"is",
			"limited",
			"loop",
			"mod",
			"new",
			"not",
			"null",
			"of",
			"or",
			"others",
			"out",
			"overriding",
			"package",
			"pragma",
			"private",
			"procedure",
			"protected",
			"put",
			"raise",
			"range",
			"record",
			"rem",
			"renames",
			"requeue",
			"return",
			"reverse",
			"select",
			"separate",
			"subtype",
			"synchronized",
			"tagged",
			"task",
			"terminate",
			"then",
			"type",
			"until",
			"use",
			"when",
			"while",
			"with",
			"xor",
	};
}
