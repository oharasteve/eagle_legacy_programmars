// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

namespace com.eagle.programmar.CMD.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class CMD_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public CMD_KeywordChoice() : base()
		{
		}

		public CMD_KeywordChoice(params string[] words) : base(words)
		{
		}

	}

}
