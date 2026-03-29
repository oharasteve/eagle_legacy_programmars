// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Ada.Expressions
{
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Ada_Subfield : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Ada_Expression left = new com.eagle.programmar.Ada.Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Expression right = new com.eagle.programmar.Ada.Ada_Expression(this, AllowedPrecedence.HIGHER);
		public Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}

}
