// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.Perl
{
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Perl_StatementSuffixModifier : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_StatementIfSuffix extends com.eagle.tokens.TokenSequence
		public class Perl_StatementIfSuffix : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice IfUnless = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("if", "unless", "while");
			public Perl_KeywordChoice IfUnless = new Perl_KeywordChoice("if", "unless", "while");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_MinusF minusF;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Perl_Expression condition;
			public Perl_Expression condition;

			public class Perl_MinusF : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationHyphen minus;
				public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice DF = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("d", "f");
				public Perl_KeywordChoice DF = new Perl_KeywordChoice("d", "f");
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_StatementOrSuffix extends com.eagle.tokens.TokenSequence
		public class Perl_StatementOrSuffix : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword OR = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("or");
			public Perl_Keyword OR = new Perl_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_StatementList statement;
			public Perl_StatementList statement;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_StatementBarSuffix extends com.eagle.tokens.TokenSequence
		public class Perl_StatementBarSuffix : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation barBar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("||");
			public Perl_Punctuation barBar = new Perl_Punctuation("||");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_StatementList statement;
			public Perl_StatementList statement;
		}
	}

}
