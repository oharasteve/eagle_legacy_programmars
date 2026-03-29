// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_Punctuation = com.eagle.programmar.Basic.Terminals.Basic_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Basic_ExponentExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Basic_Expression left = new com.eagle.programmar.Basic.Basic_Expression(this, AllowedPrecedence.HIGHER);
		public Basic_Expression left = new Basic_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Terminals.Basic_Punctuation operator = new com.eagle.programmar.Basic.Terminals.Basic_Punctuation("^");
		public Basic_Punctuation @operator = new Basic_Punctuation("^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Basic_Expression right = new com.eagle.programmar.Basic.Basic_Expression(this, AllowedPrecedence.ATLEAST);
		public Basic_Expression right = new Basic_Expression(this, AllowedPrecedence.ATLEAST);

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.ToString();

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, @operator, oper);
			}
			_metrics.operated(leftValue.getType(), rightValue.getType());

			if (leftValue.isDouble() || rightValue.isDouble())
			{
				double leftDouble = leftValue.forceDoubleValue();
				double rightDouble = rightValue.forceDoubleValue();
				interpreter.pushDouble(Math.Pow(leftDouble, rightDouble));
			}
			else
			{
				int leftInteger = leftValue.forceIntegerValue();
				int rightInteger = rightValue.forceIntegerValue();
				interpreter.pushInt((int) Math.Pow(leftInteger, rightInteger));
			}
		}
	}

}
