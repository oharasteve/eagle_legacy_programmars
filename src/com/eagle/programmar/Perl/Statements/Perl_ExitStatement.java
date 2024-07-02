// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Perl_ExitStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("function.exit.php") Perl_Keyword EXIT = new Perl_Keyword("exit");
}
