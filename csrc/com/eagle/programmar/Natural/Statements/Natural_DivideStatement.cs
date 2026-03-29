// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_DivideStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/divide.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword DIVIDE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DIVIDE");
		public @DOC("sm/divide.htm") Natural_Keyword DIVIDE = new Natural_Keyword("DIVIDE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Variable denominator;
		public Natural_Variable denominator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword INTO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("INTO");
		public Natural_Keyword INTO = new Natural_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Natural_Variable numerator;
		public Natural_Variable numerator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword GIVING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("GIVING");
		public Natural_Keyword GIVING = new Natural_Keyword("GIVING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Natural.Natural_Variable quotient;
		public Natural_Variable quotient;
	}

}
