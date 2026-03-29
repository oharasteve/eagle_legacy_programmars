// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Rexx_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Rexx_Keyword() : this("")
		{
		}

		public Rexx_Keyword(string word) : base(word)
		{
		}
	}

}
