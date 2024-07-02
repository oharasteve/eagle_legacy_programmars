// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Perl_DieStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword DIE = new Perl_Keyword("die");
	public @S(20) @OPT Perl_Literal message;
}
