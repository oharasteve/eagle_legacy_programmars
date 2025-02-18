// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rexx_LogicalAndExpression extends PrecedenceOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rexx_Expression left = new Rexx_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rexx_Punctuation AND = new Rexx_Punctuation('&');
	public @S(30) Rexx_Expression right = new Rexx_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (!leftValue)
		{
			// Short circuit operation. Don't bother with RHS
			interpreter.pushBool(false);
			return;
		}
		boolean rightValue = interpreter.getBoolValue(right);
		interpreter.pushBool(rightValue);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
	}
}
