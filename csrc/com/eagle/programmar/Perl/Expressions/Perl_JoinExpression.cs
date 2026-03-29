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
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Perl_JoinExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword JOIN = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("join");
		public Perl_Keyword JOIN = new Perl_Keyword("join");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Perl.Perl_Expression, com.eagle.tokens.punctuation.PunctuationComma> items;
		public SeparatedList<Perl_Expression, PunctuationComma> items;
	}

}
