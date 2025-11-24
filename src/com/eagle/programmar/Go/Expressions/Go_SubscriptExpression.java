// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Go_Expression expr = new Go_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Go_Expression subscr1;
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT Go_Expression subscr2;
	public @S(60) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(subscr1);
		int ec = interpreter.getIntValue(subscr2);
		interpreter.pushStr(val.substring(sc, ec));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (colon != null && colon.isPresent())
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			AbstractExpression scExpr = transformer.transformExpression(generator, subscr1);
			AbstractExpression ecExpr = transformer.transformExpression(generator, subscr2);
			return generator.newSubstringFunction(theExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
					SubstringECEnum.GIVEN_EC_PLUS_ONE, ecExpr, false, this);
		}

		if (expr.getWhich() instanceof Go_VariableExpression)
		{
			Go_VariableExpression varExpr = (Go_VariableExpression) expr.getWhich();
			String varName = varExpr.variable.vars.first().getValue();
			AbstractExpression subExpr = transformer.transformExpression(generator, subscr1);
			return generator.newVariableExpression(varName, SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
		}

		throw new RuntimeException("Unable to handle subscript");
	}
}
