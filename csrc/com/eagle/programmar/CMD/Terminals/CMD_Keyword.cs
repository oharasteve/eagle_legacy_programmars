// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

namespace com.eagle.programmar.CMD.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class CMD_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public CMD_Keyword() : this("")
		{
		}

		public CMD_Keyword(string word) : base(word)
		{
		}
	}

}
