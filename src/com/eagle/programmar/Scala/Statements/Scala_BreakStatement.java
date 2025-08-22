// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_BreakStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Scala_Keyword BREAK = new Scala_Keyword("break");
	public @S(20) Scala_EOLN eoln;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newBreakStatement(BREAK);
	}
}
