// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_LogicalOrExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Expression left = new com.eagle.programmar.Algol68.Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice orOperator = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("or", "xor");
		public Algol68_KeywordChoice orOperator = new Algol68_KeywordChoice("or", "xor");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Algol68_Expression right = new com.eagle.programmar.Algol68.Algol68_Expression(this, AllowedPrecedence.HIGHER);
		public Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			switch (orOperator.ToString())
			{
			case "or":
				if (leftValue)
				{
					interpreter.pushBool(true);
					return;
				}
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
				return;
			case "xor":
				bool rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(leftValue ^ rightVal);
				return;
			default:
				throw new Exception("Unexpected logical or operator: " + orOperator.ToString());
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			switch (orOperator.ToString())
			{
			case "or":
				return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.OR, rightExpr, this);
			case "xor":
				return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.XOR, rightExpr, this);
			default:
				throw new Exception("Unexpected logical or operator: " + orOperator.ToString());
			}
		}
	}

}
