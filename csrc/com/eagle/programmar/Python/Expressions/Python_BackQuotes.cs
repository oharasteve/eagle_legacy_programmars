// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using Python_BackQuote = com.eagle.programmar.Python.Terminals.Python_BackQuote;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;

	public class Python_BackQuotes : PrimaryOperator
	{
		// These are obsolete as of Python 3.
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @CURIOUS("Obsolete backquotes") com.eagle.tokens.TokenList<com.eagle.programmar.Python.Terminals.Python_BackQuote> quotes;
		public @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
	}

}
