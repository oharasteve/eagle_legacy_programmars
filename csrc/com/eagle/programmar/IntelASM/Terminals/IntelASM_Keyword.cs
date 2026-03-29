// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class IntelASM_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public IntelASM_Keyword() : this("")
		{
		}

		public IntelASM_Keyword(string word) : base(word)
		{
		}
	}

}
