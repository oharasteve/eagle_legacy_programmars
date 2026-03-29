// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using MultiplicativeEnum = com.eagle.transform.EagleGenerator.MultiplicativeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_MultiplicativeExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_PunctuationChoice operator = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice("*", "/", "%");
		public Java_PunctuationChoice @operator = new Java_PunctuationChoice("*", "/", "%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression right = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

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
				double leftDbl = leftValue.forceDoubleValue();
				double rightDbl = rightValue.forceDoubleValue();
				switch (oper)
				{
				case "*":
					interpreter.pushDouble(leftDbl * rightDbl);
					return;
				case "/":
					interpreter.pushDouble(leftDbl / rightDbl);
					return;
				case "%":
					interpreter.pushDouble(leftDbl % rightDbl);
					return;
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper)
				{
				case "*":
					interpreter.pushInt(leftInt * rightInt);
					return;
				case "/":
					interpreter.pushInt(leftInt / rightInt);
					return;
				case "%":
					interpreter.pushInt(leftInt % rightInt);
					return;
				}
			}
			throw new Exception("Unexpected multiplicative operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			switch (@operator.ToString())
			{
			case "*":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.TIMES, rightExpr, this);
			case "/":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
			case "%":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.REMAINDER, rightExpr, this);
			default:
				throw new Exception("Unexpected multiplicative operator: " + @operator);
			}
		}

		public static Java_Expression generateMultiplicative(Java_Expression leftExpr, EagleGenerator.MultiplicativeEnum oper, Java_Expression rightExpr, AbstractToken source)
		{
			Java_MultiplicativeExpression mulExp = new Java_MultiplicativeExpression();
			mulExp.left = leftExpr;
			mulExp.right = rightExpr;
			switch (oper)
			{
			case TIMES:
				mulExp.@operator.setValue("*");
				break;
			case DIVIDE_TRUNCATE:
				mulExp.@operator.setValue("/");
				break;
			case DIVIDE_NO_TRUNCATE:
				mulExp.@operator.setValue("/");
				Java_Type type = Java_Type.newPrimitiveType("double");
				mulExp.right = Java_CastExpression.newCastExpression(type, rightExpr, source);
				break;
			case REMAINDER:
				mulExp.@operator.setValue("%");
				break;
			default:
				throw new Exception("Unable to handle: " + oper.ToString());
			}
			mulExp.setTransformationSource(source);
			return Java_Generator.wrapExpression(mulExp);
		}
	}

}
