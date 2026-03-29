// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2014

namespace com.eagle.programmar.HTML.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class HTML_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public HTML_KeywordChoice() : base()
		{
		}

		public HTML_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
