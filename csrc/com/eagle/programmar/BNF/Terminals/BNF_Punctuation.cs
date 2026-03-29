// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class BNF_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public BNF_Punctuation() : this('\0')
		{
		}

		public BNF_Punctuation(char punct) : base(punct)
		{
		}

		public BNF_Punctuation(string punct) : base(punct)
		{
		}
	}

}
