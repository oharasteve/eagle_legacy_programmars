// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.SQL.SQL_Reserved_Words;

public class SQLite_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "SQLite";
	}

	public SQLite_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_autoAdvance = false;
		_continuationChar = null;
		_extraCharacters = "_";
//		_commentInstance = new SQL_Comment();   // Causes infinite loop, bails out eventually
		_punctuationExceptions = new String[] {
				"!=", "<=", ">=", "=>",
				"||", "&&",
				"/*", "//"
		};

		addReservedWords(SQL_Reserved_Words.RESERVED_WORDS);
	}
}
