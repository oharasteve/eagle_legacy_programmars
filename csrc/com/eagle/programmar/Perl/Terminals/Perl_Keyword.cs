// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Perl_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Perl_Keyword() : this("")
		{
		}

		public Perl_Keyword(string word) : base(word)
		{
		}
	}

}
