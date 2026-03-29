// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 15, 2024

namespace com.eagle.programmar.Eaglish.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Eaglish_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Eaglish_Keyword() : this("")
		{
		}

		public Eaglish_Keyword(string word) : base(word)
		{
		}
	}

}
