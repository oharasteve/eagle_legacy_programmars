// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_EndOfLine = com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using Perl_Program = com.eagle.programmar.Perl.Perl_Program;
	using Perl_Syntax = com.eagle.programmar.Perl.Perl_Syntax;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_Literal = com.eagle.programmar.Perl.Terminals.Perl_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class CMD_Perl_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword PERL = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("perl");
		public CMD_Keyword PERL = new CMD_Keyword("perl");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_Perl_Option> opts;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.CMD.CMD_Expression> args;
		public TokenList<CMD_Expression> args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_EndOfLine eoln1;
		public CMD_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) CMD_Goto_Statement gotoStatement;
		public CMD_Goto_Statement gotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CMD.Terminals.CMD_EndOfLine eoln2;
		public CMD_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CMD_Punctuation at = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('@');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) CMD_Rem_Statement remStatement;
		public CMD_Rem_Statement remStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.CMD.Terminals.CMD_EndOfLine eoln3;
		public CMD_EndOfLine eoln3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) com.eagle.programmar.Perl.Perl_Program perlProgram;
		public @SYNTAX(typeof(Perl_Syntax)) Perl_Program perlProgram;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) com.eagle.programmar.Perl.Terminals.Perl_Keyword END = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("__END__");
		public @SYNTAX(typeof(Perl_Syntax)) Perl_Keyword END = new Perl_Keyword("__END__");

		public static class CMD_Perl_Option extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Perl_Option_E extends com.eagle.tokens.TokenSequence
			public static class CMD_Perl_Option_E extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation dash = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation dash = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword E = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("e");
				public CMD_Keyword E = new CMD_Keyword("e");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Literal literal;
				public Perl_Literal literal;
			}
		}
	}

}
