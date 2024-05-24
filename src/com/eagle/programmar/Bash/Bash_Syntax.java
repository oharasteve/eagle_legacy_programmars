// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Bash;

import com.eagle.core.EagleSyntax;

public class Bash_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Bash";
	}

	public Bash_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = "\\";
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"==",
				"!=",
				"[[",
				"]]",
				"((",
				"))",
				"..",
				"#!",
				">>",
				"&>",
				"$#",
				"$?",
				"$@",
				"$*",
				"&&",
				"||",
				"&>>",
				"+=",
				"-=",
				">=",
				"<="
		};

		addReservedWords(Bash_Reserved_Words.RESERVED_WORDS);
	}
}
