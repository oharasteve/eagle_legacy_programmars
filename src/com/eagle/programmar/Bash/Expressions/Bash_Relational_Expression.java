// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
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

public class Bash_Relational_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Bash_RelOp operator;
	public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

	public static class Bash_RelOp extends TokenChooser
	{
		public @CHOICE Bash_PunctuationChoice XXstrOp = new Bash_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public @CHOICE Bash_KeywordChoice XXnumOp = new Bash_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-le", "-ge");
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
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			case "<":
				interpreter.pushBool(leftStr.compareTo(rightStr) < 0);
				return;
			case "<=":
				interpreter.pushBool(leftStr.compareTo(rightStr) <= 0);
				return;
			case ">":
				interpreter.pushBool(leftStr.compareTo(rightStr) > 0);
				return;
			case ">=":
				interpreter.pushBool(leftStr.compareTo(rightStr) >= 0);
				return;
			}
		}

		if (leftValue.isInteger() || rightValue.isInteger())
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "-eq":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "-ne":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "-lt":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "-le":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case "-gt":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case "-ge":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}

		throw new RuntimeException("Unable to handle " + oper);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		switch (operator.toString())
		{
		case "-eq":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "-ne":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "-lt":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "-le":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case "-gt":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case "-ge":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + operator);
		}
	}
}
