// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

namespace com.eagle.programmar.Lisp.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Lisp_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Lisp_Keyword() : this("")
		{
		}

		public Lisp_Keyword(string word) : base(word)
		{
		}
	}

}
