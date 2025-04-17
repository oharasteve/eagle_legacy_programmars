// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.NegativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_NegativeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
	public @S(20) @NOSPACE Java_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (operator.toString())
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + operator);
		}
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		switch (operator.toString())
		{
		case "-":
			return generator.newNegativeExpression(NegativeEnum.NEGATIVE, theExpr, this);
		default:
			throw new RuntimeException("Unexpected negative operator: " + operator);
		}
	}
	
	public static Java_NegativeExpression generateNegative(NegativeEnum sign, AbstractExpression theExpr, AbstractToken source)
	{
		String oper;
		switch (sign)
		{
		case POSITIVE:
			oper = "+";
			break;
		case NEGATIVE:
			oper = "-";
			break;
		default:
			return null;
		}

		Java_NegativeExpression expr = new Java_NegativeExpression();
		expr.expr = (Java_Expression) theExpr;
		expr.operator.setValue(oper);
		expr.setTransformationSource(source);
		return expr;
	}
}
