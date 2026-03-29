// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Fortran_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Fortran_Keyword() : this("")
		{
		}

		public Fortran_Keyword(string word) : base(word)
		{
		}
	}

}
