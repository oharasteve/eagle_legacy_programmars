// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Expressions
{
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Punctuation = com.eagle.programmar.Rexx.Terminals.Rexx_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Rexx_LogicalXorExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Rexx_Expression left = new com.eagle.programmar.Rexx.Rexx_Expression(this, AllowedPrecedence.ATLEAST);
		public Rexx_Expression left = new Rexx_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Terminals.Rexx_Punctuation AND = new com.eagle.programmar.Rexx.Terminals.Rexx_Punctuation("&&");
		public Rexx_Punctuation AND = new Rexx_Punctuation("&&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Rexx_Expression right = new com.eagle.programmar.Rexx.Rexx_Expression(this, AllowedPrecedence.HIGHER);
		public Rexx_Expression right = new Rexx_Expression(this, AllowedPrecedence.HIGHER);
	}

}
