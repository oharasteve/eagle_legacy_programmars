// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
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

	public class Java_LogicalOrExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_PunctuationChoice orOperator = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice("||", "^");
		public Java_PunctuationChoice orOperator = new Java_PunctuationChoice("||", "^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression right = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			bool rightValue;
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
					rightValue = interpreter.getBoolValue(right);
					interpreter.pushBool(rightValue);
				}
				break;
			case "^":
				rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(leftValue ^ rightValue);
				break;
			default:
				throw new Exception("Unable to handle " + orOperator);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			EagleGenerator.LogicalOrEnum oper;
			switch (orOperator.getValue())
			{
			case "||":
				oper = EagleGenerator.LogicalOrEnum.OR;
				break;
			case "^":
				oper = EagleGenerator.LogicalOrEnum.XOR;
				break;
			default:
				throw new Exception("Unable to handle " + orOperator);
			}
			return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
		}

		public static Java_Expression generateLogicalOr(Java_Expression leftExpr, EagleGenerator.LogicalOrEnum oper, Java_Expression rightExpr, AbstractToken source)
		{
			Java_LogicalOrExpression orExpr = new Java_LogicalOrExpression();
			orExpr.left = leftExpr;
			orExpr.right = rightExpr;
			switch (oper)
			{
			case OR:
				orExpr.orOperator.setValue("||");
				break;
			case XOR:
				orExpr.orOperator.setValue("^");
				break;
			default:
				throw new Exception("Unable to handle " + oper);
			}
			orExpr.setTransformationSource(source);
			return Java_Generator.wrapExpression(orExpr);
		}
	}

}
