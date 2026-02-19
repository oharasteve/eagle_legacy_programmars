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
import com.eagle.transform.EagleGenerator.BitwiseEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_BitwiseExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("&", "|", "^");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "&":
			interpreter.pushInt(leftValue & rightValue);
			break;
		case "|":
			interpreter.pushInt(leftValue | rightValue);
			break;
		case "^":
			interpreter.pushInt(leftValue ^ rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		BitwiseEnum oper;
		switch (operator.getValue())
		{
		case "&":
			oper = BitwiseEnum.AND;
			break;
		case "|":
			oper = BitwiseEnum.OR;
			break;
		case "^":
			oper = BitwiseEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
		return generator.newBitwiseExpression(leftExpr, oper, rightExpr, this);
	}

	public static CSharp_Expression generateBitwise(CSharp_Expression leftExpr,
			BitwiseEnum oper, CSharp_Expression rightExpr, AbstractToken source)
	{
		CSharp_BitwiseExpression bitExpr = new CSharp_BitwiseExpression();
		bitExpr.left = leftExpr;
		bitExpr.right = rightExpr;
		switch (oper)
		{
		case AND:
			bitExpr.operator.setValue("&");
			break;
		case OR:
			bitExpr.operator.setValue("|");
			break;
		case XOR:
			bitExpr.operator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		bitExpr.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(bitExpr);
	}
}
