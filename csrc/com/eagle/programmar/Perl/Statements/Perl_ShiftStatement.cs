// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Perl_ShiftStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword SHIFT = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("shift");
		public Perl_Keyword SHIFT = new Perl_Keyword("shift");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Variable var;
		public Perl_Variable var;
	}

}
