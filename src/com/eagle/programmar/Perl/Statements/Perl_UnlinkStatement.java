// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Perl_UnlinkStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword UNLINK = new Perl_Keyword("unlink");
	public @S(20) Perl_Expression expr;
}
