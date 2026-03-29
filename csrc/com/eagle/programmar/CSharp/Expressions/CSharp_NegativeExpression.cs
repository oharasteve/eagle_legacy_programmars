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
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using NegativeEnum = com.eagle.transform.EagleGenerator.NegativeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_NegativeExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice operator = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("-", "+");
		public CSharp_PunctuationChoice @operator = new CSharp_PunctuationChoice("-", "+");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE CSharp_Expression expr;
		public  NOSPACE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator1Metrics _metrics = null;
		private Operator1Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expr);
			string oper = @operator.getValue();

			if (_metrics == null)
			{
				_metrics = new Operator1Metrics(interpreter._metrics, @operator, oper);
			}
			_metrics.operated(value.getType());

			int val = value.forceIntegerValue();
			switch (oper)
			{
			case "+":
				interpreter.pushInt(val);
				break;
			case "-":
				interpreter.pushInt(-val);
				break;
			default:
				throw new Exception("Unexpected negation operator: " + oper);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			switch (@operator.ToString())
			{
			case "+":
				return theExpr;
			case "-":
				return generator.newNegativeExpression(EagleGenerator.NegativeEnum.NEGATIVE, theExpr, this);
			default:
				throw new Exception("Unexpected negative operator: " + @operator);
			}
		}

		public static CSharp_Expression generateNegative(EagleGenerator.NegativeEnum sign, CSharp_Expression theExpr, AbstractToken source)
		{
			CSharp_NegativeExpression negExpr = new CSharp_NegativeExpression();
			negExpr.expr = theExpr;
			switch (sign)
			{
			case POSITIVE:
				negExpr.@operator.setValue("+");
				break;
			case NEGATIVE:
				negExpr.@operator.setValue("-");
				break;
			}
			negExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(negExpr);
		}
	}

}
