// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Perl_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Perl_Punctuation() : this('\0')
		{
		}

		public Perl_Punctuation(char punct) : base(punct)
		{
		}

		public Perl_Punctuation(string punct) : base(punct)
		{
		}
	}

}
