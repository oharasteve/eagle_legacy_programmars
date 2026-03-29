// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 9, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_RealEndOfLine = com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
	using Bash_SheBang = com.eagle.programmar.Bash.Terminals.Bash_SheBang;
	using Perl_Program = com.eagle.programmar.Perl.Perl_Program;
	using Perl_Syntax = com.eagle.programmar.Perl.Perl_Syntax;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class Bash_PerlProgram : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_SheBang shebang;
		public Bash_SheBang shebang;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<com.eagle.tokens.punctuation.PunctuationSlash, com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference> dir;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationSlash slash;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_Keyword PERL = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("perl");
		public Bash_Keyword PERL = new Bash_Keyword("perl");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Bash_PerlOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine eoln;
		public Bash_RealEndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) com.eagle.programmar.Perl.Perl_Program perlProg;
		public @SYNTAX(typeof(Perl_Syntax)) Perl_Program perlProg;

		public static class Bash_PerlOption extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-e", "-i", "-lne", "-pi", "-w");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-e", "-i", "-lne", "-pi", "-w");
		}
	}

}
