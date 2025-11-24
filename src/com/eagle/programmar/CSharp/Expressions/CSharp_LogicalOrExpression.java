// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;

public class CSharp_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/boolean-logical-operators") CSharp_PunctuationChoice orOperator = new CSharp_PunctuationChoice(
			"||", "^");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue;
		switch (orOperator.toString())
		{
		case "||":
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			break;
		case "^":
			rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + orOperator);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		LogicalOrEnum oper;
		switch (orOperator.getValue())
		{
		case "||":
			oper = LogicalOrEnum.OR;
			break;
		case "^":
			oper = LogicalOrEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unable to handle " + orOperator);
		}
		return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
	}

	public CSharp_Expression generateLogicalOr(CSharp_Expression leftExpr,
			LogicalOrEnum oper, CSharp_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		switch (oper)
		{
		case OR:
			this.orOperator.setValue("||");
			break;
		case XOR:
			this.orOperator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
