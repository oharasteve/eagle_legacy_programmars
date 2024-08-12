// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CMacro;

import com.eagle.core.EagleSyntax;

public class CMacro_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;
	
	@Override
	public String syntaxId()
	{
		return "CMacro";
	}

	public CMacro_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_autoAdvance = false;
		// _commentInstance = new C_Comment();
		_punctuationExceptions = new String[] {
				"!=", "<=", "==", ">=", "/*", "&&", "||", "##", "..."
		};
	}
}
