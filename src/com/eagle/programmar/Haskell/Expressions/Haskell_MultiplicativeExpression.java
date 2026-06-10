// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 2, 2026

package com.eagle.programmar.Haskell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_KeywordChoice;
import com.eagle.programmar.Haskell.Terminals.Haskell_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Haskell_MultiplicativeExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Haskell_Expression left = new Haskell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Haskell_MultOper operator;
	public @S(30) Haskell_Expression right = new Haskell_Expression(this, AllowedPrecedence.HIGHER);

	public static class Haskell_MultOper extends TokenChooser
	{
		public @CHOICE Haskell_PunctuationChoice XXTimes = new Haskell_PunctuationChoice("*", "/");
		public @CHOICE Haskell_KeywordChoice XXMod = new Haskell_KeywordChoice("`div`", "`quot`", "`mod`", "`rem`");
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
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.getType(), rightValue.getType());

		if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDbl = leftValue.forceDoubleValue();
			double rightDbl = rightValue.forceDoubleValue();
			switch (oper)
			{
			case "*":
				interpreter.pushDouble(leftDbl * rightDbl);
				return;
			case "/":
				interpreter.pushDouble(leftDbl / rightDbl);
				return;
			}
		}
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "*":
			interpreter.pushInt(leftInt * rightInt);
			return;
		case "/":
			interpreter.pushDouble((double)leftInt / (double)rightInt);
			return;
		case "`quot`":
			interpreter.pushInt(leftInt / rightInt);
			return;
		case "`div`":
			interpreter.pushInt(Math.floorDiv(leftInt, rightInt));
			return;
		case "`rem`":
			interpreter.pushInt(leftInt % rightInt);
			return;
		case "`mod`":
			interpreter.pushInt(Math.floorMod(leftInt, rightInt));
			return;
		}

		throw new RuntimeException("Unexpected multiplicative operator: " + oper);
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
		case "`rem`":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		case "`mod`":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.MODULUS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
}
