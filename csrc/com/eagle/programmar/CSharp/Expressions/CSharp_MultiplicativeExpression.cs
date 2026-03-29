// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Type = com.eagle.programmar.CSharp.CSharp_Type;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
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

	public class CSharp_MultiplicativeExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression left = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("operators/arithmetic-operators") com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice operator = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("*", "/", "%");
		public @DOC("operators/arithmetic-operators") CSharp_PunctuationChoice @operator = new CSharp_PunctuationChoice("*", "/", "%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.CSharp_Expression right = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

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
			throw new Exception("Unexpected multiplicative operator: " + oper);
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

		public static CSharp_Expression generateMultiplicative(CSharp_Expression leftExpr, EagleGenerator.MultiplicativeEnum oper, CSharp_Expression rightExpr, AbstractToken source)
		{
			CSharp_MultiplicativeExpression multExpr = new CSharp_MultiplicativeExpression();
			multExpr.left = leftExpr;
			multExpr.right = rightExpr;
			switch (oper)
			{
			case TIMES:
				multExpr.@operator.setValue("*");
				break;
			case DIVIDE_TRUNCATE:
				multExpr.@operator.setValue("/");
				break;
			case DIVIDE_NO_TRUNCATE:
				multExpr.@operator.setValue("/");
				CSharp_Type type = CSharp_Type.newPrimitiveType("double");
				multExpr.right = CSharp_CastExpression.newCastExpression(type, rightExpr, source);
				break;
			case REMAINDER:
				multExpr.@operator.setValue("%");
				break;
			default:
				throw new Exception("Unable to handle: " + oper);
			}
			multExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(multExpr);
		}
	}

}
