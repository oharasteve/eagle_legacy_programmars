// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Scala.Functions
{
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Scala_ReverseMethod : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Scala_Expression leftExpr = new com.eagle.programmar.Scala.Scala_Expression(this, AllowedPrecedence.HIGHER);
		public Scala_Expression leftExpr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Terminals.Scala_Keyword REVERSE = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("reverse");
		public Scala_Keyword REVERSE = new Scala_Keyword("reverse");
	}

}
