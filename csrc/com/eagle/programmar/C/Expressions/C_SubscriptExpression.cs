// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class C_SubscriptExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Expression expr = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression subscr = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ANY);
		public C_Expression subscr = new C_Expression(this, AllowedPrecedence.ANY);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
	}

}
