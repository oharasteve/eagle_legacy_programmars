// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Scala_BlockStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Scala_EOLN eoln1;
	public @S(30) TokenList<Scala_Statement> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Scala_EOLN eoln2;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Scala_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
