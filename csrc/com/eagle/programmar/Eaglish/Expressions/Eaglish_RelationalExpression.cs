// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

namespace com.eagle.programmar.Eaglish.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_KeywordChoice = com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
	using Eaglish_PunctuationChoice = com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
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

	public class Eaglish_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Eaglish_Expression left = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Eaglish_RelationalOperator operator;
		public Eaglish_RelationalOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Eaglish_Expression right = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

		public class Eaglish_RelationalOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_PunctuationChoice XXoperSymbol = new com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice("=", "<", ">", "<=", ">=");
			public Eaglish_PunctuationChoice XXoperSymbol = new Eaglish_PunctuationChoice("=", "<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_KeywordChoice XXoperWord = new com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice("EQUALS", "NOT_EQUALS");
			public Eaglish_KeywordChoice XXoperWord = new Eaglish_KeywordChoice("EQUALS", "NOT_EQUALS");
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
				string leftStr = interpreter.getStrValue(left);
				string rightStr = interpreter.getStrValue(right);
				switch (oper.ToUpper())
				{
				case "=", "EQUALS":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "NOT_EQUALS":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				default:
					throw new Exception("Unable to handle " + oper + " with strings");
				}
			}

			if (leftValue.isInteger() || rightValue.isInteger())
			{
				int leftInt = interpreter.getIntValue(left);
				int rightInt = interpreter.getIntValue(right);
				switch (oper.ToUpper())
				{
				case "=", "EQUALS":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case "NOT_EQUALS":
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
					throw new Exception("Unable to handle " + oper + " with integers");
				}
			}

			if (leftValue.isBoolean() || rightValue.isBoolean())
			{
				bool leftBool = interpreter.getBoolValue(left);
				bool rightBool = interpreter.getBoolValue(right);
				switch (oper.ToUpper())
				{
				case "=", "EQUALS":
					interpreter.pushBool(leftBool == rightBool);
					return;
				case "NOT_EQUALS":
					interpreter.pushBool(leftBool != rightBool);
					return;
				}
			}

			throw new Exception("Unexpected operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator.getWhich());
			string oper = @operator.getWhich().ToString();

			switch (oper.ToUpper())
			{
			case "=", "EQUALS":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "NOT_EQUALS":
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
