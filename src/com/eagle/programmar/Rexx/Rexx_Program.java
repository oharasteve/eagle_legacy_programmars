// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rexx;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Statements.Rexx_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Rexx_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String REXX = "Rexx";

	public Rexx_Program()
	{
		super(REXX, new Rexx_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.ibm.com/docs/en/cics-ts/6.x?topic=";
	}

	public @S(10) TokenList<Rexx_TopElement> elements;

	public static class Rexx_TopElement extends TokenChooser
	{
		public @CHOICE Rexx_Element XXelement;
		public @CHOICE Rexx_Function XXfunction;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the Function and Sub definitions
		for (Rexx_TopElement elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Rexx_Function)
			{
				Rexx_Function func = (Rexx_Function) which;
				interpreter.addFunction(func.id.getValue(), func);
				interpreter.tryToInterpret(func);	// Initialize metrics because functions *follow* main()
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (Rexx_TopElement elt : elements._elements)
		{
			interpreter.tryToInterpret(elt);
		}
	}
}