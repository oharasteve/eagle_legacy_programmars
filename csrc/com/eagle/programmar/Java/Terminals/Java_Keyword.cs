// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

namespace com.eagle.programmar.Java.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Java_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Java_Keyword() : this("")
		{
		}

		public Java_Keyword(string word) : base(word)
		{
		}
	}

}
