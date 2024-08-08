// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class FSharp_Assignment extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) FSharp_Variable var;
	public @S(20) FSharp_Punctuation operator = new FSharp_Punctuation("<-");
	public @S(30) FSharp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.id.getValue(), val);
	}
}
