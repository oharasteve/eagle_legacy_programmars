// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.FSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_PunctuationChoice = com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_Additive_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Expression left = new com.eagle.programmar.FSharp.FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("symbol-and-operator-reference/arithmetic-operators") com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice operator = new com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice("+", "-");
		public @DOC("symbol-and-operator-reference/arithmetic-operators") FSharp_PunctuationChoice @operator = new FSharp_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.FSharp_Expression right = new com.eagle.programmar.FSharp.FSharp_Expression(this, AllowedPrecedence.HIGHER);
		public FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.ToString();

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
				case "+":
					interpreter.pushStr(leftStr + rightStr);
					return;
				}
			}
			else
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

			throw new Exception("Unexpected additive operator: " + oper);
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			switch (@operator.ToString())
			{
			case "+":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.PLUS, rightExpr, this);
			case "-":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.MINUS, rightExpr, this);
			default:
				throw new Exception("Unexpected additive operator: " + @operator);
			}
		}
	}

}
