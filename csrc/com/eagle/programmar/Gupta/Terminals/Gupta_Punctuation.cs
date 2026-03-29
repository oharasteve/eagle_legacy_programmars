// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Gupta_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Gupta_Punctuation() : this('\0')
		{
		}

		public Gupta_Punctuation(char punct) : base(punct)
		{
		}

		public Gupta_Punctuation(string punct) : base(punct)
		{
		}
	}

}
