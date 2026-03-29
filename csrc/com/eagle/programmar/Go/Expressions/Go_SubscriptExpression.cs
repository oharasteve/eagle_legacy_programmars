// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Go.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Go_SubscriptExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Go_Expression expr = new com.eagle.programmar.Go.Go_Expression(this, AllowedPrecedence.HIGHER);
		public Go_Expression expr = new Go_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Go_Expression subscr1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationColon colon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Go_Expression subscr2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public override void interpret(EagleInterpreter interpreter)
		{
			string val = interpreter.getStrValue(expr);
			int sc = interpreter.getIntValue(subscr1);
			int ec = interpreter.getIntValue(subscr2);
			interpreter.pushStr(val.Substring(sc, ec - sc));
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (colon != null && colon.isPresent())
			{
				AbstractExpression theExpr = transformer.transformExpression(generator, expr);
				AbstractExpression scExpr = transformer.transformExpression(generator, subscr1);
				AbstractExpression ecExpr = transformer.transformExpression(generator, subscr2);
				return generator.newSubstringFunction(theExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_EC_PLUS_ONE, ecExpr, false, this);
			}

			if (expr.getWhich() is Go_VariableExpression)
			{
				Go_VariableExpression varExpr = (Go_VariableExpression) expr.getWhich();
				string varName = varExpr.variable.vars.first().getValue();
				AbstractExpression subExpr = transformer.transformExpression(generator, subscr1);
				return generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
			}

			throw new Exception("Unable to handle subscript");
		}
	}

}
