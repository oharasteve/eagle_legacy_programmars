// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.MultiplicativeEnum;
import com.eagle.generate.EagleGenerator.ShiftEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Multiplicative_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Multiplicative_Operator multOp;
	public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

	public static class Delphi_Multiplicative_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice("*", "/");
		public @CHOICE Delphi_KeywordChoice XXword = new Delphi_KeywordChoice(
				"Div", "Mod", "And", "Shl", "Shr", "As");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (multOp.getWhich().toString().toLowerCase())
		{
		case "*":
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/":
			leftValue = interpreter.getIntValue(left);
			rightValue = interpreter.getIntValue(right);
			interpreter.pushDouble(leftValue / (double) rightValue);
			return;
		case "div":
			leftValue = interpreter.getIntValue(left);
			rightValue = interpreter.getIntValue(right);
			interpreter.pushInt(leftValue / rightValue);
			return;
		case "mod":
			leftValue = interpreter.getIntValue(left);
			rightValue = interpreter.getIntValue(right);
			interpreter.pushInt(leftValue % rightValue);
			return;
		case "and":
			boolean leftVal = interpreter.getBoolValue(left);
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(leftVal && rightVal);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + multOp.getWhich());
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (multOp.getWhich().toString().toLowerCase())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr, this);
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
			throw new RuntimeException("Unexpected multiplicative operator: " + multOp.getWhich());
		}
	}
}
