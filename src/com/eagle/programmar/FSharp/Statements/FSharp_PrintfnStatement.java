// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class FSharp_PrintfnStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("plaintext-formatting") FSharp_Keyword PRINTFN = new FSharp_Keyword("printfn");
	public @S(20) TokenList<FSharp_Expression> exprs;
	public @S(30) FSharp_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (FSharp_Expression expr : exprs._elements)
		{
			EagleValue result = interpreter.getEagleValue(expr);
			System.out.print(result.toString());
		}
		System.out.println();
	}
}
