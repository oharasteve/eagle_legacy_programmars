// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_Use_Definition = com.eagle.programmar.Perl.Symbols.Perl_Use_Definition;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using Perl_PunctuationChoice = com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Perl_UseStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword USE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("use");
		public Perl_Keyword USE = new Perl_Keyword("use");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_UseWhat what;
		public Perl_UseWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_UseAs as;
		public  OPT;

		public class Perl_UseWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Perl_UseRemote extends com.eagle.tokens.TokenSequence
			public class Perl_UseRemote : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice NET = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("File", "Getopt", "IPC", "List", "MIME", "Net", "Time", "Win32");
				public Perl_KeywordChoice NET = new Perl_KeywordChoice("File", "Getopt", "IPC", "List", "MIME", "Net", "Time", "Win32");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Punctuation colonColon = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("::");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_Identifier_Reference id;
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_UseLocal extends com.eagle.tokens.TokenSequence
			public class Perl_UseLocal : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
				public Perl_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Perl_MoreUse> more;
				public  OPT;

				public class Perl_MoreUse : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice backSlash = new com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice("\\", "::");
					public Perl_PunctuationChoice backSlash = new Perl_PunctuationChoice("\\", "::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
					public Perl_Identifier_Reference id;
				}
			}
		}

		public class Perl_UseAs : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword AS = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("as");
			public Perl_Keyword AS = new Perl_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Use_Definition id;
			public Perl_Use_Definition id;
		}
	}

}
