// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;

public class Bash_DieCommand extends TokenSequence
{
	public @S(10) Bash_Keyword DIE = new Bash_Keyword("die");
	public @S(20) @OPT Bash_Expression msg;
}
