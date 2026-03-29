// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Gupta.Expressions
{
	using Gupta_Expression = com.eagle.programmar.Gupta.Gupta_Expression;
	using Gupta_PunctuationChoice = com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Gupta_Multiplicative_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Gupta_Expression left = new com.eagle.programmar.Gupta.Gupta_Expression(this, AllowedPrecedence.ATLEAST);
		public Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice timesDivide = new com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice("*", "/");
		public Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("*", "/");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Gupta.Gupta_Expression right = new com.eagle.programmar.Gupta.Gupta_Expression(this, AllowedPrecedence.HIGHER);
		public Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
	}

}
