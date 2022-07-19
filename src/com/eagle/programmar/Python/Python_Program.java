// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.tokens.TokenList;

public abstract class Python_Program extends EagleLanguage
{
	public Python_Program(String name, EagleSyntax syntax)
	{
		super(name, syntax);
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://docs.python.org/3.10/reference/";
	}

	public @S(10) @OPT TokenList<Python_Statement> entries;
}
