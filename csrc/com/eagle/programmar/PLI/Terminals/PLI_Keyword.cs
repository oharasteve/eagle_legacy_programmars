// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

namespace com.eagle.programmar.PLI.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class PLI_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public PLI_Keyword() : this("")
		{
		}

		public PLI_Keyword(string word) : base(word)
		{
		}
	}

}
