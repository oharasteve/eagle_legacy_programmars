// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_MultiplicativeExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("*", "/", "%");
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
		_metrics.operated(leftValue.getType(), rightValue.getType());

		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "*":
			interpreter.pushInt(leftInt * rightInt);
			return;
		case "/":
			interpreter.pushInt(leftInt / rightInt);
			return;
		case "%":
			interpreter.pushInt(leftInt % rightInt);
			return;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + oper);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "%":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}

	public static Rust_Expression generateMultiplicative(
			Rust_Expression leftExpr, MultiplicativeEnum oper,
			Rust_Expression rightExpr, AbstractToken source)
	{
		Rust_MultiplicativeExpression mul = new Rust_MultiplicativeExpression();
		mul.left = leftExpr;
		mul.right = rightExpr;
		switch (oper)
		{
		case TIMES:
			mul.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			mul.operator.setValue("/");
			break;
		case DIVIDE_NO_TRUNCATE:
			mul.operator.setValue("/");
			mul.left = Rust_CastExpression.newCastExpression("f64", leftExpr, source);
			mul.right = Rust_CastExpression.newCastExpression("f64", rightExpr, source);
			break;
		case MODULUS:
			mul.operator.setValue("%");
			break;
		case REMAINDER:
			mul.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper.toString());
		}
		mul.setTransformationSource(source);
		return Rust_Generator.wrapExpression(mul);
	}
}
