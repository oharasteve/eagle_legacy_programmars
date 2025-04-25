// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.NegativeEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Negative;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_NegativeExpression extends PrimaryOperator implements EagleRunnable,
		EagleTransformableExpression, Eagle_Generate_Negative<Java_Expression>
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
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		switch (operator.toString())
		{
		case "+":
			return theExpr;
		case "-":
			return generator.newNegativeExpression(NegativeEnum.NEGATIVE, theExpr, this);
		default:
			throw new RuntimeException("Unexpected negative operator: " + operator);
		}
	}
	
	@Override
	public Java_Expression generateNegative(NegativeEnum sign,
			Java_Expression theExpr, AbstractToken source)
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

		this.expr = theExpr;
		this.operator.setValue(oper);
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
