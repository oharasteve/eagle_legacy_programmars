// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class PLI_TrimFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PLI_Keyword TRIM = new PLI_Keyword("TRIM");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PLI_Expression expression;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);

		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, TRIM, TRIM.getValue());
		}
		_metrics.operated(value.getType());

		String str = value.forceStringValue();
		interpreter.pushStr(str.trim());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper1Types types = transformer.findOperator1Metric(TRIM);
		TypeEnum type;
		if (types == null)
		{
			type = TypeEnum.INTEGER;	// Rash assumption
		}
		else
		{
			type = types._type1;
		}
		
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		if (type != TypeEnum.STRING)
		{
			theExpr = generator.newStringFunction(type, theExpr, this);
		}
		return generator.newTrimFunction(theExpr, this);
	}
}
