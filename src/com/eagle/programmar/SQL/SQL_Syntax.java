// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.SQL;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;

public class SQL_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "SQL";
	}

	public SQL_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new SQL_Comment();
		_punctuationExceptions = new String[] {
				"!=", "<=", ">=", "=>", "||", "&&", "/*", "//"
		};

		addReservedWords(SQL_Reserved_Words.RESERVED_WORDS);
	}
}
