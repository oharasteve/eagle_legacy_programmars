// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
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

	public class Rust_NegativeExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation operator = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("-");
		public Rust_Punctuation @operator = new Rust_Punctuation("-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression expr;
		public Rust_Expression expr;

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
			case "-":
				return generator.newNegativeExpression(EagleGenerator.NegativeEnum.NEGATIVE, theExpr, this);
			default:
				throw new Exception("Unexpected negation operator: " + @operator);
			}
		}

		public static Rust_Expression generateNegative(EagleGenerator.NegativeEnum sign, Rust_Expression theExpr, AbstractToken source)
		{
			Rust_NegativeExpression neg = new Rust_NegativeExpression();
			string oper;
			switch (sign)
			{
			case NEGATIVE:
				oper = "-";
				break;
			default:
				return null;
			}

			neg.expr = theExpr;
			neg.@operator.setValue(oper);
			neg.setTransformationSource(source);
			return Rust_Generator.wrapExpression(neg);
		}
	}

}
