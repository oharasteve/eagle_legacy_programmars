// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.Go.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class Go_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public Go_KeywordChoice() : base()
		{
		}

		public Go_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
