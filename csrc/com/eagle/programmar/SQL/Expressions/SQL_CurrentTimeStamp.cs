// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Expressions
{
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class SQL_CurrentTimeStamp : PrimaryOperator
	{
		// For some reason, this sometimes has parens after it
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword TIMESTAMP = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CURRENT_TIMESTAMP");
		public SQL_Keyword TIMESTAMP = new SQL_Keyword("CURRENT_TIMESTAMP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_CurrentTimeStampFunction func;
		public  OPT;

		public class SQL_CurrentTimeStampFunction : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}
	}

}
