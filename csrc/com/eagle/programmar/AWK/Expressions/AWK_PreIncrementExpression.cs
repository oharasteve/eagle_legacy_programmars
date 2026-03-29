// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_PunctuationChoice = com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class AWK_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice operator = new com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice("++", "--");
		public AWK_PunctuationChoice @operator = new AWK_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.AWK_Expression expr;
		public AWK_Expression expr;
	}

}
