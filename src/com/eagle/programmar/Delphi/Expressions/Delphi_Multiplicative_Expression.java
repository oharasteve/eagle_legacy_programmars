// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleGenerator.ShiftEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Multiplicative_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Multiplicative_Operator operator;
	public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

	public static class Delphi_Multiplicative_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice("*", "/");
		public @CHOICE Delphi_KeywordChoice XXword = new Delphi_KeywordChoice(
				"Div", "Mod", "And", "Shl", "Shr", "As");
	}

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.getWhich().toString();

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator.getWhich(), oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		switch (oper.toLowerCase())
		{
		case "*":
			int leftInt1 = leftValue.forceIntegerValue();
			int rightInt1 = rightValue.forceIntegerValue();
			interpreter.pushInt(leftInt1 * rightInt1);
			return;
		case "/":
			int leftInt2 = leftValue.forceIntegerValue();
			int rightInt2 = rightValue.forceIntegerValue();
			interpreter.pushDouble(leftInt2 / (double) rightInt2);
			return;
		case "div":
			int leftInt3 = leftValue.forceIntegerValue();
			int rightInt3 = rightValue.forceIntegerValue();
			interpreter.pushInt(leftInt3 / rightInt3);
			return;
		case "mod":
			int leftInt4 = leftValue.forceIntegerValue();
			int rightInt4 = rightValue.forceIntegerValue();
			interpreter.pushInt(leftInt4 % rightInt4);
			return;
		case "and":
			boolean leftBool = leftValue.forceBooleanValue();
			boolean rightBool = rightValue.forceBooleanValue();
			interpreter.pushBool(leftBool && rightBool);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + operator.getWhich());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.getWhich().toString().toLowerCase())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr,
					this);
		case "div":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "mod":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		case "shl":
			return generator.newShiftExpression(leftExpr, ShiftEnum.LEFT, rightExpr, this);
		case "shr":
			return generator.newShiftExpression(leftExpr, ShiftEnum.RIGHT, rightExpr, this);
		case "and":
			return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator.getWhich());
		}
	}
}
