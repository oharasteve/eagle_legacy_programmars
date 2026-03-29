// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

namespace com.eagle.programmar.Java.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Java_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Java_Punctuation() : this('\0')
		{
		}

		public Java_Punctuation(char punct) : base(punct)
		{
		}

		public Java_Punctuation(string punct) : base(punct)
		{
		}
	}

}
