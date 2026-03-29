// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Ruby.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_PunctuationChoice = com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ruby_EqualityExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Expression left = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice operator = new com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice("==", "!=");
		public Ruby_PunctuationChoice @operator = new Ruby_PunctuationChoice("==", "!=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Ruby_Expression right = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);

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
				}
			}

			if (leftValue.isDouble() || rightValue.isDouble())
			{
				double leftDbl = leftValue.forceDoubleValue();
				double rightDbl = rightValue.forceDoubleValue();
				switch (oper)
				{
				case "==":
					interpreter.pushBool(leftDbl == rightDbl);
					return;
				case "!=":
					interpreter.pushBool(leftDbl != rightDbl);
					return;
				}
			}

			if (leftValue.isInteger() || rightValue.isInteger())
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper)
				{
				case "==":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case "!=":
					interpreter.pushBool(leftInt != rightInt);
					return;
				}
			}

			if (leftValue.isBoolean() && rightValue.isBoolean())
			{
				bool leftBool = leftValue.forceBooleanValue();
				bool rightBool = rightValue.forceBooleanValue();
				switch (oper)
				{
				case "==":
					interpreter.pushBool(leftBool == rightBool);
					return;
				case "!=":
					interpreter.pushBool(leftBool != rightBool);
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
			case "==":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "!=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + @operator);
			}
		}
	}

}
