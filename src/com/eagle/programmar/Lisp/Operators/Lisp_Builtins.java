// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Lisp_Builtins extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Lisp_KeywordChoice builtins = new Lisp_KeywordChoice("T", "NIL");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtins.toString().toUpperCase())
		{
		case "NIL":
			interpreter.pushBool(false);
			return;
		case "T":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than T/NIL: " + builtins);
	}
}