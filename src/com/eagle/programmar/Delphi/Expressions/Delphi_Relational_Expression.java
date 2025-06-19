// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class Delphi_Relational_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Relational_Operator operator;
	public @S(30) @OPT Delphi_Comment comment;
	public @S(40) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

	public static class Delphi_Relational_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice(
				"=", "<>", "<", ">", "<=", ">=");
		public @CHOICE Delphi_KeywordChoice XXIN = new Delphi_KeywordChoice("In", "Is");
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
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
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
			switch (oper)
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
		throw new RuntimeException("Unexpected relational operator: " + operator.getWhich());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);
		
		switch (operator.getWhich().toString())
		{
		case "<":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_EQUALS, rightExpr, this);
		case "=":
			return generator.newRelationalExpression(types, leftExpr,	
					RelationalEnum.EQUALS, rightExpr, this);
		case "<>":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.NOT_EQUALS, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_THAN, rightExpr, this);
		}
		throw new RuntimeException("Unexpected relational operator: " + operator.getWhich());
	}
}
