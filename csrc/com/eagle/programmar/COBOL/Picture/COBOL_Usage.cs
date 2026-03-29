// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL.Picture
{
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_Usage : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword USAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("USAGE");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice type = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("BINARY", "COMP", "COMP-0", "COMP-1", "COMP-3", "COMP-5", "COMP-X", "COMPUTATIONAL", "DISPLAY", "PACKED-DECIMAL");
		public COBOL_KeywordChoice type = new COBOL_KeywordChoice("BINARY", "COMP", "COMP-0", "COMP-1", "COMP-3", "COMP-5", "COMP-X", "COMPUTATIONAL", "DISPLAY", "PACKED-DECIMAL");
	}
}
