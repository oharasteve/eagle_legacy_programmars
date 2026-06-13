// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Haskell_FunctionCall extends TokenSequence
	implements EagleRunnable // , EagleTransformableStatementList
{
	public @S(10) Haskell_Variable variable;
	public @S(20) Haskell_Punctuation arrow = new Haskell_Punctuation("<-");
	public @S(30) Haskell_Identifier_Reference funcName;
	public @S(40) @OPT TokenList<Haskell_Expression> arguments;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = funcName.getValue();

		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Haskell_Function func = (Haskell_Function) fn;
		func.call(interpreter, fnName, arguments._elements);

		// Grab return value from stack and save it in the variable
		EagleValue val = interpreter.popEagleValue();
		interpreter.setSymbol(variable, variable.id.getValue(), val);
	}
}
