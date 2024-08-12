// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CMD;

import com.eagle.core.EagleSyntax;

public class CMD_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE= false;
	
	@Override
	public String syntaxId()
	{
		return "CMD";
	}

	public CMD_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "^"; // Perhaps a backslash (\) on some systems?
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"::", "==", "&&", ">>", "||"
		};

		addReservedWords(new String[] {
				"else", "eof", "for"
		});
	}
}
