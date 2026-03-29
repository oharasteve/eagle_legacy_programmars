// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class HTML_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public HTML_Punctuation() : this('\0')
		{
		}

		public HTML_Punctuation(char punct) : base(punct)
		{
		}

		public HTML_Punctuation(string punct) : base(punct)
		{
		}
	}

}
