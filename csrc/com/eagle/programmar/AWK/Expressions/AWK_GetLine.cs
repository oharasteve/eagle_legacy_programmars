// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using AWK_Punctuation = com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class AWK_GetLine : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#index-getline-command") com.eagle.programmar.AWK.Terminals.AWK_Keyword GETLINE = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("GETLINE");
		public @DOC("#index-getline-command") AWK_Keyword GETLINE = new AWK_Keyword("GETLINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.AWK_Variable var;
		public AWK_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.Terminals.AWK_Punctuation lessThan = new com.eagle.programmar.AWK.Terminals.AWK_Punctuation('<');
		public AWK_Punctuation lessThan = new AWK_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.AWK.AWK_Variable file;
		public AWK_Variable file;
	}

}
