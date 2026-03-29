// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 9, 2022

namespace com.eagle.programmar.AWK.Statements
{
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class AWK_ExitStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Exit-Statement") com.eagle.programmar.AWK.Terminals.AWK_Keyword EXIT = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("exit");
		public @DOC("#Exit-Statement") AWK_Keyword EXIT = new AWK_Keyword("exit");
	}

}
