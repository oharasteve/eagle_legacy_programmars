// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Python_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Python_Punctuation() : this('\0')
		{
		}

		public Python_Punctuation(char punct) : base(punct)
		{
		}

		public Python_Punctuation(string punct) : base(punct)
		{
		}
	}

}
