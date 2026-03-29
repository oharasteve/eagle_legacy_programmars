// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

namespace com.eagle.programmar.TCL.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class TCL_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public TCL_KeywordChoice() : base()
		{
		}

		public TCL_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
