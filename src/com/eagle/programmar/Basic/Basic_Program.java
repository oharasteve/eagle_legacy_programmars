// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.TokenList;

public class Basic_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String BASIC = "Basic";

	public Basic_Program()
	{
		super(BASIC, new Basic_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return null;
	}

	public @S(10) @OPT TokenList<Basic_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Basic_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
