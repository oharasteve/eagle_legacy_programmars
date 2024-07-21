// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.core.EagleSyntax;

public class TCL_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "TCL";
	}

	public TCL_Syntax()
	{
		_isCaseSensitive = false;
		// _continuationChar = "+"; // THIS DOESN'T WORK WELL AT ALL.
		_extraCharacters = "_";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"--", "&&", "||", "<=", ">=", "<>", "!=", "=="
		};

		addReservedWords(RESERVED_WORDS);
	}

	private static final String[] RESERVED_WORDS = new String[] {
			"and", "break", "not", "or", "puts", "set",
	};
}