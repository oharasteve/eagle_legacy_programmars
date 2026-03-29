// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2014

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Package_Definition = com.eagle.programmar.Perl.Symbols.Perl_Package_Definition;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Perl_PackageStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword PACKAGE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("package");
		public Perl_Keyword PACKAGE = new Perl_Keyword("package");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Package_Definition pkg;
		public Perl_Package_Definition pkg;
	}

}
