// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class SQL_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public SQL_Keyword() : this("")
		{
		}

		public SQL_Keyword(string word) : base(word)
		{
		}
	}

}
