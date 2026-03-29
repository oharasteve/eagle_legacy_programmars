// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using NegativeEnum = com.eagle.transform.EagleGenerator.NegativeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_NegativeExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("+", "-");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Expression expr;
		public C_Expression expr;

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
				throw new Exception("Unexpected negation operator: " + @operator);
			}
		}
	}

}
