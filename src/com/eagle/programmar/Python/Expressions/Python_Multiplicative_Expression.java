// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Multiplicative_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			break;
		case "/":
			interpreter.pushDouble((double)leftValue / rightValue);
			break;
		case "//":
			interpreter.pushInt(leftValue / rightValue);
			break;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr, this);
		case "//":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "%":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.MODULUS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
	
	public static Python_Multiplicative_Expression generateExpression(AbstractExpression leftExpr, MultiplicativeEnum oper, AbstractExpression rightExpr, AbstractToken source)
	{
		Python_Multiplicative_Expression expr = new Python_Multiplicative_Expression();
		expr.left = (Python_Expression) leftExpr;
		expr.right = (Python_Expression) rightExpr;
		switch (oper)
		{
		case TIMES:
			expr.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			expr.operator.setValue("//");
			break;
		case DIVIDE_NO_TRUNCATE:
			expr.operator.setValue("/");
			break;
		case MODULUS:
			expr.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
