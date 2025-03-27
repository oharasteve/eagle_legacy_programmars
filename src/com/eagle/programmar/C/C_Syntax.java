// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.C;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;

public class C_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;
	
	@Override
	public String syntaxId()
	{
		return "C";
	}

	public C_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_commentInstance = new CMacro_Comment(); // Doesn't work at all
		_punctuationExceptions = new String[] {
				"!=", "<=", "==", ">=", "/*", "&&", "||", "..", "->", "++", "--", "::", "+=", "-=", "...", ">>", "<<"
		};

		addReservedWords(C_Reserved_Words.RESERVED_WORDS);
	}
}
