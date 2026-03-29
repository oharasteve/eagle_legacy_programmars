// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Basic_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Basic_Keyword() : this("")
		{
		}

		public Basic_Keyword(string word) : base(word)
		{
		}
	}

}
