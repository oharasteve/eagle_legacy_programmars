// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_MultiplicativeExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/":
			interpreter.pushInt(leftValue / rightValue);
			return;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + operator);
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "%":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
	
	public static CSharp_MultiplicativeExpression generateMultiplicative(
			AbstractExpression leftExpr, MultiplicativeEnum oper,
			AbstractExpression rightExpr, AbstractToken source)
	{
		CSharp_MultiplicativeExpression expr = new CSharp_MultiplicativeExpression();
		expr.left = (CSharp_Expression) leftExpr;
		expr.right = (CSharp_Expression) rightExpr;
		switch (oper)
		{
		case TIMES:
			expr.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			expr.operator.setValue("/");
			break;
		case DIVIDE_NO_TRUNCATE:
			expr.operator.setValue("/");
			CSharp_Type type = CSharp_Type.newPrimitiveType("double");
			CSharp_CastExpression cast = CSharp_CastExpression.newCastExpression(type, expr.right);
			expr.right = CSharp_Generator.wrapExpression(cast);
			break;
		case REMAINDER:
			expr.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
