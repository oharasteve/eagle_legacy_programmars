// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleSyntax;

public class Fortran_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "Fortran";
	}

	public Fortran_Syntax()
	{
		_autoAdvance = false;
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "&";
		_extraCharacters = "";
		_fixedStartColumn = 6;
		_fixedEndColumn = 72;

		_continuationColumn = 5; // if 6th column is a * then continue previous line
		_continuationColumnChar = '*';

		// _commentInstance = new Fortran_Comment();
		_punctuationExceptions = new String[] {
				"/=", "::", "//", "<=", ">="
		};

		addReservedWords(RESERVED_WORDS);
	}

	private static final String[] RESERVED_WORDS = new String[] {
			"call",
			"common",
			"else",
			"end",
			"exit",
			"function",
			"if",
			"implicit",
			"print",
			"program",
			"subroutine",
			"then",
			"write",
	};
}
