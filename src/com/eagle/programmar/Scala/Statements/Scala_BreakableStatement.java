// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Scala_BreakableStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) Scala_Keyword BREAKABLE = new Scala_Keyword("breakable");
	public @S(20) Scala_BlockStatement block;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(block);
	}
}
