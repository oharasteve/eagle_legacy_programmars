// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_NotCondition extends PrimaryOperator
{
	public @S(10) Bash_Punctuation NOT = new Bash_Punctuation("!");
	public @S(20) Bash_Condition condition;
}
