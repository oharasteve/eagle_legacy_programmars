// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Bash_ConditionConstants extends PrimaryOperator
{
	public @S(10) Bash_KeywordChoice TRUE = new Bash_KeywordChoice("false", "true");
}
