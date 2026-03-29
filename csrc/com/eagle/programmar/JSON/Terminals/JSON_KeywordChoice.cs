// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2015

namespace com.eagle.programmar.JSON.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class JSON_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public JSON_KeywordChoice() : base()
		{
		}

		public JSON_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
