// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_BlockStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Go_EOLN eoln1;
	public @S(30) TokenList<Go_Statement> stmts;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Go_EOLN eoln2;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Go_Statement stmt : stmts._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
