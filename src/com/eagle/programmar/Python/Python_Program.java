// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.tokens.TokenList;

public abstract class Python_Program<PS extends Python_Simple_Statement> extends EagleLanguage
{
	public Python_Program(String name, EagleSyntax syntax)
	{
		super(name, syntax);
	}
	
	public @OPT TokenList<Python_Statement<PS>> entries;
}
