// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

namespace com.eagle.programmar.Lisp.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Lisp_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Lisp_Punctuation() : this('\0')
		{
		}

		public Lisp_Punctuation(char punct) : base(punct)
		{
		}

		public Lisp_Punctuation(string punct) : base(punct)
		{
		}
	}
}
