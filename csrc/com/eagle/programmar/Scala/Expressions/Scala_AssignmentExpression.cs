// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Scala.Expressions
{
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_PunctuationChoice = com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Scala_AssignmentExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Scala_Expression var = new com.eagle.programmar.Scala.Scala_Expression(this, AllowedPrecedence.HIGHER);
		public Scala_Expression var = new Scala_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice equals = new com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
		public Scala_PunctuationChoice equals = new Scala_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Expression expr;
		public Scala_Expression expr;
	}

}
