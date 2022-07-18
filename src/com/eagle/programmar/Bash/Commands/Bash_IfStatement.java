// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Bash_IfStatement extends TokenSequence
{
	public @S(10) Bash_Keyword IF = new Bash_Keyword("if");
	public @S(20) Bash_Punctuation leftBrackets = new Bash_Punctuation("[[");
	public @S(30) Bash_Expression condition;
	public @S(40) Bash_Punctuation rightBrackets = new Bash_Punctuation("]]");
}
