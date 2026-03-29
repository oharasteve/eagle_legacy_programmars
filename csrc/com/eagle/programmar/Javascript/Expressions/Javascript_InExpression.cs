// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Javascript_InExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Expression left = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword inOperator = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("in");
		public Javascript_Keyword inOperator = new Javascript_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Javascript_Expression right = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

}
