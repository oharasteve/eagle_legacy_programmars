// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree;

import com.eagle.core.EagleSyntax;

public class RPGFree_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "RPGFree";
	}

	public RPGFree_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "?";
		_extraCharacters = "";
		_autoAdvance = true;
	}
}
