// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Ada_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Ada_Keyword() : this("")
		{
		}

		public Ada_Keyword(string word) : base(word)
		{
		}
	}

}
