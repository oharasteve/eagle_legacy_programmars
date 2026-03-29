// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class AWK_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public AWK_Punctuation() : this('\0')
		{
		}

		public AWK_Punctuation(char punct) : base(punct)
		{
		}

		public AWK_Punctuation(string punct) : base(punct)
		{
		}
	}

}
