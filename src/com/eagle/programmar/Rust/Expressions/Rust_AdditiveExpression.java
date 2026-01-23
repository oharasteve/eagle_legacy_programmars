// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_AdditiveExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("+", "-");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

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

		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(leftInt + rightInt);
			return;
		case "-":
			interpreter.pushInt(leftInt - rightInt);
			return;
		default:
			throw new RuntimeException("Unexpected additive operator: " + oper);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
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

	public Rust_Expression generateAdditive(Oper2Types types, Rust_Expression leftExpr, AdditiveEnum oper,
			Rust_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		if (types != null)
		{
			if (types._type1.equals(EagleString.STRING) && !types._type2.equals(EagleString.STRING))
			{
				Rust_ToStringMethod strMeth = new Rust_ToStringMethod();
				this.right = strMeth.generateString(rightExpr, rightExpr);
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
		}
		this.setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
