// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleSyntax;

public class Fortran_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Fortran";
	}

	public Fortran_Syntax()
	{
		_autoAdvance = false;
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		_fixedStartColumn = 6;
		_fixedEndColumn = 72;

		_continuationColumn = 5; // if 6th column is a * then continue previous line
		_continuationColumnChar = '*';

		// _commentInstance = new Fortran_Comment();
		_punctuationExceptions = new String[] {
				"/=", "::", "//", "<=", ">="
		};

		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
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
