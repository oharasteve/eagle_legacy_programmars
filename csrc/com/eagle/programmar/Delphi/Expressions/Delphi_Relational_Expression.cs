// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Delphi.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using Delphi_PunctuationChoice = com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
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

	public class Delphi_Relational_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Expression left = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Relational_Operator operator;
		public Delphi_Relational_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Delphi.Delphi_Expression right = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public class Delphi_Relational_Operator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_PunctuationChoice XXoperator = new com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice("=", "<>", "<", ">", "<=", ">=");
			public Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice("=", "<>", "<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_KeywordChoice XXIN = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("In", "Is");
			public Delphi_KeywordChoice XXIN = new Delphi_KeywordChoice("In", "Is");
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
				_metrics = new Operator2Metrics(interpreter._metrics, @operator, oper);
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
				case "<>":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				}
			}
			else if (leftValue.isDouble() || rightValue.isDouble())
			{
				double leftDbl = leftValue.forceDoubleValue();
				double rightDbl = rightValue.forceDoubleValue();
				switch (oper)
				{
				case "=":
					interpreter.pushBool(leftDbl == rightDbl);
					return;
				case "<>":
					interpreter.pushBool(leftDbl != rightDbl);
					return;
				case "<":
					interpreter.pushBool(leftDbl < rightDbl);
					return;
				case "<=":
					interpreter.pushBool(leftDbl <= rightDbl);
					return;
				case ">":
					interpreter.pushBool(leftDbl > rightDbl);
					return;
				case ">=":
					interpreter.pushBool(leftDbl >= rightDbl);
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
			throw new Exception("Unexpected relational operator: " + @operator.getWhich());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			switch (@operator.getWhich().ToString())
			{
			case "<":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "<=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case "=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "<>":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case ">=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			case ">":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			}
			throw new Exception("Unexpected relational operator: " + @operator.getWhich());
		}
	}

}
