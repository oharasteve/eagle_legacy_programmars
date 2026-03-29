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
	using MultiplicativeEnum = com.eagle.transform.EagleGenerator.MultiplicativeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_MultiplicativeExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Eaglish_Expression left = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Eaglish_MultiplicationOperator operator;
		public Eaglish_MultiplicationOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Eaglish_Expression right = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

		public class Eaglish_MultiplicationOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_PunctuationChoice XXoperSymbol = new com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice("*");
			public Eaglish_PunctuationChoice XXoperSymbol = new Eaglish_PunctuationChoice("*");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_KeywordChoice XXoperWord = new com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice("DIVIDE_TRUNCATE", "MODULUS", "REMAINDER");
			public Eaglish_KeywordChoice XXoperWord = new Eaglish_KeywordChoice("DIVIDE_TRUNCATE", "MODULUS", "REMAINDER");
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

			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.ToUpper())
			{
			case "*":
				interpreter.pushInt(leftInt * rightInt);
				return;
			case "DIVIDE_TRUNCATE":
				interpreter.pushInt(leftInt / rightInt);
				return;
			case "REMAINDER", "MODULUS":
				interpreter.pushInt(leftInt % rightInt);
				return;
			}
			throw new Exception("Unable to handle: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			string oper = @operator.getWhich().ToString();
			switch (oper.ToUpper())
			{
			case "*":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.TIMES, rightExpr, this);
			case "DIVIDE_TRUNCATE":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
			case "REMAINDER", "MODULUS":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.REMAINDER, rightExpr, this);
			default:
				throw new Exception("Unexpected multiplicative operator: " + oper);
			}
		}
	}

}
