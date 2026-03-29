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
	using MultiplicativeEnum = com.eagle.transform.EagleGenerator.MultiplicativeEnum;
	using ShiftEnum = com.eagle.transform.EagleGenerator.ShiftEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Multiplicative_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Expression left = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Multiplicative_Operator operator;
		public Delphi_Multiplicative_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Delphi_Expression right = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public class Delphi_Multiplicative_Operator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_PunctuationChoice XXoperator = new com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice("*", "/");
			public Delphi_PunctuationChoice XXoperator = new Delphi_PunctuationChoice("*", "/");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_KeywordChoice XXword = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
			public Delphi_KeywordChoice XXword = new Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
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

			switch (oper.ToLower())
			{
			case "*":
				int leftInt1 = leftValue.forceIntegerValue();
				int rightInt1 = rightValue.forceIntegerValue();
				interpreter.pushInt(leftInt1 * rightInt1);
				return;
			case "/":
				int leftInt2 = leftValue.forceIntegerValue();
				int rightInt2 = rightValue.forceIntegerValue();
				interpreter.pushDouble(leftInt2 / (double) rightInt2);
				return;
			case "div":
				int leftInt3 = leftValue.forceIntegerValue();
				int rightInt3 = rightValue.forceIntegerValue();
				interpreter.pushInt(leftInt3 / rightInt3);
				return;
			case "mod":
				int leftInt4 = leftValue.forceIntegerValue();
				int rightInt4 = rightValue.forceIntegerValue();
				interpreter.pushInt(leftInt4 % rightInt4);
				return;
			case "and":
				bool leftBool = leftValue.forceBooleanValue();
				bool rightBool = rightValue.forceBooleanValue();
				interpreter.pushBool(leftBool && rightBool);
				return;
			}
			throw new Exception("Unexpected multiplicative operator: " + @operator.getWhich());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			switch (@operator.getWhich().ToString().ToLower())
			{
			case "*":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.TIMES, rightExpr, this);
			case "/":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr, this);
			case "div":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
			case "mod":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.REMAINDER, rightExpr, this);
			case "shl":
				return generator.newShiftExpression(leftExpr, EagleGenerator.ShiftEnum.LEFT, rightExpr, this);
			case "shr":
				return generator.newShiftExpression(leftExpr, EagleGenerator.ShiftEnum.RIGHT, rightExpr, this);
			case "and":
				return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
			default:
				throw new Exception("Unexpected multiplicative operator: " + @operator.getWhich());
			}
		}
	}

}
