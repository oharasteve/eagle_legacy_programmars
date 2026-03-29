// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class MSSolution_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public MSSolution_Keyword() : this("")
		{
		}

		public MSSolution_Keyword(string word) : base(word)
		{
		}
	}

}
