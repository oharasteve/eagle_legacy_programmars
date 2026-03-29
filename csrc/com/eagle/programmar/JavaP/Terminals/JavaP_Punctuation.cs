// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class JavaP_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public JavaP_Punctuation() : this('\0')
		{
		}

		public JavaP_Punctuation(char punct) : base(punct)
		{
		}

		public JavaP_Punctuation(string punct) : base(punct)
		{
		}
	}

}
