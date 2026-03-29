// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL.Screen
{
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Punctuation = com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ScreenLine : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LINE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINE");
		public COBOL_Keyword LINE = new COBOL_Keyword("LINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Punctuation plus = new com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation('+');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_NumberOrIdentifier number;
		public COBOL_NumberOrIdentifier number;
	}
}
