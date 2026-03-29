// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class Julia_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public Julia_KeywordChoice() : base()
		{
		}

		public Julia_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
