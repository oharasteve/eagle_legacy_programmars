// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class CSharp_LambdaExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression var = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation lambda = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("=>");
		public CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.CSharp_Expression expr;
		public CSharp_Expression expr;
	}

}
