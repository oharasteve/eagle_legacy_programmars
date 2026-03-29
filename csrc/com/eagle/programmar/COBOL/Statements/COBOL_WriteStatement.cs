// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_WriteStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpswrit.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WRITE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WRITE");
		public @DOC("rlpswrit.htm") COBOL_Keyword WRITE = new COBOL_Keyword("WRITE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
		public COBOL_Identifier_Reference file;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_WriteFrom from;
		public @OPT COBOL_WriteFrom from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_WriteAfter after;
		public @OPT COBOL_WriteAfter after;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_WriteKey key;
		public @OPT COBOL_WriteKey key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword ENDWRITE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-WRITE");
		public @OPT COBOL_Keyword ENDWRITE = new COBOL_Keyword("END-WRITE");

		public static class COBOL_WriteFrom extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FROM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FROM");
			public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression fromWhat;
			public COBOL_Expression fromWhat;
		}

		public static class COBOL_WriteAfter extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice after = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("BEFORE", "AFTER");
			public COBOL_KeywordChoice after = new COBOL_KeywordChoice("BEFORE", "AFTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword ADVANCING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ADVANCING");
			public @OPT COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Expression lines;
			public @OPT COBOL_Expression lines;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_KeywordChoice what = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("PAGE", "LINE", "LINES");
			public @OPT COBOL_KeywordChoice what = new COBOL_KeywordChoice("PAGE", "LINE", "LINES");
		}

		public static class COBOL_WriteKey extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INVALID = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INVALID");
			public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
			public COBOL_Keyword KEY = new COBOL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> statements;
			public TokenList<COBOL_Statement> statements;
		}
	}

}
