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
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using Delphi_PunctuationChoice = com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Additive_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Expression left = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Additive_Operator operator;
		public Delphi_Additive_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Delphi_Expression right = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public class Delphi_Additive_Operator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_PunctuationChoice XXoperator = new com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice("+", "-");
			public Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_KeywordChoice XXOR = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Or", "Xor");
			public Delphi_KeywordChoice XXOR = new Delphi_KeywordChoice("Or", "Xor");
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
				case "+":
					interpreter.pushStr(leftStr + rightStr);
					return;
				}
			}

			if (leftValue.isDouble() || rightValue.isDouble())
			{
				double leftDbl = leftValue.forceDoubleValue();
				double rightDbl = rightValue.forceDoubleValue();
				switch (oper)
				{
				case "+":
					interpreter.pushDouble(leftDbl + rightDbl);
					return;
				case "-":
					interpreter.pushDouble(leftDbl - rightDbl);
					return;
				}
			}

			if (leftValue.isInteger() && rightValue.isInteger())
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper)
				{
				case "+":
					interpreter.pushInt(leftInt + rightInt);
					return;
				case "-":
					interpreter.pushInt(leftInt - rightInt);
					return;
				}
			}

			if (leftValue.isBoolean() || rightValue.isBoolean())
			{
				bool leftBool = leftValue.forceBooleanValue();
				bool rightBool = rightValue.forceBooleanValue();
				switch (oper)
				{
				case "Or":
					interpreter.pushBool(leftBool || rightBool);
					return;
				case "Xor":
					interpreter.pushBool(leftBool ^ rightBool);
					return;
				}
			}

			throw new Exception("Unexpected additive operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			switch (@operator.getWhich().ToString().ToLower())
			{
			case "+":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.PLUS, rightExpr, this);
			case "-":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.MINUS, rightExpr, this);
			case "or":
				return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.OR, rightExpr, this);
			case "xor":
				return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.XOR, rightExpr, this);
			default:
				throw new Exception("Unexpected additive operator: " + @operator.getWhich());
			}
		}
	}

}
