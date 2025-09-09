// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleRange;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Rust_Expression subscrExpr = new Rust_Expression();
	public @S(60) PunctuationRightBracket rightBracket;

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		int lowValue;
		int highValue = Integer.MAX_VALUE;

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics,
					leftBracket, leftBracket.toString());
		}
		_metrics.operated(value.typeName(), EagleInteger.INTEGER);

		EagleValue low = interpreter.getEagleValue(subscrExpr);
		if (low.isRange())
		{
			EagleRange range = (EagleRange) low;
			lowValue = range._lowValue;
			if (range._hasHigh)
			{
				highValue = range._highValue;
			}
		}
		else
		{
			lowValue = low.forceIntegerValue();
		}

		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			EagleValue val = array.getValue(lowValue);
			interpreter.pushEagleValue(val);
		}
		else if (value.isString())
		{
			String str = value.forceStringValue();
			if (highValue > str.length()) highValue = str.length();
			String substr = str.substring(lowValue, highValue);
			interpreter.pushStr(substr);
		}
		else
		{
			throw new RuntimeException("Unable to handle " + value.toString());
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, expr);
		Oper2Types types = transformer.findOperator2Metric(leftBracket);
		
		if (types._type1.equals(EagleString.STRING))
		{
			if (subscrExpr.getWhich() instanceof Rust_RangeExpression)
			{
				// string with a range subscript
				Rust_RangeExpression range = (Rust_RangeExpression) subscrExpr.getWhich();
				AbstractExpression newSc = transformer.transformExpression(generator, range.lowExpression);
				AbstractExpression newEc = null;
				if (range.highExpression != null && range.highExpression.isPresent())
				{
					newEc = transformer.transformExpression(generator, range.highExpression);
				}
				return generator.newSubstringFunction(newExpr, newSc,
						SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.GIVEN_EC_PLUS_ONE,
						newEc, false, this);
			}
			
			// string with a single subscript, not a range
			AbstractExpression newSubscr = transformer.transformExpression(generator, subscrExpr);
			AbstractExpression one = generator.newNumberExpression("1", null);
			return generator.newSubstringFunction(newExpr, newSubscr,
					SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.GIVEN_NC,
					one, false, this);
		}
		
		// Assume it must be an array
		if (expr.getWhich() instanceof Rust_VariableExpression)
		{
			Rust_VariableExpression varExpr = (Rust_VariableExpression) expr.getWhich();
			AbstractExpression newSubscr = transformer.transformExpression(generator, subscrExpr);
			return generator.newVariableExpression(varExpr.variable.var.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, newSubscr, expr);
		}
		
		throw new RuntimeException("Unable to handle " + expr);
	}
}
