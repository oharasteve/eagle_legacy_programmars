// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;

public class Javascript_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;
	
	@Override
	public String syntaxId()
	{
		return "Javascript";
	}

	public Javascript_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Javascript_Comment();
		_punctuationExceptions = new String[] {
				"/*", "!=", "<=", "==", ">=", "//", "&&", "||", "===", "!==", "!===", "=>", "</", "**", "..."
		};

		addReservedWords(Javascript_Reserved_Words.RESERVED_WORDS);
	}
}
