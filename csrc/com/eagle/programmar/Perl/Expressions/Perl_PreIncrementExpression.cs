// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_PunctuationChoice = com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Perl_PreIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice operator = new com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice("++", "--");
		public Perl_PunctuationChoice @operator = new Perl_PunctuationChoice("++", "--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Variable var;
		public Perl_Variable var;
	}

}
