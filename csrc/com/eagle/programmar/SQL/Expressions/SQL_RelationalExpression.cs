// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.SQL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using SQL_PunctuationChoice = com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.SQL_Expression left = new com.eagle.programmar.SQL.SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) SQL_RelationalOperator operator;
		public SQL_RelationalOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.SQL_Expression right = new com.eagle.programmar.SQL.SQL_Expression(this, AllowedPrecedence.HIGHER);
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

		public class SQL_RelationalOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_KeywordChoice XXLIKE = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("LIKE", "IN", "IS");
			public SQL_KeywordChoice XXLIKE = new SQL_KeywordChoice("LIKE", "IN", "IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_PunctuationChoice XXoperator = new com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
			public SQL_PunctuationChoice XXoperator = new SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.getWhich().ToString();

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, @operator.getWhich(), oper);
			}
			_metrics.operated(leftValue.getType(), rightValue.getType());

			if (leftValue.isString() || rightValue.isString())
			{
				string leftStr = leftValue.forceStringValue();
				string rightStr = rightValue.forceStringValue();
				switch (oper)
				{
				case "=":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "!=":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper.ToUpper())
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
			throw new Exception("Unexpected relational operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			string oper = @operator.getWhich().ToString();
			switch (oper.ToUpper())
			{
			case "=", "IS":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "!=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case "<":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "<=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case ">":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case ">=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + oper);
			}
		}
	}

}
