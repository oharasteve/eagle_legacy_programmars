// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Label = com.eagle.programmar.Natural.Natural_Label;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_UpdateStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/update.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword UPDATE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("UPDATE");
		public @DOC("sm/update.htm") Natural_Keyword UPDATE = new Natural_Keyword("UPDATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Label label;
		public Natural_Label label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
	}

}
