// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class C_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public C_Punctuation() : this('\0')
		{
		}

		public C_Punctuation(char punct) : base(punct)
		{
		}

		public C_Punctuation(string punct) : base(punct)
		{
		}
	}

}
