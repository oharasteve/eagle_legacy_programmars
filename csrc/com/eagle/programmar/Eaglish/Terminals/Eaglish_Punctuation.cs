// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

namespace com.eagle.programmar.Eaglish.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Eaglish_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Eaglish_Punctuation() : this('\0')
		{
		}

		public Eaglish_Punctuation(char punct) : base(punct)
		{
		}

		public Eaglish_Punctuation(string punct) : base(punct)
		{
		}
	}

}
