// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

namespace com.eagle.programmar.AWK.Statements
{
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class AWK_ContinueStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Continue-Statement") com.eagle.programmar.AWK.Terminals.AWK_Keyword CONTINUE = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("continue");
		public @DOC("#Continue-Statement") AWK_Keyword CONTINUE = new AWK_Keyword("continue");
	}

}
