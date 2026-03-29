// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class C_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("++", "--");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Expression expr;
		public C_Expression expr;
	}

}
