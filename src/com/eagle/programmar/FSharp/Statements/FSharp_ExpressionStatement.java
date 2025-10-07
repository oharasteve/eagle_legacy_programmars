// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2025

package com.eagle.programmar.FSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class FSharp_ExpressionStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) FSharp_Expression expression;
	public @S(20) FSharp_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, expression);
//		boolean isReturn = false;
//		if (isReturn)
//		{
//			return generator.newReturnStatement(newExpr, this);
//		}
		return generator.newExpressionStatement(newExpr, this);
	}
}
