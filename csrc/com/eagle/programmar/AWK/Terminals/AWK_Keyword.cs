// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class AWK_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public AWK_Keyword() : this(null)
		{
		}

		public AWK_Keyword(string word) : base(word)
		{
		}
	}

}
