// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using Algol68_PunctuationChoice = com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
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

	public class Algol68_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Expression left = new com.eagle.programmar.Algol68.Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Algol68_RelOp operator;
		public Algol68_RelOp @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Algol68_Expression right = new com.eagle.programmar.Algol68.Algol68_Expression(this, AllowedPrecedence.HIGHER);
		public Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

		public class Algol68_RelOp : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_PunctuationChoice XXsymbol = new com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice("<", ">", "<=", ">=", "=", "~=", "/=");
			public Algol68_PunctuationChoice XXsymbol = new Algol68_PunctuationChoice("<", ">", "<=", ">=", "=", "~=", "/=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_KeywordChoice XXword = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("LT", "LE", "EQ", "NE", "GE", "GT");
			public Algol68_KeywordChoice XXword = new Algol68_KeywordChoice("LT", "LE", "EQ", "NE", "GE", "GT");
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
				case "=", "EQ":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "~=", "/=", "NE":
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
				case "=", "EQ":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case "~=", "/=", "NE":
					interpreter.pushBool(leftInt != rightInt);
					return;
				case "<", "LT":
					interpreter.pushBool(leftInt < rightInt);
					return;
				case "<=", "LE":
					interpreter.pushBool(leftInt <= rightInt);
					return;
				case ">", "GT":
					interpreter.pushBool(leftInt > rightInt);
					return;
				case ">=", "GE":
					interpreter.pushBool(leftInt >= rightInt);
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
			case "=", "EQ":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "~=", "/=", "NE":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case "<", "LT":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "<=", "LE":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case ">", "GT":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case ">=", "GE":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + oper);
			}
		}
	}

}
