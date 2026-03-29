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
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Perl_ExistsExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationHyphen minus;
		public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice XE = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("e", "x");
		public Perl_KeywordChoice XE = new Perl_KeywordChoice("e", "x");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression file;
		public Perl_Expression file;
	}

}
