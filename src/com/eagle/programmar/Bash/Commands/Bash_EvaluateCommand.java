// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 23, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Bash_EvaluateCommand extends TokenSequence
{
	public @S(10) Bash_Punctuation leftParenParen = new Bash_Punctuation("((");
	public @S(20) Bash_Expression expr;
	public @S(30) Bash_Punctuation rightParenParen = new Bash_Punctuation("))");
}
