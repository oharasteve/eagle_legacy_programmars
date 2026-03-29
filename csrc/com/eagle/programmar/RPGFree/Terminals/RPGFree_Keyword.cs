// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class RPGFree_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public RPGFree_Keyword() : this("")
		{
		}

		public RPGFree_Keyword(string word) : base(word)
		{
		}
	}

}
