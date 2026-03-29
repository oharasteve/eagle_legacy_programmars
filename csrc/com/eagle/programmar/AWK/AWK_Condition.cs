// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{
	using AWK_KeywordChoice = com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class AWK_Condition : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_KeywordChoice XXBEGIN = new com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice("BEGIN", "END");
		public AWK_KeywordChoice XXBEGIN = new AWK_KeywordChoice("BEGIN", "END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST AWK_Expression XXexpr;
		public AWK_Expression XXexpr;
	}

}
