// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;
import com.eagle.transform.EagleTransformer;

public class C_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) C_Punctuation orOperator = new C_Punctuation("||");
	public @S(30) C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			interpreter.pushBool(true);
		}
		else
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
}
