// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.core.EagleSyntax;

public class MSSolution_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "MSSolution";
	}

	public MSSolution_Syntax()
	{
		_isCaseSensitive = true;
		_autoAdvance = false;
	}
}
