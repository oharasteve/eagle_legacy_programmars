// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.TokenList;

public class Haskell_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String HASKELL = "Haskell";

	public Haskell_Program()
	{
		super(HASKELL, new Haskell_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) @OPT TokenList<Haskell_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (statements.isPresent())
		{
			for (Haskell_Statement stmt : statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}