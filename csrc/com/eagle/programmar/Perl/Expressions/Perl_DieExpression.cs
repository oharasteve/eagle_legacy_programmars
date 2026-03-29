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
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Perl_DieExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword DIE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("die");
		public Perl_Keyword DIE = new Perl_Keyword("die");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Expression expr = new com.eagle.programmar.Perl.Perl_Expression();
		public Perl_Expression expr = new Perl_Expression();
	}

}
