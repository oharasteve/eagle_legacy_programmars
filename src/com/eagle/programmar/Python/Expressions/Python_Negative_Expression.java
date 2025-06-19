// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.NegativeEnum;

public class Python_Negative_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_PunctuationChoice operator = new Python_PunctuationChoice("*", "-", "+", "~");
	public @S(20) Python_Expression expr;

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
		_metrics.operated(value.typeName());

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
			EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		switch (operator.toString())
		{
		case "-":
			return generator.newNegativeExpression(NegativeEnum.NEGATIVE, theExpr, this);
		default:
			throw new RuntimeException("Unexpected negative operator: " + operator);
		}
	}
	
	public Python_Expression generateNegative(NegativeEnum sign,
			Python_Expression theExpr, AbstractToken source)
	{
		this.expr = theExpr;
		this.operator.setValue("-");
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
