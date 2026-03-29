// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Go.Expressions
{
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_PunctuationChoice = com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Go_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Terminals.Go_PunctuationChoice operator = new com.eagle.programmar.Go.Terminals.Go_PunctuationChoice("++", "--");
		public Go_PunctuationChoice @operator = new Go_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Go_Variable var;
		public Go_Variable var;
	}

}
