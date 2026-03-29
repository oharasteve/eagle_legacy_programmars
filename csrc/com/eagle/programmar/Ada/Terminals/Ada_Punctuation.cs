// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Ada_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Ada_Punctuation() : this('\0')
		{
		}

		public Ada_Punctuation(char punct) : base(punct)
		{
		}

		public Ada_Punctuation(string punct) : base(punct)
		{
		}
	}

}
