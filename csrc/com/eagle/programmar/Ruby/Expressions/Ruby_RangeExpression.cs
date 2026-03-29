// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Ruby.Expressions
{
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Punctuation = com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Ruby_RangeExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Expression left = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation dotDot = new com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation("..");
		public Ruby_Punctuation dotDot = new Ruby_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Ruby_Expression right = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}

}
