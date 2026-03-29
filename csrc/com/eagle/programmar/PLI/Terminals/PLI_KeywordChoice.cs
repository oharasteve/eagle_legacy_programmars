// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.PLI.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class PLI_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public PLI_KeywordChoice() : base()
		{
		}

		public PLI_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
