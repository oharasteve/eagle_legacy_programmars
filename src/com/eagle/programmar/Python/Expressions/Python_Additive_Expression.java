// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AdditiveEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Additive;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Functions.Python_Str_Function;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Additive_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_Additive<Python_Expression>
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

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
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "+":
				interpreter.pushStr(leftStr + rightStr);
				break;
			default:
				throw new RuntimeException("Unexpected concatenation operator: " + oper);
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "+":
				interpreter.pushInt(leftInt + rightInt);
				break;
			case "-":
				interpreter.pushInt(leftInt - rightInt);
				break;
			default:
				throw new RuntimeException("Unexpected additive operator: " + oper);
			}
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);
		
		switch (operator.toString())
		{
		case "+":
			return generator.newAdditiveExpression(types, leftExpr, AdditiveEnum.PLUS, rightExpr, this);
		case "-":
			return generator.newAdditiveExpression(types, leftExpr, AdditiveEnum.MINUS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
	
	@Override
	public Python_Expression generateAdditive(Oper2Types types, Python_Expression leftExpr,
			AdditiveEnum oper, Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		if (types != null)
		{
			if (types._type1.equals(EagleString.STRING) && ! types._type2.equals(EagleString.STRING))
			{
				Python_Str_Function strFn = new Python_Str_Function();
				this.right = strFn.generateString(rightExpr, rightExpr);
			}
		}
		
		switch (oper)
		{
		case PLUS:
			this.operator.setValue("+");
			break;
		case MINUS:
			this.operator.setValue("-");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
