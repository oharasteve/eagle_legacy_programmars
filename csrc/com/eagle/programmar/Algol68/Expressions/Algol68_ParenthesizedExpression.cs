// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_ParenthesizedExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Algol68.Algol68_Expression, com.eagle.tokens.punctuation.PunctuationComma> expressions;
		public SeparatedList<Algol68_Expression, PunctuationComma> expressions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			int numArgs = expressions.getPrimaryCount();
			if (numArgs == 1)
			{
				Algol68_Expression expr = expressions.first();
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.pushEagleValue(val);
			}
			else
			{
				EagleArray array = new EagleArray();
				for (int i = 0; i < numArgs; i++)
				{
					EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
					array.addValue(val);
				}
				interpreter.pushEagleValue(array);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			int numArgs = expressions.getPrimaryCount();
			if (numArgs == 1)
			{
				// Just regular parens
				AbstractExpression theExpr = transformer.transformExpression(generator, expressions.first());
				return generator.newParenthesizedExpression(theExpr, this);
			}

			// Must be a []STRING array
			List<AbstractExpression> exprs = new List<AbstractExpression>();
			for (int i = 0; i < numArgs; i++)
			{
				Algol68_Expression expr = expressions.getPrimaryElement(i);
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				exprs.Add(newExpr);
			}
			return generator.newArrayExpression(exprs, this);
		}
	}
}
