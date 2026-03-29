// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class Basic_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public Basic_KeywordChoice() : base()
		{
		}

		public Basic_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
