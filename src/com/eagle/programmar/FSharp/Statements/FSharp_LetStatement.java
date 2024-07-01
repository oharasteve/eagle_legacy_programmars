// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Symbols.FSharp_Identifier_Reference;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class FSharp_LetStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("functions/let-bindings") FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) @OPT FSharp_Keyword MUTABLE = new FSharp_Keyword("mutable");
	public @S(30) FSharp_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) FSharp_Expression expr;
	public @S(60) FSharp_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		AbstractToken which = var.var.getWhich();
		if (!(which instanceof FSharp_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which.toString());
		}
		FSharp_Identifier_Reference id = (FSharp_Identifier_Reference) which;
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
				value);
	}
}
