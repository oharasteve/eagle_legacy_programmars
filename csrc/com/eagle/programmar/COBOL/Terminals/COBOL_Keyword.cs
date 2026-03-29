// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class COBOL_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public COBOL_Keyword() : this("")
		{
		}

		public COBOL_Keyword(string word) : base(word)
		{
		}
	}

}
