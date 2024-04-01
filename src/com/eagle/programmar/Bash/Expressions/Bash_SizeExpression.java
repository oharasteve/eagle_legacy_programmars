// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_SizeExpression extends PrimaryOperator
{
	public @S(10) Bash_Punctuation bang = new Bash_Punctuation("#");
	public @S(20) Bash_Variable var;
}
