// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Algol68_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Algol68_Punctuation() : this('\0')
		{
		}

		public Algol68_Punctuation(char punct) : base(punct)
		{
		}

		public Algol68_Punctuation(string punct) : base(punct)
		{
		}
	}

}
