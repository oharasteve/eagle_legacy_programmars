// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.NegativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_NegativeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
	public @S(20) @NOSPACE Java_Expression expr;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		String oper = operator.getValue();

		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(value.getType());

		int val = value.forceIntegerValue();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + oper);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

	public static Java_Expression generateNegative(NegativeEnum sign,
			Java_Expression theExpr, AbstractToken source)
	{
		Java_NegativeExpression negExpr = new Java_NegativeExpression();
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

		negExpr.expr = theExpr;
		negExpr.operator.setValue(oper);
		negExpr.setTransformationSource(source);
		return Java_Generator.wrapExpression(negExpr);
	}
}
