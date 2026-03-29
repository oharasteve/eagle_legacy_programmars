// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Overflow = com.eagle.programmar.COBOL.COBOL_Overflow;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class COBOL_UnstringStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsunst.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword UNSTRING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("UNSTRING");
		public @DOC("rlpsunst.htm") COBOL_Keyword UNSTRING = new COBOL_Keyword("UNSTRING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
		public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_UnstringDelimited delimited;
		public @OPT COBOL_UnstringDelimited delimited;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INTO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INTO");
		public COBOL_Keyword INTO = new COBOL_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<COBOL_UnstringPiece> pieces;
		public TokenList<COBOL_UnstringPiece> pieces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_UnstringWith with;
		public @OPT COBOL_UnstringWith with;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT COBOL_Overflow overflow;
		public @OPT COBOL_Overflow overflow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_Keyword ENDUNSTRING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-UNSTRING");
		public @OPT COBOL_Keyword ENDUNSTRING = new COBOL_Keyword("END-UNSTRING");

		public static class COBOL_UnstringDelimited extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DELIMITED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DELIMITED");
			public COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
			public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
			public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) COBOL_UnstringOrWhat delim;
			public COBOL_UnstringOrWhat delim;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_UnstringOrClause> orClauses;
			public @OPT TokenList<COBOL_UnstringOrClause> orClauses;
		}

		public static class COBOL_UnstringOrClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OR");
			public COBOL_Keyword OR = new COBOL_Keyword("OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
			public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_UnstringOrWhat unstringWhat;
			public COBOL_UnstringOrWhat unstringWhat;
		}

		public static class COBOL_UnstringOrWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXdelim;
			public COBOL_Literal XXdelim;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXSPACES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SPACES");
			public COBOL_Keyword XXSPACES = new COBOL_Keyword("SPACES");
		}

		public static class COBOL_UnstringPiece extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference intoVar;
			public COBOL_Identifier_Reference intoVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_UnstringCount count;
			public @OPT COBOL_UnstringCount count;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_UnstringDelimiter delimiter;
			public @OPT COBOL_UnstringDelimiter delimiter;

			public static class COBOL_UnstringCount extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COUNT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COUNT");
				public COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IN");
				public COBOL_Keyword IN = new COBOL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference countVar;
				public COBOL_Identifier_Reference countVar;
			}

			public static class COBOL_UnstringDelimiter extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DELIMITER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DELIMITER");
				public COBOL_Keyword DELIMITER = new COBOL_Keyword("DELIMITER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IN");
				public COBOL_Keyword IN = new COBOL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression range;
				public COBOL_Expression range;
			}
		}

		public static class COBOL_UnstringWith extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword POINTER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("POINTER");
			public COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference withPointer;
			public COBOL_Identifier_Reference withPointer;
		}
	}

}
