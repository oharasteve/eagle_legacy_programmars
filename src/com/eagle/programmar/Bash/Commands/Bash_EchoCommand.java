// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_EchoCommand extends TokenSequence
{
	public @S(10) @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
	public @S(20) @OPT TokenList<Bash_Expression> lines;
}
