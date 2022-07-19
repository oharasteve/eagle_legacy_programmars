// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Bash_Assignment extends TokenSequence
{
	public @S(10) @DOC("#Shell-Arithmetic") @OPT Bash_Keyword LET = new Bash_Keyword("let");
	public @S(20) Bash_Variable variable;
	public @S(30) PunctuationEquals equals;
	public @S(40) Bash_Expression value;
}
