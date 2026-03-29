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
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ReleaseStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rele.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RELEASE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RELEASE");
		public @DOC("rele.htm") COBOL_Keyword RELEASE = new COBOL_Keyword("RELEASE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id1;
		public COBOL_Identifier_Reference id1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_ReleaseFrom from;
		public @OPT COBOL_ReleaseFrom from;

		public static class COBOL_ReleaseFrom extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FROM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FROM");
			public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id2;
			public COBOL_Identifier_Reference id2;
		}
	}

}
