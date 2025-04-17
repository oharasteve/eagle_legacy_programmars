// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.NegativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Negative_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_PunctuationChoice sign = new Python_PunctuationChoice("*", "-", "+", "~");
	public @S(20) Python_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (sign.toString())
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + sign);
		}
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		switch (sign.toString())
		{
		case "-":
			return generator.newNegativeExpression(NegativeEnum.NEGATIVE, theExpr, this);
		default:
			throw new RuntimeException("Unexpected negative operator: " + sign);
		}
	}
	
	public static Python_Negative_Expression generateNegative(NegativeEnum sign,
			AbstractExpression theExpr, AbstractToken source)
	{
		Python_Negative_Expression expr = new Python_Negative_Expression();
		expr.expr = (Python_Expression) theExpr;
		expr.sign.setValue("-");
		expr.setTransformationSource(source);
		return expr;
	}
}
