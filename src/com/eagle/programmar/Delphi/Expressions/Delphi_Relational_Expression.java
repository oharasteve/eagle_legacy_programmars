// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Relational_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Relational_Operator relOp;
	public @S(30) @OPT Delphi_Comment comment;
	public @S(40) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

	public static class Delphi_Relational_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice(
				"=", "<>", "<", ">", "<=", ">=");
		public @CHOICE Delphi_KeywordChoice XXIN = new Delphi_KeywordChoice("In", "Is");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (relOp.getWhich().toString())
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "<>":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (relOp.getWhich().toString())
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "<>":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected relational operator: " + relOp.getWhich());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (relOp.toString())
		{
		case "<":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.LESS_EQUALS, rightExpr, this);
		case "=":
			return generator.newRelationalExpression(leftExpr,	
					RelationalEnum.EQUALS, rightExpr, this);
		case "<>":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.NOT_EQUALS, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.GREATER_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.GREATER_THAN, rightExpr, this);
		}
		throw new RuntimeException("Unexpected relational operator: " + relOp.getWhich());
	}
}
