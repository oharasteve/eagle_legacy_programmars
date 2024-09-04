// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_NegativeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
	public @S(20) CSharp_Expression expr;

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
			return generator.newNegativeExpression(theExpr, this);
		default:
			throw new RuntimeException("Unexpected negative operator: " + operator);
		}
	}
	
	public static CSharp_NegativeExpression generateExpression(AbstractExpression theExpr, AbstractToken source)
	{
		CSharp_NegativeExpression expr = new CSharp_NegativeExpression();
		expr.expr = (CSharp_Expression) theExpr;
		expr.operator.setValue("-");
		expr.setTransformationSource(source);
		return expr;
	}
}
