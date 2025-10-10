// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Relational_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Relational_Operator operator;
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static class Python_IN_Operator extends TokenSequence
	{
		public @S(10) @OPT Python_Keyword NOT = new Python_Keyword("not");
		public @S(20) Python_Keyword IN = new Python_Keyword("in");
	}

	public static class Python_IS_Operator extends TokenSequence
	{
		public @S(10) Python_Keyword IS = new Python_Keyword("is");
		public @S(20) @OPT Python_Keyword NOT = new Python_Keyword("not");
	}

	public static class Python_Relational_Operator extends TokenChooser
	{
		public @CHOICE Python_PunctuationChoice XXoperatorSymbol =
				new Python_PunctuationChoice("==", "!=", "<>", "<=", ">=", "<", ">");
		public @CHOICE Python_IN_Operator XXinOperator;
		public @CHOICE Python_IS_Operator XXisOperator;
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
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=", "<>":
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
			default:
				throw new RuntimeException("Unable to handle operator: " + oper);
			}
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator.getWhich());

		switch (operator.getWhich().toString())
		{
		case "==":
			return generator.newRelationalExpression(types, leftExpr,	
					RelationalEnum.EQUALS, rightExpr, this);
		case "!=", "<>":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_EQUALS, rightExpr, this);
		}
		throw new RuntimeException("Unexpected relational operator: " + operator.getWhich());
	}

	public Python_Expression generateRelational(Oper2Types types, Python_Expression leftExpr,
			RelationalEnum relOp, Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		
		Python_PunctuationChoice oper = null;
		switch (relOp)
		{
		case EQUALS:
			oper = new Python_PunctuationChoice("==");
			break;
		case NOT_EQUALS:
			oper = new Python_PunctuationChoice("!=");
			break;
		case LESS_THAN:
			oper = new Python_PunctuationChoice("<");
			break;
		case LESS_EQUALS:
			oper = new Python_PunctuationChoice("<=");
			break;
		case GREATER_THAN:
			oper = new Python_PunctuationChoice(">");
			break;
		case GREATER_EQUALS:
			oper = new Python_PunctuationChoice(">=");
			break;
		}
		this.operator = new Python_Relational_Operator();
		this.operator.setWhich(oper);
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
