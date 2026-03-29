// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.Go.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Go_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Go_Punctuation() : this('\0')
		{
		}

		public Go_Punctuation(char punct) : base(punct)
		{
		}

		public Go_Punctuation(string punct) : base(punct)
		{
		}
	}

}
