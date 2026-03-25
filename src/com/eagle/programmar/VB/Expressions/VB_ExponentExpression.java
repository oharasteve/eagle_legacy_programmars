// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_ExponentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	// Note: VB does these left-to-right. Most languages do right-to-left
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) @DOC("operators/arithmetic-operators") VB_Punctuation operator = new VB_Punctuation('^');
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.ATLEAST);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.getType(), rightValue.getType());

		double leftDbl = leftValue.forceDoubleValue();
		int rightInt = rightValue.forceIntegerValue();
		interpreter.pushDouble(Math.pow(leftDbl, rightInt));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newExponentExpression(leftExpr, rightExpr, this);
	}
}
