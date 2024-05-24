// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_Perl_Statement extends TokenSequence
{
	public @S(10) CMD_Keyword PERL = new CMD_Keyword("perl");
	public @S(20) @OPT TokenList<CMD_Perl_Option> opts;
	public @S(30) TokenList<CMD_Argument> args;
	public @S(40) CMD_EndOfLine eoln1;
	public @S(50) CMD_Goto_Statement gotoStatement;
	public @S(60) CMD_EndOfLine eoln2;
	public @S(70) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(80) CMD_Rem_Statement remStatement;
	public @S(90) CMD_EndOfLine eoln3;

	public @S(100) @SYNTAX(Perl_Syntax.class) Perl_Program perlProgram;

	public @S(110) @SYNTAX(Perl_Syntax.class) Perl_Keyword END = new Perl_Keyword("__END__");

	public static class CMD_Perl_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Perl_Option_E extends TokenSequence
		{
			public @S(10) CMD_Punctuation slash = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword E = new CMD_Keyword("e");
		}
	}
}
