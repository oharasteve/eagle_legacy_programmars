// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_RewriteStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsrewr.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REWRITE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REWRITE");
		public @DOC("rlpsrewr.htm") COBOL_Keyword REWRITE = new COBOL_Keyword("REWRITE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
		public COBOL_Identifier_Reference file;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_RewriteKey key;
		public @OPT COBOL_RewriteKey key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword ENDREWRITE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-REWRITE");
		public @OPT COBOL_Keyword ENDREWRITE = new COBOL_Keyword("END-REWRITE");

		public static class COBOL_RewriteKey extends TokenSequence
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
