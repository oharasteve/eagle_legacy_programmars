// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Ruby.Ruby_Expression;
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

public class Ruby_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Ruby_Expression subscript;
	public @S(40) PunctuationRightBracket rightBracket;
	
	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, this, leftBracket.getValue());
		}
		_metrics.operated(value.typeName());

		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(subscript);
			EagleValue val = array.getValue(sub);
			interpreter.pushEagleValue(val);
		}
		else if (value.isString() && subscript.getWhich() instanceof Ruby_RangeExpression)
		{
			String str = value.forceStringValue();
			Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.getWhich();
			int len = str.length();
			int sc = interpreter.getIntValue(range.left);
			int ec = interpreter.getIntValue(range.right) + 1;
			if (ec > len) ec = len;
			interpreter.pushStr(str.substring(sc, ec));
		}
		else
		{
			throw new RuntimeException("Unable to handle subscript");
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		Oper1Types type = transformer.findOperator1Metric(this);
		if (type != null &&
				type._type1.equals(EagleString.STRING) &&
				subscript.getWhich() instanceof Ruby_RangeExpression)
		{
			Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.getWhich();
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			AbstractExpression scExpr = transformer.transformExpression(generator, range.left);
			AbstractExpression ecExpr = transformer.transformExpression(generator, range.right);
			return generator.newSubstringFunction(theExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
					SubstringECEnum.GIVEN_EC, ecExpr, false, this);
		}

		if (expr.getWhich() instanceof Ruby_VariableExpression)
		{
			Ruby_VariableExpression varExpr = (Ruby_VariableExpression) expr.getWhich();
			String varName = varExpr.variable.vars.first().getValue();
			AbstractExpression subExpr = transformer.transformExpression(generator, subscript);
			return generator.newVariableExpression(varName, SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
		}

		throw new RuntimeException("Unable to handle subscript");
	}
}
