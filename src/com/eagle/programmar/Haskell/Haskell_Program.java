// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_SameLineStatement;
import com.eagle.tokens.AbstractToken;
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

	public @S(10) TokenList<Haskell_ComplexStatement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Haskell_ComplexStatement stmt : statements._elements)
		{
			AbstractToken which1 = stmt.statementOrComment.getWhich();
			if (which1 instanceof Haskell_SameLineStatement)
			{
				Haskell_SameLineStatement same = (Haskell_SameLineStatement) which1;
				AbstractToken which2 = same.statements.first().getWhich();
				if (which2 instanceof Haskell_Function)
				{
					Haskell_Function fn = (Haskell_Function) which2;
					interpreter.addFunction(fn.definition.def.getValue(), fn);
				}
			}
		}

		for (Haskell_ComplexStatement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}