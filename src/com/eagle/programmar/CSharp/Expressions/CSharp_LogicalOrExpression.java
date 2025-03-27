// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_Punctuation orOperator = new CSharp_Punctuation("||");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			interpreter.pushBool(true);
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
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
	
	public static CSharp_LogicalOrExpression generateExpression(AbstractExpression leftExpr, LogicalOrEnum oper, AbstractExpression rightExpr, AbstractToken source)
	{
		CSharp_LogicalOrExpression expr = new CSharp_LogicalOrExpression();
		expr.left = (CSharp_Expression) leftExpr;
		expr.right = (CSharp_Expression) rightExpr;
		switch (oper)
		{
		case OR:
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
