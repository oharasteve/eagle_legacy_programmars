// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;

public class VB_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/logical-bitwise-operators") VB_KeywordChoice orOperator = new VB_KeywordChoice("or",
			"orelse");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		String oper = orOperator.getValue();
		switch (oper)
		{
		case "or":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue || rightValue);
			return;
		case "orelse":
			if (leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(true);
				return;
			}
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(rightVal);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in VB_ConditionalOrExpression");
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
