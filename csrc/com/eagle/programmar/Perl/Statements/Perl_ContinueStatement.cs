// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Perl_ContinueStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.continue.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword CONTINUE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("continue");
		public @DOC("control-structures.continue.php") Perl_Keyword CONTINUE = new Perl_Keyword("continue");
	}

}
