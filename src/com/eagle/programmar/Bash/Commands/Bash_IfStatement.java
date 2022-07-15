// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;

public class Bash_IfStatement extends TokenSequence
{
	public @S(10) Bash_Keyword IF = new Bash_Keyword("if");
}
