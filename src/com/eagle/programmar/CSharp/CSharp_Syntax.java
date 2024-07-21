// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;

public class CSharp_Syntax extends EagleSyntax
{
	public static boolean isCaseSensitive = true;

	@Override
	public String syntaxId()
	{
		return "CSharp";
	}

	public CSharp_Syntax()
	{
		_isCaseSensitive = isCaseSensitive;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"!=", "<=", "==", ">=", "=>", "//", "...", "::", "||", "&&"
		};
		_commentInstance = new CSharp_Comment();

		addReservedWords(CSharp_Reserved_Words.RESERVED_WORDS);
	}
}
