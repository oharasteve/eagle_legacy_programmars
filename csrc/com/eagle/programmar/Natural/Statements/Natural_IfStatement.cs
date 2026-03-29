// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 9, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Condition = com.eagle.programmar.Natural.Natural_Condition;
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_IfStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/if.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword IF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("IF");
		public @DOC("sm/if.htm") Natural_Keyword IF = new Natural_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_IfWhat ifWhat;
		public Natural_IfWhat ifWhat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_ElseClause elseClause;
		public @OPT Natural_ElseClause elseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice endIf = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("END-IF", "END-NOREC");
		public Natural_KeywordChoice endIf = new Natural_KeywordChoice("END-IF", "END-NOREC");

		public static class Natural_IfWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_NoRecordsFound XXnoRecordsFound;
			public Natural_NoRecordsFound XXnoRecordsFound;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Condition XXcondition;
			public Natural_Condition XXcondition;
		}

		public static class Natural_NoRecordsFound extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword NO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NO");
			public Natural_Keyword NO = new Natural_Keyword("NO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword RECORDS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("RECORDS");
			public Natural_Keyword RECORDS = new Natural_Keyword("RECORDS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword FOUND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FOUND");
			public Natural_Keyword FOUND = new Natural_Keyword("FOUND");
		}

		public static class Natural_ElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword ELSE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ELSE");
			public Natural_Keyword ELSE = new Natural_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
			public TokenList<Natural_Statement> statements;
		}
	}

}
