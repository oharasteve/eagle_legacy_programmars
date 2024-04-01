// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_DollarNumber extends PrimaryOperator
{
	public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) Bash_Number number;
}
