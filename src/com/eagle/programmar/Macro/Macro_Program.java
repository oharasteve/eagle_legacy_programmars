// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Macro;

import com.eagle.core.AbstractLanguage;

public class Macro_Program extends AbstractLanguage
{
	public static final String MACRO = "Macro";

	public Macro_Program()
	{
		super(MACRO, new Macro_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	// Add body
}
