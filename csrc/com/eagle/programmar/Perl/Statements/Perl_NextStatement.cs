// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 7, 2014

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Label_Reference = com.eagle.programmar.Perl.Symbols.Perl_Label_Reference;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Perl_NextStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword NEXT = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("next");
		public Perl_Keyword NEXT = new Perl_Keyword("next");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Label_Reference label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_NextUnless unless;
		public  OPT;

		public class Perl_NextUnless : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword UNLESS = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("unless");
			public Perl_Keyword UNLESS = new Perl_Keyword("unless");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationHyphen minus;
			public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Keyword F = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("f");
			public Perl_Keyword F = new Perl_Keyword("f");
		}
	}

}
