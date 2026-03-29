// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Basic_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Basic_Punctuation() : this('\0')
		{
		}

		public Basic_Punctuation(char punct) : base(punct)
		{
		}

		public Basic_Punctuation(string punct) : base(punct)
		{
		}
	}

}
