// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

namespace com.eagle.programmar.CMacro.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class CMacro_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public CMacro_Punctuation() : this('\0')
		{
		}

		public CMacro_Punctuation(char punct) : base(punct)
		{
		}

		public CMacro_Punctuation(string punct) : base(punct)
		{
		}
	}

}
