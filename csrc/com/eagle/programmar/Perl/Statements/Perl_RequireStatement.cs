// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2014

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Perl_RequireStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice REQUIRE = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("require", "require_once");
		public Perl_KeywordChoice REQUIRE = new Perl_KeywordChoice("require", "require_once");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Expression require;
		public Perl_Expression require;
	}

}
