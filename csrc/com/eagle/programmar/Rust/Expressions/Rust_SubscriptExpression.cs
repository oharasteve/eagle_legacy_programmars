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
	using EagleArray = com.eagle.math.EagleArray;
	using EagleRange = com.eagle.math.EagleRange;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	/* Ran this in rextester.com
	 * 
	 *  fn main() {
	 *    let s = "abcdèfghij";
	 *    println!("{}", &s[0..3]);
	 *    println!("{}", &s[1..3]);
	 *    println!("{}", &s[1..9]);
	 *    println!("{}", &s[2..]);
	 *    println!("{}", &s[..4]);
	 *  }
	
	 * abc
	 * bc
	 * bcdèfgh
	 * cdèfghij
	 * abcd
	 * 
	 * first is zero, second is ec+1, cannot do &s[1..9999]
	*/

	public class Rust_SubscriptExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression expr = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftBracket leftBracket;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Rust_Expression subscrExpr = new com.eagle.programmar.Rust.Rust_Expression();
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
		public  NOSPACE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expr);
			int lowValue;
			int highValue = int.MaxValue;

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, leftBracket, leftBracket.ToString());
			}
			_metrics.operated(value.getType(), EagleGenerator.TypeEnum.INTEGER);

			EagleValue low = interpreter.getEagleValue(subscrExpr);
			if (low.isRange())
			{
				EagleRange range = (EagleRange) low;
				lowValue = range._lowValue;
				if (range._hasHigh)
				{
					highValue = range._highValue;
				}
			}
			else
			{
				lowValue = low.forceIntegerValue();
			}

			if (value.isArray())
			{
				EagleArray array = (EagleArray) value;
				EagleValue val = array.getValue(lowValue);
				interpreter.pushEagleValue(val);
			}
			else if (value.isString())
			{
				string str = value.forceStringValue();
				if (highValue > str.Length)
				{
					highValue = str.Length;
				}
				string substr = str.Substring(lowValue, highValue - lowValue);
				interpreter.pushStr(substr);
			}
			else
			{
				throw new Exception("Unable to handle " + value.ToString());
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(leftBracket);

			if (types._type1 == EagleGenerator.TypeEnum.STRING)
			{
				if (subscrExpr.getWhich() is Rust_RangeExpression)
				{
					// string with a range subscript
					Rust_RangeExpression range = (Rust_RangeExpression) subscrExpr.getWhich();
					AbstractExpression newSc = transformer.transformExpression(generator, range.lowExpression);
					AbstractExpression newEc = null;
					if (range.highExpression != null && range.highExpression.isPresent())
					{
						newEc = transformer.transformExpression(generator, range.highExpression);
					}
					return generator.newSubstringFunction(newExpr, newSc, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_EC_PLUS_ONE, newEc, false, this);
				}

				// string with a single subscript, not a range
				AbstractExpression newSubscr = transformer.transformExpression(generator, subscrExpr);
				AbstractExpression one = generator.newNumberExpression("1", null);
				return generator.newSubstringFunction(newExpr, newSubscr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_NC, one, false, this);
			}

			// Assume it must be an array
			if (expr.getWhich() is Rust_VariableExpression)
			{
				Rust_VariableExpression varExpr = (Rust_VariableExpression) expr.getWhich();
				AbstractExpression newSubscr = transformer.transformExpression(generator, subscrExpr);
				return generator.newVariableExpression(varExpr.variable.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, newSubscr, expr);
			}

			throw new Exception("Unable to handle " + expr);
		}

		public static Rust_Expression generateSubscriptExpression(AbstractExpression theExpr, AbstractExpression sc, EagleGenerator.SubstringSCEnum whichSC, EagleGenerator.SubstringECEnum whichEC, AbstractExpression ecOrnc, bool ncMightBeTooBig, AbstractToken source)
		{
			Rust_SubscriptExpression subscr = new Rust_SubscriptExpression();
			subscr.expr = Rust_BorrowExpression.generateBorrow((Rust_Expression) theExpr, source);
			subscr.leftBracket = new PunctuationLeftBracket();
			subscr.rightBracket = new PunctuationRightBracket();

			Rust_RangeExpression range = Rust_RangeExpression.generateSubscript(sc, whichSC, whichEC, ecOrnc, ncMightBeTooBig, source);
			subscr.subscrExpr = Rust_Generator.wrapExpression(range);

			subscr.setTransformationSource(source);
			return Rust_Generator.wrapExpression(subscr);
		}
	}

}
