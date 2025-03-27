// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Format;
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
		String formatted = FSharp_Format.format(interpreter, exprs);
		System.out.println(formatted);
	}
}
