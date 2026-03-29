// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

namespace com.eagle.programmar.PLI.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class PLI_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public PLI_Punctuation() : this('\0')
		{
		}

		public PLI_Punctuation(char punct) : base(punct)
		{
		}

		public PLI_Punctuation(string punct) : base(punct)
		{
		}
	}

}
