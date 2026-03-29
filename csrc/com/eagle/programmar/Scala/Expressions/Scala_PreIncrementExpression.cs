// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Scala.Expressions
{
	using Scala_Variable = com.eagle.programmar.Scala.Scala_Variable;
	using Scala_PunctuationChoice = com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Scala_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice preIncrementOperator = new com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice("++", "--");
		public Scala_PunctuationChoice preIncrementOperator = new Scala_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Scala_Variable var;
		public Scala_Variable var;
	}

}
