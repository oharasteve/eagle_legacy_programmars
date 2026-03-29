// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Powershell_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Powershell_Keyword() : this("")
		{
		}

		public Powershell_Keyword(string word) : base(word)
		{
		}
	}

}
