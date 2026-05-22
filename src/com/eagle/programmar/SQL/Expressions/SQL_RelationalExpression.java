// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class SQL_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) SQL_RelationalOperator operator;
	public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

	public static class SQL_RelationalOperator extends TokenChooser
	{
		public @CHOICE SQL_KeywordChoice XXLIKE = new SQL_KeywordChoice("LIKE", "IN", "IS");
		public @CHOICE SQL_PunctuationChoice XXoperator = new SQL_PunctuationChoice(
				"=", "!=", "<", ">", "<=", ">=");
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
		_metrics.operated(leftValue.getType(), rightValue.getType());

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.toUpperCase())
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=":
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
			case "IS":
				interpreter.pushBool((leftInt != 0) == (rightInt != 0));
				return;
			}
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		String oper = operator.getWhich().toString();
		switch (oper.toUpperCase())
		{
		case "=", "IS":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "!=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + oper);
		}
	}
}
