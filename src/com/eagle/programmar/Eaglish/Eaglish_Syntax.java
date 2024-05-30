// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleSyntax;

public class Eaglish_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Eaglish";
	}

	public Eaglish_Syntax()
	{
		_isCaseSensitive = false;
		_autoAdvance = false;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = new String[] {
				"<=", ">="
		};
		addReservedWords(reservedWords);
	}

	// From https://en.wikibooks.org/wiki/Delphi_Programming/Reserved_keywords
	private static String[] reservedWords = new String[] {
			"NOT"
	};
}
