// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class RPGFree_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public RPGFree_Punctuation() : this('\0')
		{
		}

		public RPGFree_Punctuation(char punct) : base(punct)
		{
		}

		public RPGFree_Punctuation(string punct) : base(punct)
		{
		}
	}

}
