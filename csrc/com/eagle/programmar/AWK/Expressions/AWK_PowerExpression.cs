// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 4, 2026

namespace com.eagle.programmar.AWK.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_PunctuationChoice = com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_PowerExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Expression left = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.HIGHER);
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice stars = new com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice("**", "^");
		public AWK_PunctuationChoice stars = new AWK_PunctuationChoice("**", "^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression right = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			interpreter.pushInt((int) (long)Math.Round(Math.Pow(leftValue, rightValue), MidpointRounding.AwayFromZero));
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newExponentExpression(leftExpr, rightExpr, this);
		}
	}
}
