// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Subscript = com.eagle.programmar.Natural.Natural_Subscript;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_SortStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/sort.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword SORT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SORT");
		public @DOC("sm/sort.htm") Natural_Keyword SORT = new Natural_Keyword("SORT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_KeywordChoice THEM_RECORDS = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("THEM", "RECORDS");
		public @OPT Natural_KeywordChoice THEM_RECORDS = new Natural_KeywordChoice("THEM", "RECORDS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword BY = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BY");
		public @OPT Natural_Keyword BY = new Natural_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Natural_SortBy> sortBy;
		public TokenList<Natural_SortBy> sortBy;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Natural_SortUsing using;
		public Natural_SortUsing @using;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Natural_Keyword ENDSORT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-SORT");
		public @OPT Natural_Keyword ENDSORT = new Natural_Keyword("END-SORT");

		public static class Natural_SortBy extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference id;
			public Natural_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_KeywordChoice ASCDESC = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("ASC", "DESC", "ASCENDING", "DESCENDING");
			public @OPT Natural_KeywordChoice ASCDESC = new Natural_KeywordChoice("ASC", "DESC", "ASCENDING", "DESCENDING");
		}

		public static class Natural_SortUsing extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword USING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("USING");
			public Natural_Keyword USING = new Natural_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword KEYS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("KEYS");
			public @OPT Natural_Keyword KEYS = new Natural_Keyword("KEYS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Natural_SortUsingVar> using;
			public @OPT TokenList<Natural_SortUsingVar> @using;

			public static class Natural_SortUsingVar extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference id;
				public Natural_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Subscript subscript;
				public @OPT Natural_Subscript subscript;
			}
		}
	}

}
