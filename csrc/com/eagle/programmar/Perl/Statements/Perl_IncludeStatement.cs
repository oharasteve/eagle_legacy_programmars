// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Perl_IncludeStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("@");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("function.include.php") com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice INCLUDE = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("include", "include_once");
		public @DOC("function.include.php") Perl_KeywordChoice INCLUDE = new Perl_KeywordChoice("include", "include_once");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression filename;
		public Perl_Expression filename;
	}

}
