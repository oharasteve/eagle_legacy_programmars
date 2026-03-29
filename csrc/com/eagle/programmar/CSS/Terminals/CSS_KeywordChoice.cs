// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

namespace com.eagle.programmar.CSS.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class CSS_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public CSS_KeywordChoice() : base()
		{
		}

		public CSS_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
