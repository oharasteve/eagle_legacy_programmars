// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.core.EagleSyntax;

public class MSSolution_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "MSSolution";
	}

	public MSSolution_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_autoAdvance = false;

		// Because of the 'd': VisualStudioVersion = 17.14.37111.16 d17.14
		_allowDigitsInKeywords = false;
	}
}
