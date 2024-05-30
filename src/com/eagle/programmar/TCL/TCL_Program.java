// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;

public class TCL_Program extends EagleLanguage implements EagleRunnable
{
	public static final String TCL = "TCL";

	public TCL_Program()
	{
		super(TCL, new TCL_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "$true";
		return "$false";
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.tcl.tk/man/tcl8.7/";
	}

	public @S(10) TokenList<TCL_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (TCL_Statement elt : statements._elements)
		{
			for (AbstractToken stmt : elt.compoundStatement.statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}
