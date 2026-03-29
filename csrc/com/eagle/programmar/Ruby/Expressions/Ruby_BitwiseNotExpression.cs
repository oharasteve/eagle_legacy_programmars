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
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Ruby_BitwiseNotExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation logicalNotOperator = new com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation('~');
		public Ruby_Punctuation logicalNotOperator = new Ruby_Punctuation('~');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Ruby_Expression expr;
		public Ruby_Expression expr;
	}

}
