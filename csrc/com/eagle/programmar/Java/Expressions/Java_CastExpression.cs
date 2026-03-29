// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_CastExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE SeparatedList<com.eagle.programmar.Java.Java_Type, com.eagle.tokens.punctuation.PunctuationAmpersand> types;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Java.Java_Expression expr;
		public Java_Expression expr;

		public static Java_Expression newCastExpression(Java_Type type, Java_Expression expr, AbstractToken source)
		{
			Java_CastExpression cast = new Java_CastExpression();
			cast.leftParen = new PunctuationLeftParen();
			cast.types = new SeparatedList<Java_Type, PunctuationAmpersand>();
			cast.types.addPrimaryElement(type);
			cast.rightParen = new PunctuationRightParen();

			if (expr.getWhich() is Java_ParenthesizedExpression)
			{
				cast.expr = expr;
			}
			else
			{
				cast.expr = Java_ParenthesizedExpression.generateParentheses(expr, expr);
			}

			cast.setTransformationSource(source);
			return Java_Generator.wrapExpression(cast);
		}
	}

}
