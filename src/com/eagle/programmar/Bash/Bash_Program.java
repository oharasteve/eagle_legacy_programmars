// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Bash;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Bash.Commands.Bash_Function;
import com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;

public class Bash_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String BASH = "Bash";

	public Bash_Program()
	{
		super(BASH, new Bash_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html";
	}

	public @S(10) TokenList<Bash_Element> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Bash_Element stmt : statements._elements)
		{
			AbstractToken which = stmt.element.getWhich();
			if (which instanceof Bash_Function)
			{
				Bash_Function fn = (Bash_Function) which;
				if (fn.getWhich() instanceof Bash_Function_Explicit)
				{
					Bash_Function_Explicit func = (Bash_Function_Explicit) fn.getWhich();
					interpreter.addFunction(func.fnName.getValue(), func);
				}
			}
		}

		// Second pass, execute the program
		for (Bash_Element stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt.element);
		}
	}
}
