// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.core.EagleSyntax;

public class Haskell_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "Haskell";
	}

	public Haskell_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"++", "::", "!!", "&&", "||", "<-", "->",
				"..", "/=", "<=", ">=", "=="
		};

		addReservedWords(RESERVED_WORDS);
	}

	private static String[] RESERVED_WORDS = new String[] {
			"False",
			"True",
			"case",
			"data",
			"do",
			"else",
			"if",
			"in",
			"let",
			"module",
			"of",
			"return",
			"then",
			"type",
			"where",
	};
}