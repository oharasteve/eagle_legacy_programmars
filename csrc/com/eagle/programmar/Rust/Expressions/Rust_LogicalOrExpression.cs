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
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_LogicalOrExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression left = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice orOperator = new com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice("||", "^");
		public Rust_PunctuationChoice orOperator = new Rust_PunctuationChoice("||", "^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Expression right = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			switch (orOperator.ToString())
			{
			case "||":
				if (leftValue)
				{
					// Short circuit, don't bother with RHS
					interpreter.pushBool(true);
				}
				else
				{
					bool rightValue = interpreter.getBoolValue(right);
					interpreter.pushBool(rightValue);
				}
				break;
			case "^":
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(leftValue ^ rightValue);
				break;
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.OR, rightExpr, this);
		}

		public static Rust_Expression generateLogicalOr(Rust_Expression leftExpr, EagleGenerator.LogicalOrEnum oper, Rust_Expression rightExpr, AbstractToken source)
		{
			Rust_LogicalOrExpression or = new Rust_LogicalOrExpression();
			or.left = leftExpr;
			or.right = rightExpr;
			switch (oper)
			{
			case OR:
				or.orOperator.setValue("||");
				break;
			case XOR:
				or.orOperator.setValue("^");
				break;
			default:
				throw new Exception("Unable to handle " + oper);
			}
			or.setTransformationSource(source);
			return Rust_Generator.wrapExpression(or);
		}
	}

}
