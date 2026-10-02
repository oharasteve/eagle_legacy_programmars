// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.tokens.AbstractToken;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.generate.NegativeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_NegativeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
	public @S(20) Javascript_Expression expr;

	private @SKIP Operator1Metrics _metrics = null;

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
			throw new RuntimeException("Unexpected negation operator: " + operator);
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double v = JsValues.toNumber(JsRuntime.of(interpreter).eval(expr));
		interpreter.pushEagleValue(JsValues.num("-".equals(operator.getValue()) ? -v : v));
	}
}
