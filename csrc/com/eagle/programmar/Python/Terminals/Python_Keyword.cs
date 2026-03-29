// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Python_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Python_Keyword() : this("")
		{
		}

		public Python_Keyword(string word) : base(word)
		{
		}
	}

}
