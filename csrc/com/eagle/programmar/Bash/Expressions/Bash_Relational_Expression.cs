// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Bash.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
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

	public class Bash_Relational_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Bash_Expression left = new com.eagle.programmar.Bash.Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Bash_RelOp operator;
		public Bash_RelOp @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_Expression right = new com.eagle.programmar.Bash.Bash_Expression(this, AllowedPrecedence.HIGHER);
		public Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

		public class Bash_RelOp : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_PunctuationChoice XXstrOp = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
			public Bash_PunctuationChoice XXstrOp = new Bash_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXnumOp = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-le", "-ge");
			public Bash_KeywordChoice XXnumOp = new Bash_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-le", "-ge");
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
				case "==":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "!=":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				case "<":
					interpreter.pushBool(string.CompareOrdinal(leftStr, rightStr) < 0);
					return;
				case "<=":
					interpreter.pushBool(string.CompareOrdinal(leftStr, rightStr) <= 0);
					return;
				case ">":
					interpreter.pushBool(string.CompareOrdinal(leftStr, rightStr) > 0);
					return;
				case ">=":
					interpreter.pushBool(string.CompareOrdinal(leftStr, rightStr) >= 0);
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

			throw new Exception("Unable to handle " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			switch (@operator.ToString())
			{
			case "-eq":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "-ne":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case "-lt":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "-le":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case "-gt":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case "-ge":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + @operator);
			}
		}
	}

}
