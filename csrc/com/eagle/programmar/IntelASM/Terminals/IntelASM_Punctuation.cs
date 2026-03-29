// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class IntelASM_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public IntelASM_Punctuation() : this('\0')
		{
		}

		public IntelASM_Punctuation(char punct) : base(punct)
		{
		}

		public IntelASM_Punctuation(string punct) : base(punct)
		{
		}
	}

}
