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
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
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

	public class Ruby_SubscriptExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Expression expr = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Ruby_Expression subscript;
		public Ruby_Expression subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator1Metrics _metrics = null;
		private Operator1Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expr);

			if (_metrics == null)
			{
				_metrics = new Operator1Metrics(interpreter._metrics, this, leftBracket.getValue());
			}
			_metrics.operated(value.getType());

			if (value.isArray())
			{
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(subscript);
				EagleValue val = array.getValue(sub);
				interpreter.pushEagleValue(val);
			}
			else if (value.isString() && subscript.getWhich() is Ruby_RangeExpression)
			{
				string str = value.forceStringValue();
				Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.getWhich();
				int len = str.Length;
				int sc = interpreter.getIntValue(range.left);
				int ec = interpreter.getIntValue(range.right) + 1;
				if (ec > len)
				{
					ec = len;
				}
				interpreter.pushStr(str.Substring(sc, ec - sc));
			}
			else
			{
				throw new Exception("Unable to handle subscript");
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Operator1Metrics.Oper1Types type = transformer.findOperator1Metric(this);
			if (type != null && type._type1 == EagleGenerator.TypeEnum.STRING && subscript.getWhich() is Ruby_RangeExpression)
			{
				Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.getWhich();
				AbstractExpression theExpr = transformer.transformExpression(generator, expr);
				AbstractExpression scExpr = transformer.transformExpression(generator, range.left);
				AbstractExpression ecExpr = transformer.transformExpression(generator, range.right);
				return generator.newSubstringFunction(theExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_EC, ecExpr, false, this);
			}

			if (expr.getWhich() is Ruby_VariableExpression)
			{
				Ruby_VariableExpression varExpr = (Ruby_VariableExpression) expr.getWhich();
				string varName = varExpr.variable.vars.first().getValue();
				AbstractExpression subExpr = transformer.transformExpression(generator, subscript);
				return generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
			}

			throw new Exception("Unable to handle subscript");
		}
	}

}
