// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Terminals
{
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class AWK_KeywordChoice : TerminalKeywordChoice
	{
		// Need default constructor for reading from the XML file
		public AWK_KeywordChoice() : base()
		{
		}

		public AWK_KeywordChoice(params string[] words) : base(words)
		{
		}
	}

}
