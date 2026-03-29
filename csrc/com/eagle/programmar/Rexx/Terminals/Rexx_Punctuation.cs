// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Rexx_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Rexx_Punctuation() : this('\0')
		{
		}

		public Rexx_Punctuation(char punct) : base(punct)
		{
		}

		public Rexx_Punctuation(string punct) : base(punct)
		{
		}
	}

}
