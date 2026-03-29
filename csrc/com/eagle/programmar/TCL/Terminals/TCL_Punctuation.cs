// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class TCL_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public TCL_Punctuation() : this('\0')
		{
		}

		public TCL_Punctuation(char punct) : base(punct)
		{
		}

		public TCL_Punctuation(string punct) : base(punct)
		{
		}
	}
}
