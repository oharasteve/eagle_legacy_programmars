// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Java;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Java.Terminals.Java_Comment;

public class Java_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "Java";
	}

	public Java_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Java_Comment();
		_punctuationExceptions = new String[] {
				"//", "/*", "!=", "<=", "==", ">=", "...", "++", "--", "||", "&&", "::", "->"
		};

		addReservedWords(Java_Reserved_Words.RESERVED_WORDS);
	}
}
