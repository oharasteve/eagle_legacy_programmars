// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Django.Expressions
{
	using Django_Expression = com.eagle.programmar.Django.Django_Expression;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Django_AndExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Django_Expression left = new com.eagle.programmar.Django.Django_Expression(this, AllowedPrecedence.ATLEAST);
		public Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Terminals.Django_Keyword OR = new com.eagle.programmar.Django.Terminals.Django_Keyword("or");
		public Django_Keyword OR = new Django_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Django.Django_Expression right = new com.eagle.programmar.Django.Django_Expression(this, AllowedPrecedence.HIGHER);
		public Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}

}
