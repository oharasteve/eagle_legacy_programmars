// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Punctuation = com.eagle.programmar.Java.Terminals.Java_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_LogicalAndExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_Punctuation andOperator = new com.eagle.programmar.Java.Terminals.Java_Punctuation("&&");
		public Java_Punctuation andOperator = new Java_Punctuation("&&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression right = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			if (leftValue)
			{
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			else
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(false);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
		}

		public static Java_Expression generateLogicalAnd(Java_Expression leftExpr, Java_Expression rightExpr, AbstractToken source)
		{
			Java_LogicalAndExpression andExpr = new Java_LogicalAndExpression();
			andExpr.left = leftExpr;
			andExpr.right = rightExpr;
			andExpr.setTransformationSource(source);
			return Java_Generator.wrapExpression(andExpr);
		}
	}

}
