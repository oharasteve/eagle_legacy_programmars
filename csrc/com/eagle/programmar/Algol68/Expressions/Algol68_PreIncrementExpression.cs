// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using Algol68_PunctuationChoice = com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Algol68_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice preIncrementOperator = new com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice("++", "--");
		public Algol68_PunctuationChoice preIncrementOperator = new Algol68_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Variable var;
		public Algol68_Variable var;
	}
}
