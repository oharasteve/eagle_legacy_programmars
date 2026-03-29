// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Ruby_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Ruby_Punctuation() : this('\0')
		{
		}

		public Ruby_Punctuation(char punct) : base(punct)
		{
		}

		public Ruby_Punctuation(string punct) : base(punct)
		{
		}
	}

}
