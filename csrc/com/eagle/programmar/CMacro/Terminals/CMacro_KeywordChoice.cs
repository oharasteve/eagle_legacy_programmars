// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

namespace com.eagle.programmar.CMacro.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class CMacro_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public CMacro_KeywordChoice() : base()
		{
		}

		public CMacro_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
