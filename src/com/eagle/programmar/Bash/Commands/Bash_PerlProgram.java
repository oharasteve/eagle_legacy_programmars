// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 9, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
import com.eagle.programmar.Bash.Terminals.Bash_SheBang;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Bash_PerlProgram extends TokenSequence
{
	public @S(10) Bash_SheBang shebang;
	public @S(20) @OPT SeparatedList<PunctuationSlash,Bash_Identifier_Reference> dir;
	public @S(30) @OPT PunctuationSlash slash;
	public @S(40) Bash_Keyword PERL = new Bash_Keyword("perl");
	public @S(50) @OPT TokenList<Bash_PerlOption> options;
	public @S(60) Bash_RealEndOfLine eoln;
	
	public @S(60) @SYNTAX(Perl_Syntax.class) Perl_Program perlProg;
	
	public static class Bash_PerlOption extends TokenChooser
	{
		public @CHOICE Bash_Keyword W = new Bash_Keyword("-w");
	}
}
