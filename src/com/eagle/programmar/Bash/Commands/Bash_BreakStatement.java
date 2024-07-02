// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_BreakStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#index-break") Bash_Keyword BREAK = new Bash_Keyword("break");
}
