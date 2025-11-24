// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_BracketExpr extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword EXPR = new TCL_Keyword("expr");
	public @S(30) TCL_Expression expression;
	public @S(40) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return transformer.transformExpression(generator, expression);
	}
}
