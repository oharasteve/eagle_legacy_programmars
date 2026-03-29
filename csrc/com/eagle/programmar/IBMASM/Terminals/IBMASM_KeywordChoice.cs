// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.IBMASM.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class IBMASM_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public IBMASM_KeywordChoice() : base()
		{
		}

		public IBMASM_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
