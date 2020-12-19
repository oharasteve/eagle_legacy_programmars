// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_DoStatement extends TokenSequence
{
	public @S(10) @DOC("control-structures.do.php") Perl_Keyword DO = new Perl_Keyword("do");
	public @S(20) Perl_Statement stmt;
	public @S(30) Perl_Keyword WHILE = new Perl_Keyword("while");
	public @S(40) Perl_Expression condition;
}
