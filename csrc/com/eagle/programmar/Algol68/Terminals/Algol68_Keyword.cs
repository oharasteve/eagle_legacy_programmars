// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Algol68_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Algol68_Keyword() : this("")
		{
		}

		public Algol68_Keyword(string word) : base(word)
		{
		}
	}

}
