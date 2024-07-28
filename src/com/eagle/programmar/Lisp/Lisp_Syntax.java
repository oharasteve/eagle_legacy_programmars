// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Lisp.Terminals.Lisp_Comment;

public class Lisp_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "Lisp";
	}

	public Lisp_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Lisp_Comment();
		_punctuationExceptions = new String[] {
				"<=", ">=", "/=", "++", "+++", "**", "***"
		};
		
		// addReservedWords(RESERVED_WORDS); // None needed!
	}
}
