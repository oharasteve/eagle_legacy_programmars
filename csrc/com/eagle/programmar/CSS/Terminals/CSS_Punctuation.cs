// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.CSS.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class CSS_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public CSS_Punctuation() : this('\0')
		{
		}

		public CSS_Punctuation(char punct) : base(punct)
		{
		}

		public CSS_Punctuation(string punct) : base(punct)
		{
		}
	}

}
