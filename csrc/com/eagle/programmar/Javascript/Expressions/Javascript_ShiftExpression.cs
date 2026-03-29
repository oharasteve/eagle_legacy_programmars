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
	using Javascript_PunctuationChoice = com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Javascript_ShiftExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Expression left = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice operator = new com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice(">>>", "<<", ">>");
		public Javascript_PunctuationChoice @operator = new Javascript_PunctuationChoice(">>>", "<<", ">>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Javascript_Expression right = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

}
