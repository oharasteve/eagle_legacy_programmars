// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.generate.AdditiveEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
		_metrics.operated(leftValue.getType(), rightValue.getType());

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
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

	public static Rust_Expression generateAdditive(Oper2Types types, Rust_Expression leftExpr, AdditiveEnum oper,
			Rust_Expression rightExpr, AbstractToken source)
	{
		// Don't bother if both are constants, just use the sum (or difference) directly
		AbstractToken whichLeft = leftExpr.getWhich();
		if (whichLeft instanceof Rust_Number)
		{
			AbstractToken whichRight = rightExpr.getWhich();
			if (whichRight instanceof Rust_Number)
			{
				Rust_Number leftNum = (Rust_Number) whichLeft;
				Rust_Number rightNum = (Rust_Number) whichRight;
				try
				{
					int left = Integer.parseInt(leftNum.getValue());
					int right = Integer.parseInt(rightNum.getValue());
					switch (oper)
					{
					case PLUS:
						return Rust_Generator.wrapExpression(Rust_Number.createNumber(left + right));
					case MINUS:
						return Rust_Generator.wrapExpression(Rust_Number.createNumber(left - right));
					default:
						// Ignore this case
					}
				}
				catch (Exception ex)
				{
					// Ignore errors
				}
			}
		}

		Rust_AdditiveExpression add = new Rust_AdditiveExpression();
		add.left = leftExpr;
		add.right = rightExpr;
		
		boolean stringy = false;
		
		AbstractToken leftWhich = leftExpr.getWhich();
		AbstractToken rightWhich = rightExpr.getWhich();
		if ((leftWhich instanceof Rust_Literal) || (rightWhich instanceof Rust_Literal))
		{
			stringy = true;
		}

		if (types != null && (types._type1 == TypeEnum.STRING || types._type2 == TypeEnum.STRING))
		{
			stringy = true;
		}

		switch (oper)
		{
		case PLUS:
			add.operator.setValue("+");
			break;
		case MINUS:
			add.operator.setValue("-");
			break;
		}

		if (stringy)
		{
			return sharedAppend(add, source);
		}
		
		add.setTransformationSource(source);
		return Rust_Generator.wrapExpression(add);
	}
	

	public static Rust_Expression generateAppend(Rust_Expression leftExpr,
			Rust_Expression rightExpr, AbstractToken source)
	{
		Rust_AdditiveExpression add = new Rust_AdditiveExpression();
		add.left = leftExpr;
		add.operator.setValue("+");
		add.right = rightExpr;
		return sharedAppend(add, source);
	}	
	
	private static Rust_Expression sharedAppend(Rust_AdditiveExpression add, AbstractToken source)
	{
		// Force String's on both sides of the + operator
		AbstractToken leftWhich = add.left.getWhich();
		AbstractToken rightWhich = add.right.getWhich();
		if (leftWhich instanceof Rust_VariableExpression)
		{
			add.left = Rust_ToStringMethod.generateString(TypeEnum.OTHER, add.left, add.left);
		}
		else if (leftWhich instanceof Rust_Literal)
		{
			add.left = Rust_ToStringMethod.generateString(TypeEnum.STRING, add.left, add.left);
		}

		if (rightWhich instanceof Rust_VariableExpression)
		{
			add.right = Rust_BorrowExpression.generateBorrow(add.right, add.right);
			add.right = Rust_ToStringMethod.generateString(TypeEnum.OTHER, add.right, null);
		}
		else if (rightWhich instanceof Rust_ToStringMethod)
		{
			add.right = Rust_BorrowExpression.generateBorrow(add.right, add.right);
		}

		add.setTransformationSource(source);
		return Rust_Generator.wrapExpression(add);
	}
}
