// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Javascript_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Javascript_Punctuation() : this('\0')
		{
		}

		public Javascript_Punctuation(char punct) : base(punct)
		{
		}

		public Javascript_Punctuation(string punct) : base(punct)
		{
		}
	}

}
