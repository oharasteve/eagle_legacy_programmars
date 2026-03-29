// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Natural_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Natural_Punctuation() : this('\0')
		{
		}

		public Natural_Punctuation(char punct) : base(punct)
		{
		}

		public Natural_Punctuation(string punct) : base(punct)
		{
		}
	}
}
