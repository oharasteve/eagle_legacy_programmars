// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class COBOL_SortStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpssort.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SORT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SORT");
		public @DOC("rlpssort.htm") COBOL_Keyword SORT = new COBOL_Keyword("SORT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
		public COBOL_Identifier_Reference file;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
		public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice ASCENDING = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ASCENDING", "DESCENDING");
		public COBOL_KeywordChoice ASCENDING = new COBOL_KeywordChoice("ASCENDING", "DESCENDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
		public @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference key;
		public COBOL_Identifier_Reference key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<COBOL_SortKey> moreKeys;
		public @OPT TokenList<COBOL_SortKey> moreKeys;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_SortInput input;
		public @OPT COBOL_SortInput input;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT COBOL_SortUsing using;
		public @OPT COBOL_SortUsing @using;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT COBOL_SortOutput output;
		public @OPT COBOL_SortOutput output;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT COBOL_SortGiving giving;
		public @OPT COBOL_SortGiving giving;

		public static class COBOL_SortKey extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference key;
			public COBOL_Identifier_Reference key;
		}

		public static class COBOL_SortInput extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INPUT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INPUT");
			public COBOL_Keyword INPUT = new COBOL_Keyword("INPUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PROCEDURE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PROCEDURE");
			public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference inProc;
			public COBOL_Identifier_Reference inProc;
		}

		public static class COBOL_SortOutput extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OUTPUT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OUTPUT");
			public COBOL_Keyword OUTPUT = new COBOL_Keyword("OUTPUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PROCEDURE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PROCEDURE");
			public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference outProc;
			public COBOL_Identifier_Reference outProc;
		}

		public static class COBOL_SortUsing extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword USING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("USING");
			public COBOL_Keyword USING = new COBOL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
			public COBOL_Identifier_Reference file;
		}

		public static class COBOL_SortGiving extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GIVING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GIVING");
			public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
			public COBOL_Identifier_Reference file;
		}
	}

}
