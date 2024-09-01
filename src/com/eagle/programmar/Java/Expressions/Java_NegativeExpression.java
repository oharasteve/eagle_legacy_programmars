// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGeneratableExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_NegativeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression, EagleGeneratableExpression
{
	public @S(10) Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
	public @S(20) Java_Expression expr;

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
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		switch (operator.toString())
		{
		case "-":
			return generator.newNegativeExpression(theExpr);
		default:
			throw new RuntimeException("Unexpected negative operator: " + operator);
		}
	}
	
	public static Java_NegativeExpression generateExpression(String oper, AbstractExpression theExpr)
	{
		Java_NegativeExpression expr = new Java_NegativeExpression();
		expr.expr = (Java_Expression) theExpr;
		expr.operator.setValue(oper);
		return expr;
	}
}
