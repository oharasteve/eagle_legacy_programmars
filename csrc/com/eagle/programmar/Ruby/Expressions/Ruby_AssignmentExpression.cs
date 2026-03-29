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
	using Ruby_PunctuationChoice = com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Ruby_AssignmentExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Expression var = new com.eagle.programmar.Ruby.Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public Ruby_Expression var = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice equals = new com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
		public Ruby_PunctuationChoice equals = new Ruby_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Ruby_Expression expr;
		public Ruby_Expression expr;
	}

}
