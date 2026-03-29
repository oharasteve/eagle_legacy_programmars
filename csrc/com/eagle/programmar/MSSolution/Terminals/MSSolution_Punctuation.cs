// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class MSSolution_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public MSSolution_Punctuation() : this('\0')
		{
		}

		public MSSolution_Punctuation(char punct) : base(punct)
		{
		}

		public MSSolution_Punctuation(string punct) : base(punct)
		{
		}
	}

}
