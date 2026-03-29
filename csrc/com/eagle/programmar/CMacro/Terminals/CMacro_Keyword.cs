// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

namespace com.eagle.programmar.CMacro.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class CMacro_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public CMacro_Keyword() : this(null)
		{
		}

		public CMacro_Keyword(string word) : base(word)
		{
		}
	}

}
