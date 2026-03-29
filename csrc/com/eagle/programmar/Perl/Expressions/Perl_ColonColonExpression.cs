// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Perl_ColonColonExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Perl_Expression left = new com.eagle.programmar.Perl.Perl_Expression(this, AllowedPrecedence.ATLEAST);
		public Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation colonColon = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("::");
		public Perl_Punctuation colonColon = new Perl_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression right = new com.eagle.programmar.Perl.Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	}

}
