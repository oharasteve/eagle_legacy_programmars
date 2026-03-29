// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class SQL_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public SQL_Punctuation() : this('\0')
		{
		}

		public SQL_Punctuation(char punct) : base(punct)
		{
		}

		public SQL_Punctuation(string punct) : base(punct)
		{
		}
	}

}
