// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL.Screen
{
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Picture = com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ScreenPicture : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice PIC = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("PIC", "PICTURE");
		public COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Picture picture;
		public COBOL_Picture picture;
	}
}
